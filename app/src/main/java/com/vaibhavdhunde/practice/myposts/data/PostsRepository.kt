package com.vaibhavdhunde.practice.myposts.data

import com.vaibhavdhunde.practice.myposts.api.PostsApi
import com.vaibhavdhunde.practice.myposts.api.SafeApiRequest
import com.vaibhavdhunde.practice.myposts.model.Post

class PostsRepository(
    private val api: PostsApi
) : PostsDataSource {

    override suspend fun getPosts(): List<Post> {
        return SafeApiRequest.apiRequest { api.getPosts() }
    }

    override suspend fun getPostById(postId: Int): Post {
        return SafeApiRequest.apiRequest { api.getPostById(postId) }
    }
}