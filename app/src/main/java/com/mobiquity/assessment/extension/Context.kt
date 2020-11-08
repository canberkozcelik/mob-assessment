package com.mobiquity.assessment.extension

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * The extension brings the screen height. Return type is integer
 */
val Context?.screenHeight: Int
    get() {
        this?.let {
            val windowManager = it.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            windowManager.let { wm ->
                val dm = DisplayMetrics()
                wm.defaultDisplay.getMetrics(dm)
                return dm.heightPixels
            }
        }
        return 0
    }