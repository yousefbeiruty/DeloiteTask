package com.example.data.cashe.room.features.home.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.data.cashe.room.common.BaseDao
import com.example.data.cashe.room.features.home.entities.MostPopularEntity

@Dao
interface MostPopularDao : BaseDao<MostPopularEntity> {

    @Query("SELECT * FROM MostPopularEntity")
    fun getMostPopular(): List<MostPopularEntity>

    @Query("SELECT * FROM MostPopularEntity WHERE title LIKE '%' || :searchQuery || '%'")
    fun getFilteredMostPopular(searchQuery : String): List<MostPopularEntity>

}
