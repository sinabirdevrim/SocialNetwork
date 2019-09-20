package com.firestore.ksgeyik.presentation.main.postlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.firestore.ksgeyik.R
import com.firestore.ksgeyik.data.model.Post
import com.firestore.ksgeyik.databinding.ItemPostListBinding

class PostListAdapter(
    private val items: List<Post>,
    private val context: Context?,
    var likeClick: (arg: Post) -> Unit?,
    var commentClick: (arg: Post) -> Unit?
) : RecyclerView.Adapter<PostListAdapter.PostListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostListViewHolder(ItemPostListBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_post_list, parent, false)))

    override fun getItemCount() = items.size


    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        val post = items[position]
        holder.bind(post)
        holder.itemPostListCommentIv?.setOnClickListener { commentClick(post) }
        holder.itemPostListLikeIv?.setOnClickListener { likeClick(post) }
    }

    class PostListViewHolder constructor(private val binding: ItemPostListBinding) : RecyclerView.ViewHolder(binding.root) {
        var itemPostListLikeIv: ImageView? = null
        var itemPostListCommentIv: ImageView? = null
        fun bind(post: Post) {
            binding.post = post
            itemPostListLikeIv = binding.itemPostListLikeIv
            itemPostListCommentIv = binding.itemPostListCommentIv
        }
    }
}