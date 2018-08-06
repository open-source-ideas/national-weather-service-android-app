package org.osii.nwsapp.util.extension

import android.util.LongSparseArray
import android.util.SparseArray

public inline fun <V> LongSparseArray<V>.getOrPut(key: Long, defaultValue: () -> V): V {
    val value = get(key)
    return if (value == null) {
        val answer = defaultValue()
        put(key, answer)
        answer
    } else {
        value
    }
}

public inline fun <V> SparseArray<V>.getOrPut(key: Int, defaultValue: () -> V): V {
    val value = get(key)
    return if (value == null) {
        val answer = defaultValue()
        put(key, answer)
        answer
    } else {
        value
    }
}