package com.latifah.techbook.network

data class Place(
    val country: String,
    val country_alpha2: String,
    val country_alpha3: String,
    val county: Any,
    val id: String,
    val location: List<Double>,
    val name: String,
    val region: String,
    val type: String
)