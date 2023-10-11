package com.example.data.network.repository.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.network.paging.GenericPagingSource
import com.example.domain.model.movie.Movie
import com.example.domain.repository.movie.MovieRemoteDataRepository
import com.example.domain.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataRepository
) : MovieRepository {
    override suspend fun getMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 2),
            pagingSourceFactory = {
                GenericPagingSource(remoteDataSource){
                        page ->

                    val response = remoteDataSource.getMovies(page)
                    response ?: emptyList()
                }
            }
        ).flow
    }
}