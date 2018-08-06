package org.osii.nwsapp.util.extension

import timber.log.Timber


/**
 * Created by Maciej Różański on 31.05.2017.
 */

fun Throwable.log() {
    Timber.e(this, "Rx Error")
}
