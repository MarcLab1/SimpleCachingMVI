package com.mvifun.repository

import com.mvifun.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    fun getPostsDatabaseFlow(): Flow<List<Post>>
    suspend fun getPostsNetworkList(): List<Post>
    suspend fun getPostById(id: Int): Post

    suspend fun insertPost(post: Post): Unit
    suspend fun insertPosts(list: List<Post>)
    suspend fun deletePostById(id: Int): Unit
    suspend fun deletePosts(): Unit
    suspend fun updatePost(post: Post): Unit
    suspend fun deleteFromThenInsertIntoDatabase(posts: List<Post>)
}