package com.example.data.cashe.room.common

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.cashe.room.features.home.dao.MostPopularDao
import com.example.data.cashe.room.features.home.entities.MostPopularEntity
import com.example.data.cashe.room.features.signup.dao.UserDao
import com.example.data.cashe.room.features.signup.entities.UserEntity

@Database(
    entities = [UserEntity::class, MostPopularEntity::class],
    version = RoomConstants.DATABASE_VERSION
)
abstract class DatabaseRoom : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun mostPopularDao(): MostPopularDao
}