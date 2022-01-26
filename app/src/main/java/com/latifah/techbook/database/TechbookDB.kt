package com.latifah.techbook.database

import androidx.room.RoomDatabase


abstract class TechbookDB : RoomDatabase() {
    abstract fun getTechbookDao() : TechbookDao
}