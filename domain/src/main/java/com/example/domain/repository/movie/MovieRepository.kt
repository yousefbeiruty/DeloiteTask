package com.example.domain.repository.movie

import androidx.paging.PagingData
import com.example.domain.model.movie.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(): Flow<PagingData<Movie>>
}