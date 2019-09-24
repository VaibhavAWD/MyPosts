package com.vaibhavdhunde.practice.myposts.ui.posts

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.vaibhavdhunde.practice.myposts.model.Post

object PostsListBindings {

    @BindingAdapter("app:posts")
    @JvmStatic
    fun setPosts(rv: RecyclerView, posts: List<Post>?) {
        with(rv.adapter as PostsListAdapter) {
            posts?.let {
                submitList(it)
            }
        }
    }

    @BindingAdapter("app:divider")
    @JvmStatic
    fun setDividerItemDecoration(rv: RecyclerView, orientation: Int) {
        rv.addItemDecoration(DividerItemDecoration(rv.context, orientation))
    }
}