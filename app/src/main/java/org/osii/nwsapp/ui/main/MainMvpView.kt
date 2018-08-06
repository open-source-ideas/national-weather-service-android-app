package org.osii.nwsapp.ui.main

import org.osii.nwsapp.ui.base.MvpView

interface MainMvpView : MvpView {

    fun showCityAndState(city: String, state: String)
    fun showError(error: String)

}
