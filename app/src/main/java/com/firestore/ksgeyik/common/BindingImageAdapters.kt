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


@BindingAdapter("bind:loadOrHideImage")
fun AppCompatImageView.loadOrHideImage(url: String?) {
    if (!url.isNullOrEmpty()) {
        this.visibility = View.VISIBLE
        Glide.with(context).load(url).into(this)
    } else {
        this.visibility = View.GONE
    }
}

@BindingAdapter("bind:loadOrHoldCircleImage")
fun AppCompatImageView.loadOrHoldCircleImage(url: String?) {
    when (!url.isNullOrEmpty()) {
        true -> {
            this.visibility = View.VISIBLE
            Glide.with(context).load(url).apply(RequestOptions.circleCropTransform()).into(this)
        }
        false -> {
            Glide.with(context).load(R.drawable.ic_user).apply(RequestOptions.circleCropTransform()).into(this)

        }
    }
}

@BindingAdapter("bind:loadMyPicture")
fun AppCompatImageView.loadMyPicture(post: Post?) {
    val user: User? = Hawk.get<User>(Constants.USER)
    val url = user?.photoUrl
    when (!url.isNullOrEmpty()) {
        true -> {
            this.visibility = View.VISIBLE
            Glide.with(context).load(url).apply(RequestOptions.circleCropTransform()).into(this)
        }
        false -> {
            Glide.with(context).load(R.drawable.ic_user).apply(RequestOptions.circleCropTransform()).into(this)

        }
    }
}

@BindingAdapter("bind:likeOrUnlike")
fun AppCompatImageView.likeOrUnlike(post: Post?) {
    val isLiked: Boolean? = post?.likeList.toString().contains(Hawk.get<User>(Constants.USER).toString())
    isLiked?.let {
        when (isLiked) {
            true -> Glide.with(context).load(R.drawable.ic_liked).into(this)
            false -> Glide.with(context).load(R.drawable.ic_like).into(this)
        }
    } ?: run { Glide.with(context).load(R.drawable.ic_like).into(this) }
}
