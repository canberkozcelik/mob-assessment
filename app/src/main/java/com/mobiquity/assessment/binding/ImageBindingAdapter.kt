package com.mobiquity.assessment.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.mobiquity.assessment.R
import com.mobiquity.assessment.extension.loadImageCenterCrop
import com.mobiquity.assessment.extension.resDrawable

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("setImageCenterCrop")
    fun setImageCenterCrop(view: ImageView, url: String?) {
        url?.let {
            view.loadImageCenterCrop(it, view.resDrawable(R.drawable.ic_noimage))
        }
    }
}