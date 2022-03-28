package com.latifah.techbook.network

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface TechEventApiService {

    @Headers(
        "Authorization: Bearer M9-7hvtuRKaot_nq4FET39y0xQGDlU4eb59TTUUZ",
        "Accept: application/json"
    )
    @GET("/v1/events/")
    suspend fun getEvents(
        @Query("category") category: String = "conferences,expos",
        @Query("label") label: String = "technology",
        @Query("place.scope") placeID: String = "5128638"
    ) : EventsResponse

    @Headers(
        "Authorization: Bearer M9-7hvtuRKaot_nq4FET39y0xQGDlU4eb59TTUUZ",
        "Accept: application/json"
    )
    @GET("/v1/places/")
    suspend fun getPlace(
        @Query("q") place: String = "New York",
        @Query("limit") limit: Int = 10
    ) : PlaceResponse
}