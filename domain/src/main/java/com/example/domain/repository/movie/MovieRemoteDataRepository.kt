package com.example.domain.repository.movie

import com.example.domain.model.movie.Movie

interface MovieRemoteDataRepository {
    suspend fun getMovies( pageNumber: Int): List<Movie>?
}