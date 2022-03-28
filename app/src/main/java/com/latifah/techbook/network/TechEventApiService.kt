package com.latifah.techbook.network

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TechEventApiService {

    @Headers("Authorization: Bearer M9-7hvtuRKaot_nq4FET39y0xQGDlU4eb59TTUUZ")
    @GET("/v1/events/")
    suspend fun getEvents() : List<TechEvent>

}