package com.vaibhavdhunde.practice.myposts.api

import com.vaibhavdhunde.practice.myposts.model.Post
import com.vaibhavdhunde.practice.myposts.model.PostsResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsApi {

    @GET("posts")
    suspend fun getPosts(): Response<PostsResponse>

    @GET("posts/{id}")
    suspend fun getPostById(
        @Path("id") postId: Int
    ): Response<Post>

    companion object {

        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        operator fun invoke(networkInterceptor: NetworkInterceptor): PostsApi {

            val client = OkHttpClient.Builder()
                .addInterceptor(networkInterceptor)
                .build()

            return Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PostsApi::class.java)
        }

    }
}