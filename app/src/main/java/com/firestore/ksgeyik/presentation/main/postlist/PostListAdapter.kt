package com.firestore.ksgeyik.presentation.main.postlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firestore.android.repository.model.Post
import com.firestore.ksgeyik.R
import kotlinx.android.synthetic.main.item_post_list.view.*

class PostListAdapter(val items: List<Post>, val context: Context?) :
    RecyclerView.Adapter<PostListAdapter.PostListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        return PostListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_post_list, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        holder.itemPostListTv.text = items[position].contentText
    }

    class PostListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemPostListTv = view.itemPostListTv
        val itemPostListIv = view.itemPostListIv

    }
}