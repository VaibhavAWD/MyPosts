package com.vaibhavdhunde.practice.myposts.data

import com.vaibhavdhunde.practice.myposts.model.Post

interface PostsDataSource {

    suspend fun getPosts(): List<Post>

    suspend fun getPostById(postId: Int): Post
}