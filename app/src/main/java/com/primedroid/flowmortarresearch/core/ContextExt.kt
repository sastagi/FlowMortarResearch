package com.primedroid.flowmortarresearch.core

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.drawable.Drawable
import android.support.annotation.*
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

fun Context.inflater(): LayoutInflater {
    return LayoutInflater.from(this)
}

fun Context.inflate(@LayoutRes resource: Int, root: ViewGroup?, attachToRoot: Boolean): View {
    return LayoutInflater.from(this).inflate(resource, root, attachToRoot)
}

fun Context.inflate(@LayoutRes resource: Int, root: ViewGroup?): View {
    return LayoutInflater.from(this).inflate(resource, root)
}

fun Context.getActivity(): Activity? {
    return if (this is Activity) {
        this
    } else if (this is ContextWrapper) {
        this.baseContext.getActivity()
    } else {
        null
    }
}

fun Context.getColorCompat(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}

fun Context.getDrawableCompat(@DrawableRes drawableRes: Int): Drawable {
    return ContextCompat.getDrawable(this, drawableRes)
}

fun Context.getStringList(@ArrayRes arrayRes: Int): List<String> {
    return resources.getStringArray(arrayRes).toList()
}

fun Context.showToast(@StringRes textId: Int, isLong: Boolean = false) =
        showToast(getString(textId), isLong)

fun Context.showToast(text: String, isLong: Boolean = false) =
        Toast.makeText(this, text, if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()

fun Context.showNotImplementedYetToast() = showToast("Not implemented yet!")




fun Context.getDimensionPx(@DimenRes dimensionResId: Int) =
        resources.getDimensionPixelSize(dimensionResId)



