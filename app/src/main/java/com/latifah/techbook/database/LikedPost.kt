package com.latifah.techbook.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "liked_posts")
data class LikedPost(
    val userId : String,
    val firstName : String,
    val lastName : String,
    val userName : String
    ) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
