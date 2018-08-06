package org.osii.nwsapp.ui.main

import io.reactivex.rxkotlin.subscribeBy
import org.osii.nwsapp.data.remote.ApiService
import javax.inject.Inject

import org.osii.nwsapp.injection.ConfigPersistent
import org.osii.nwsapp.ui.base.BasePresenter

@ConfigPersistent
class MainPresenter @Inject
constructor(
        private val apiService: ApiService
) : BasePresenter<MainMvpView>() {

    public fun setup() {
        apiService.getWeather(ApiService.TEST_LATITUDE, ApiService.TEST_LONGITUDE)
                .subscribeBy(
                        onSuccess = { weather ->
                            view.showCityAndState(
                                weather.properties?.city ?: "",
                                weather.properties?.state ?: ""
                            )
                        },
                        onError = { throwable ->
                            view.showError(throwable.message ?: "General error")
                    })

    }

}
