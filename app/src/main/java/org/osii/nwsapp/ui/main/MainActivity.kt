package org.osii.nwsapp.ui.main

import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import org.osii.nwsapp.R

import javax.inject.Inject

import org.osii.nwsapp.ui.base.BaseActivity

class MainActivity : BaseActivity(), MainMvpView {

    @Inject lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        setContentView(R.layout.activity_main)
        presenter.attachView(this)

        presenter.setup()
    }

    override fun showCityAndState(city: String, state: String) {
        cityAndState.text = getString(R.string.city_state_holder, city, state)
    }

    override fun showError(error: String) {
        toast(error)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

}
