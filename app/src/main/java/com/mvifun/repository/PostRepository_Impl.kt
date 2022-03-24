package com.mvifun.repository

import androidx.room.withTransaction
import com.mvifun.model.Post
import com.mvifun.model.toPostEntity
import com.mvifun.network.dto.ApiService
import com.mvifun.network.dto.toPost
import com.mvifun.database.PostDatabase
import com.mvifun.database.toPost
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PostRepository_Impl(
    private val apiService: ApiService,
    private val postDatabase: PostDatabase
) : PostRepository {

    private val postDao = postDatabase.dao()

    override suspend fun getPostsNetworkList(): List<Post> {
        return apiService.getPosts().map { it.toPost() }
    }
    override fun getPostsDatabaseFlow(): Flow<List<Post>> {
        return postDao.getPostsFlow().map { it.map { it.toPost() } }
    }
    override suspend fun getPostById(id: Int): Post {
        return postDao.getPostsById(id).toPost()
    }
    override suspend fun insertPost(post: Post) {
        return postDao.insertPost(post.toPostEntity())
    }
    override suspend fun insertPosts(list: List<Post>) {
        return postDao.insertPosts(list.map { it.toPostEntity() })
    }
    override suspend fun deletePosts() {
        return postDao.deletePosts()
    }
    override suspend fun deletePostById(id: Int) {
        return postDao. deletePostById(id)
    }
    override suspend fun updatePost(post: Post) {
        postDao.updatePost(post.toPostEntity())
    }
    override suspend fun deleteFromThenInsertIntoDatabase(posts: List<Post>)
    {
       postDatabase.withTransaction {
           postDao.deletePosts()
           postDao.insertPosts(posts.map { it.toPostEntity() })
       }
    }
}