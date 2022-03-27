package com.latifah.techbook.network

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface TechEventApiService {

    @GET("/v1/events/")
    suspend fun getEvents() : Flow<TechEvent>

}