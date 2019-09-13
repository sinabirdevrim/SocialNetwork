package com.firestore.ksgeyik.presentation.main.postlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.firestore.android.repository.model.Post
import com.firestore.ksgeyik.R
import kotlinx.android.synthetic.main.item_post_list.view.*

class PostListAdapter(val items: List<Post>, val context: Context?) :
    RecyclerView.Adapter<PostListAdapter.PostListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        return PostListViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_post_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        holder.itemPostListTv.text = items[position].contentText
        context?.let {
            if (!items[position].contentPhotoUrl.isNullOrEmpty()) {
                Glide.with(context).load(items[position].contentPhotoUrl)
                    .into(holder.itemPostListIv)
            } else {
                holder.itemPostListIv.visibility = View.GONE
            }

            Glide.with(context).load(items[position].user?.photoUrl).apply(RequestOptions.circleCropTransform().placeholder(R.drawable.ic_user).error(R.drawable.ic_user)).into(holder.itemPostUserIv)
        }

        holder.itemPostUserTv.text = items[position].user?.name + items[position].user?.surname
    }

    class PostListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemPostListTv = view.itemPostListTv
        val itemPostListIv = view.itemPostListIv
        val itemPostUserTv = view.itemPostUserTv
        val itemPostUserIv = view.itemPostUserIv

    }
}