package com.vaibhavdhunde.practice.myposts.data

import com.vaibhavdhunde.practice.myposts.model.Post
import com.vaibhavdhunde.practice.myposts.model.PostsResponse

interface PostsDataSource {

    suspend fun getPosts(): PostsResponse

    suspend fun getPostById(postId: Int): Post
}