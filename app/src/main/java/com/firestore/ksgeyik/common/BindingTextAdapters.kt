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
