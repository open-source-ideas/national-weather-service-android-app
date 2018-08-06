package org.osii.nwsapp.ui.tmp

import javax.inject.Inject

import org.osii.nwsapp.injection.ConfigPersistent
import org.osii.nwsapp.ui.base.BasePresenter

@ConfigPersistent
class TmpPresenter @Inject
constructor() : BasePresenter<TmpMvpView>() {

    override fun attachView(mvpView: TmpMvpView) {
        super.attachView(mvpView)
    }

    override fun detachView() {
        super.detachView()
    }


}
