package com.mvifun.model

import com.mvifun.database.PostEntity

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    val favourite: Boolean,
)

fun Post.toPostEntity(): PostEntity {
    return PostEntity(
        userId = userId,
        id = id, title = title, body = body, favourite = favourite
    )
}
