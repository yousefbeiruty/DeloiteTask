package com.example.data.network.home

import com.example.data.network.common.NetworkResult
import com.example.data.network.home.model.MostPopularResponse
import com.example.data.network.services.ApiManager
import javax.inject.Inject

class HomeService @Inject constructor(private val apiManager: ApiManager) {

    companion object {

        private const val API_KEY = "api-key"
        private const val PATH_EVENT_DETAILS =
            "svc/mostpopular/v2/viewed/30.json"
    }

    suspend fun getMostPopular(key: String): NetworkResult<MostPopularResponse> {
        return apiManager.getRequest(
            PATH_EVENT_DETAILS,
            queryParamsMap = mapOf(
                API_KEY to key
            )
        )
    }
}