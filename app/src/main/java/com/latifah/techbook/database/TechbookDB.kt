package com.latifah.techbook.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [LikedPost::class], version = 1)
abstract class TechbookDB : RoomDatabase() {
    abstract fun getTechbookDao() : TechbookDao
}