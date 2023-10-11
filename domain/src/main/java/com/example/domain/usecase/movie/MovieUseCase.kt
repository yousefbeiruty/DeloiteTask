package com.example.domain.usecase.movie

import androidx.paging.PagingData
import com.example.domain.common.BaseUseCase
import com.example.domain.model.movie.Movie
import com.example.domain.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieUseCase  @Inject constructor(
    private val repository: MovieRepository
) : BaseUseCase<Unit, Flow<PagingData<Movie>>> {
    override suspend fun invoke(params: Unit?): Flow<PagingData<Movie>> {
        return repository.getMovies()
    }
}