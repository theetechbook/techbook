package com.latifah.techbook.network

data class PlaceResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<Place>
)