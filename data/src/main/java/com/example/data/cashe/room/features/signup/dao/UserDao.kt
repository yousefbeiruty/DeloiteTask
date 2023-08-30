package com.example.data.cashe.room.features.signup.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.data.cashe.room.common.BaseDao
import com.example.data.cashe.room.features.signup.entities.UserEntity

@Dao
interface UserDao : BaseDao<UserEntity> {

    @Query("SELECT * FROM UserEntity WHERE email = :email")
    fun getUserDataEntity(email: String): UserEntity

    @Query("SELECT * FROM UserEntity WHERE email = :email AND password = :password")
    fun login(email: String, password: String): UserEntity
}