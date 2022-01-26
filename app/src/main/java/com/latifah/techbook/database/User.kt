package com.latifah.techbook.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    val userid : String
    ) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
