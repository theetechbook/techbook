package com.latifah.techbook.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "liked_posts")
data class LikedPost(
    val userUid : String = "",
    val text : String = "",
    val gif_url : String = "",
    val username : String = "",
    ) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
