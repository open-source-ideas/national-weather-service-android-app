package org.osii.nwsapp.ui.tmp

import android.os.Bundle
import org.osii.nwsapp.R

import javax.inject.Inject

import org.osii.nwsapp.ui.base.BaseActivity

class TmpActivity : BaseActivity(), TmpMvpView {

    @Inject lateinit var presenter: TmpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        setContentView(R.layout.activity_main)

        presenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

}
