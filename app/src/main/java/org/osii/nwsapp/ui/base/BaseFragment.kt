package org.osii.nwsapp.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast

open class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    fun showError(msg: String) {
        Toast.makeText(view?.context, msg, Toast.LENGTH_SHORT).show()
    }

    fun finish() {
        activity?.finish()
    }
}
