package com.mvifun.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mvifun.model.Post

@Entity(tableName = "posts")
data class PostEntity(
    val userId: Int,

    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,

    val title: String,
    val body : String,
    val favourite : Boolean = false
)

fun PostEntity.toPost(): Post {
    return Post(
        userId = userId,
        id = id, title = title, body = body, favourite = favourite
    )
}


