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


@BindingAdapter("bind:setLikeText")
fun AppCompatTextView.setLikeText(post: Post?) {
    val likeCount: Int? = post?.likeList?.size
    likeCount?.let {
        when (likeCount > 0) {
            true -> this.text = likeCount.toString() + context.getString(R.string.like)
            false -> this.visibility = View.GONE
        }
    } ?: run { this.visibility = View.GONE }

}

@BindingAdapter("bind:setCommentText")
fun AppCompatTextView.setCommentText(post: Post?) {
    val commentCount: Int? = post?.commentList?.size
    commentCount?.let {
        when (commentCount > 0) {
            true -> this.text = commentCount.toString() + context.getString(R.string.comment)
            false -> this.visibility = View.GONE
        }
    } ?: run { this.visibility = View.GONE }
}
