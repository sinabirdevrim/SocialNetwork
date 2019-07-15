package com.firestore.ksgeyik.common

import android.view.View
import android.widget.ImageView
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
        @BindingAdapter("bind:imageUrl")
        fun ImageView.loadImage(url: String) {
            Glide.with(this.context).load(url).into(this)
        }
    }
}

