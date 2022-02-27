package com.latifah.techbook.database

import androidx.room.Dao
import androidx.room.Insert
import com.latifah.techbook.database.models.User

@Dao
interface TechbookDao {

    @Insert
    suspend fun insertUser(user: User)
}