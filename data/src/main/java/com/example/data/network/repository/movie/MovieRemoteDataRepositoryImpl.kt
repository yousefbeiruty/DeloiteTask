package com.example.data.network.repository.movie

import com.example.data.network.extensions.map
import com.example.data.network.extensions.toMovie
import com.example.data.network.movie.MovieService
import com.example.domain.model.movie.Movie
import com.example.domain.repository.movie.MovieRemoteDataRepository
import com.example.navigationtutorial.model.MoviesListResponse
import javax.inject.Inject

class MovieRemoteDataRepositoryImpl @Inject constructor(
    private val api: MovieService
) : MovieRemoteDataRepository {
    override suspend fun getMovies(pageNumber: Int): List<Movie>? {
         val data=api.getMostPopular("",pageNumber,"popularity.desc")
         println("API Response: $data")
         val list= ArrayList<Movie>()
        data.map {
          return  it?.toMovie()
        }
        return list
    }

}