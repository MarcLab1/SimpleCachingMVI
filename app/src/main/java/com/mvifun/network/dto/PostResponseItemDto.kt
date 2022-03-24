package com.mvifun.network.dto

import com.mvifun.model.Post
import com.mvifun.database.PostEntity

data class PostResponseItem(
    val userId: Int?,
    val id: Int?,
    val title: String?,
    val body: String?,
    val favourite : Boolean = false
)

fun PostResponseItem.toPost() : Post
{
    return Post(
        userId = userId ?: 0,
        id = id ?: 0,
        title = title ?: "",
        body = body ?: "",
        favourite = favourite
    )
}

fun PostResponseItem.toPostEntity() : PostEntity
{
    return PostEntity(
        userId = userId ?: 0,
        id = id ?: 0,
        title = title ?: "",
        body = body ?: "",
        favourite = favourite
    )
}