package com.vaibhavdhunde.practice.myposts.ui.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vaibhavdhunde.practice.myposts.R
import com.vaibhavdhunde.practice.myposts.databinding.ItemPostBinding
import com.vaibhavdhunde.practice.myposts.model.Post

class PostViewHolder(
    private val itemPostBinding: ItemPostBinding
) : RecyclerView.ViewHolder(itemPostBinding.root) {

    fun bind(post: Post) {
        itemPostBinding.post = post
        itemPostBinding.executePendingBindings()
    }

    companion object {
        operator fun invoke(parent: ViewGroup): PostViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_post, parent, false)
            val binding = ItemPostBinding.bind(view)
            return PostViewHolder(binding)
        }
    }
}