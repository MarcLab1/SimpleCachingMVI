package com.mvifun.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao{

    @Query("SELECT * FROM posts")
    suspend fun getPostsList() : List<PostEntity>

    @Query("SELECT * FROM posts")
    fun getPostsFlow() : Flow<List<PostEntity>>

    @Query("SELECT * FROM posts WHERE id=:id")
    suspend fun getPostsById(id: Int) : PostEntity

    @Insert(onConflict = REPLACE)
    suspend fun insertPost(postEntity: PostEntity)

    @Insert(onConflict = REPLACE)
    suspend fun insertPosts(list: List<PostEntity>)

    @Update()
    suspend fun updatePost(postEntity: PostEntity)

    @Delete
    suspend fun deletePost(postEntity: PostEntity)

    @Query("DELETE FROM posts")
    suspend fun deletePosts()

    @Query("DELETE FROM posts WHERE id=:id")
    suspend fun deletePostById(id: Int)
}