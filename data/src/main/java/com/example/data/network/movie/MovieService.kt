package com.example.data.network.movie

import com.example.data.network.common.NetworkResult
import com.example.data.network.services.ApiManager
import com.example.navigationtutorial.model.MoviesListResponse
import javax.inject.Inject


class MovieService @Inject constructor(private val apiManager: ApiManager) {
    companion object {
        private const val KEY = "9e851da311d976ec3754a43b0185bc8c"
        private const val API_KEY = "api_key"
        const val SERVER_URL = "https://api.themoviedb.org/3/"
        private const val PATH_EVENT_DETAILS = "discover/movie"
    }

    suspend fun getMostPopular(key: String,page:Int,sortBy:String): NetworkResult<List<MoviesListResponse.Result>> {
        return apiManager.getMovies<MoviesListResponse>(
         url = "$SERVER_URL$PATH_EVENT_DETAILS?$API_KEY=$KEY&page=$page&sort_by=$sortBy"
        )
    }


}
