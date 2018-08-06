package org.osii.nwsapp.util;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * RxJava2 and Retrofit 2.2.0 compatible factory,
 * which wraps the {@link RxJava2CallAdapterFactory} and takes care of the error conversion.
 *
 * Based on: https://github.com/square/retrofit/issues/1102#issuecomment-241250796
 */
public class RxThreadingWithErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
    private final RxJava2CallAdapterFactory mOriginalCallAdapterFactory;

    private RxThreadingWithErrorHandlingCallAdapterFactory() {
        mOriginalCallAdapterFactory = RxJava2CallAdapterFactory
                .createWithScheduler(Schedulers.io());
    }

    public static CallAdapter.Factory create() {
        return new RxThreadingWithErrorHandlingCallAdapterFactory();
    }

    @Override
    public CallAdapter<?, ?> get(final Type returnType,
                                 final Annotation[] annotations, final Retrofit retrofit) {
        return new RxCallAdapterWrapper<>(
                retrofit,
                mOriginalCallAdapterFactory.get(returnType, annotations, retrofit)
        );
    }

    private static class RxCallAdapterWrapper<R> implements CallAdapter<R, Object> {
        private final Retrofit mRetrofit;
        private final CallAdapter<R, ?> mWrappedCallAdapter;

        public RxCallAdapterWrapper(final Retrofit retrofit, final CallAdapter<R, ?> wrapped) {
            mRetrofit = retrofit;
            mWrappedCallAdapter = wrapped;
        }

        @Override
        public Type responseType() {
            return mWrappedCallAdapter.responseType();
        }

        @SuppressWarnings("unchecked")
        @Override
        public Object adapt(final Call<R> call) {
            Object observable = mWrappedCallAdapter.adapt(call);

            if (observable instanceof Flowable) {
                return ((Flowable) mWrappedCallAdapter.adapt(call)).onErrorResumeNext(
                        new Function<Throwable, Flowable>() {
                            @Override
                            public Flowable apply(final Throwable throwable) {
                                return Flowable.error(asRetrofitException(throwable));
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
            if (observable instanceof Single) {
                return ((Single) mWrappedCallAdapter.adapt(call)).onErrorResumeNext(
                        new Function<Throwable, SingleSource>() {
                            @Override
                            public Single apply(final Throwable throwable) {
                                return Single.error(asRetrofitException(throwable));
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
            if (observable instanceof Maybe) {
                return ((Maybe) mWrappedCallAdapter.adapt(call)).onErrorResumeNext(
                        new Function<Throwable, MaybeSource>() {
                            @Override
                            public Maybe apply(final Throwable throwable) {
                                return Maybe.error(asRetrofitException(throwable));
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
            if (observable instanceof Completable) {
                return ((Completable) mWrappedCallAdapter.adapt(call)).onErrorResumeNext(
                        new Function<Throwable, CompletableSource>() {
                            @Override
                            public Completable apply(final Throwable throwable) {
                                return Completable.error(asRetrofitException(throwable));
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            } else {
                return ((Observable) mWrappedCallAdapter.adapt(call)).onErrorResumeNext(
                        new Function<Throwable, ObservableSource>() {
                            @Override
                            public Observable apply(final Throwable throwable) {
                                return Observable.error(asRetrofitException(throwable));
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        }

        private RetrofitException asRetrofitException(final Throwable throwable) {
            // We had non-200 http error
            if (throwable instanceof HttpException) {
                final HttpException httpException = (HttpException) throwable;
                final Response response = httpException.response();

                return RetrofitException.httpError(
                        response.raw().request().url().toString(),
                        response, mRetrofit
                );
            }
            // A network error happened
            if (throwable instanceof IOException) {
                return RetrofitException.networkError((IOException) throwable);
            }

            // We don't know what happened. We need to simply convert to an unknown error

            return RetrofitException.unexpectedError(throwable);
        }
    }
}