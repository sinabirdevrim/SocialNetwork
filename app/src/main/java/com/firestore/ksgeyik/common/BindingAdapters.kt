package com.firestore.ksgeyik.common

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class BindingAdapters {

    companion object {
        @JvmStatic
        @BindingAdapter("bind:visibility")
        fun View.showHide(show: Boolean) {
            this.visibility = when (show) {
                true -> View.VISIBLE
                false -> View.GONE
            }
        }

        @JvmStatic
        @BindingAdapter("bind:clickable")
        fun View.clickable(isClickable: Boolean) {
            this.isClickable = isClickable
        }

        @JvmStatic
        @BindingAdapter("bind:loadImage")
        fun AppCompatImageView.loadImage(url: String?) {
            url?.let { Glide.with(this.context).load(url).into(this) }

        }
    }
}

