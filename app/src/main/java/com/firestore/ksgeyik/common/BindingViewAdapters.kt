package com.firestore.ksgeyik.common

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.firestore.android.repository.model.User
import com.firestore.ksgeyik.R
import com.firestore.ksgeyik.data.model.Post
import com.orhanobut.hawk.Hawk


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
