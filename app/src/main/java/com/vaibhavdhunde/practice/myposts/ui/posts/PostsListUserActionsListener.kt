package com.vaibhavdhunde.practice.myposts.ui.posts

import com.vaibhavdhunde.practice.myposts.model.Post

interface PostsListUserActionsListener {

    fun onClickPost(post: Post)
}