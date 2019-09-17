package com.firestore.ksgeyik.common

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("bind:visibility")
fun View.showHide(show: Boolean) {
    this.visibility = when (show) {
        true -> View.VISIBLE
        false -> View.GONE
    }
}

@BindingAdapter("bind:clickable")
fun View.clickable(isClickable: Boolean) {
    this.isClickable = isClickable
}

@BindingAdapter("bind:loadImage")
fun AppCompatImageView.loadImage(url: String?) {
    if (!url.isNullOrEmpty()) {
        this.visibility = View.VISIBLE
        Glide.with(context).load(url).into(this)
    } else {
        this.visibility = View.GONE
    }
}




