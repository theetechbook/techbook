package com.latifah.techbook.database

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.latifah.techbook.database.models.User

@Dao
interface TechbookDao {

    @Insert
    suspend fun insertLikedPost(likedPost: LikedPost)

    @Query("SELECT * FROM liked_posts")
    suspend fun getLikedPosts(): MutableLiveData<ArrayList<LikedPost>>



}