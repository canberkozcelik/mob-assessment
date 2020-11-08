package com.mobiquity.assessment.extension

import android.os.SystemClock
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat

fun View.resDrawable(@DrawableRes drawableRes: Int) =
    ResourcesCompat.getDrawable(this.resources, drawableRes, null)

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

private class SafeClickListener(
    private var defaultInterval: Int = 1000,
    private val onSafeCLick: (View) -> Unit
) : View.OnClickListener {
    private var lastTimeClicked: Long = 0
    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeCLick(v)
    }
}