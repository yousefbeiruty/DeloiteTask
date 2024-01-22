package com.example.domain.usecase.movie

import android.security.identity.ResultData
import androidx.paging.PagingData
import com.example.domain.model.movie.Movie
import com.example.domain.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieFakeRepoImpl : MovieRepository{


    override suspend fun getMovies(): Flow<PagingData<Movie>> {
        return flow {

        }
    }
}