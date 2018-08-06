package org.osii.nwsapp.ui.base

import io.reactivex.disposables.CompositeDisposable

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the [_view] that
 * can be accessed from the children classes by calling [view].
 */
open class BasePresenter<T : MvpView> : Presenter<T> {

    protected val disposables = CompositeDisposable()

    private var _view: T? = null
    val view: T
        get() { return _view ?: throw MvpViewNotAttachedException() }

    override fun attachView(mvpView: T) {
        _view = mvpView
        disposables.clear()
    }

    override fun detachView() {
        _view = null
        disposables.clear()
    }

    class MvpViewNotAttachedException : RuntimeException(
        "Please call Presenter.attachView(MvpView) before requesting data to the Presenter")

}
