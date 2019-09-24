package com.vaibhavdhunde.practice.myposts.ui.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vaibhavdhunde.practice.myposts.R
import com.vaibhavdhunde.practice.myposts.databinding.ItemPostBinding
import com.vaibhavdhunde.practice.myposts.model.Post

class PostViewHolder(
    private val itemPostBinding: ItemPostBinding,
    private val viewModel: PostsViewModel
) : RecyclerView.ViewHolder(itemPostBinding.root) {

    fun bind(post: Post) {
        itemPostBinding.post = post
        itemPostBinding.listener = this@PostViewHolder.listener
        itemPostBinding.executePendingBindings()
    }

    private val listener = object : PostsListUserActionsListener {
        override fun onClickPost(post: Post) {
            viewModel.openPostDetails(post.postId)
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup, viewModel: PostsViewModel): PostViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_post, parent, false)
            val binding = ItemPostBinding.bind(view)
            return PostViewHolder(binding, viewModel)
        }
    }
}