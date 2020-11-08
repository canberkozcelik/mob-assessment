package com.mobiquity.assessment.extension

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImageCenterCrop(url: String, placeholder: Drawable?) {
    Glide.with(context)
        .load(url)
        .placeholder(placeholder)
        .centerCrop()
        .into(this)
}