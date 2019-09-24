package com.vaibhavdhunde.practice.myposts.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vaibhavdhunde.practice.myposts.data.PostsRepository
import com.vaibhavdhunde.practice.myposts.ui.details.DetailsViewModel
import com.vaibhavdhunde.practice.myposts.ui.posts.PostsViewModel
import java.lang.IllegalArgumentException

class MyPostsViewModelFactory(private val repository: PostsRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(PostsViewModel::class.java) -> PostsViewModel(repository)
                isAssignableFrom(DetailsViewModel::class.java) -> DetailsViewModel(repository)
                else -> throw IllegalArgumentException("Unknown viewmodel class: $modelClass")
            }
        } as T
    }
}