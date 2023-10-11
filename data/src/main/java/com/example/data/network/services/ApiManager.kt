package com.example.data.network.services

import com.example.data.network.api.ApiRequests
import com.example.data.network.common.NetworkResult
import com.example.data.network.extensions.getModel
import com.example.data.network.extensions.map
import com.example.data.network.extensions.safeApiCall
import com.example.data.network.extensions.safeApiCallList
import com.example.navigationtutorial.model.MoviesListResponse
import javax.inject.Inject

class ApiManager @Inject constructor(val services: ApiRequests) {
    suspend inline fun <reified T> getRequest(
        url: String,
        headersMap: Map<String, String>? = mapOf(),
        queryParamsMap: Map<String, Any?>? = mapOf()
    ): NetworkResult<T> =
        safeApiCall {
            services.getRequest(
                url = url,
                headersMap = headersMap,
                queryParamsMap = queryParamsMap
            )
        }.map { response ->
            response?.body()?.getModel()
        }

    suspend inline fun <reified T> getMovies(
        url: String,
    ): NetworkResult<List<MoviesListResponse.Result>> =
        safeApiCallList {
            services.getMovies(
                url = url
            )
        }.map { response ->
            response?.body()?.results
        }

}