package com.example.viacep.util

import android.content.Context
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetView

fun Fragment.hideKeyboard() {
    val view = activity?.currentFocus
    if (view != null) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
        view.clearFocus()
    }
}

fun Fragment.setupTapTargetView(view: View, title: String, description: String, targetRadius: Int = 100, tintTarget: Boolean = false, transparentTarget: Boolean = false) {
    TapTargetView.showFor(
        requireActivity(), TapTarget.forView(
            view,
            title,
            description
        )
            .tintTarget(tintTarget)
            .targetRadius(targetRadius)
            .transparentTarget(transparentTarget)
    )
}

fun <T : Parcelable?> Bundle.getParcelableCompat(key: String, clazz: Class<T>): T? = when {
    SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getParcelable(key, clazz)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}