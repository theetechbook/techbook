package com.latifah.techbook.network

data class Event(
    val aviation_rank: Int?,
    val brand_safe: Boolean?,
    val category: String?,
    val country: String?,
    val description: String?,
    val duration: Int?,
    val end: String?,
    val entities: List<Entity?>?,
    val first_seen: String,
    val geo: Geo,
    val id: String,
    val labels: List<String>,
    val local_rank: Int,
    val location: List<Double>,
    val phq_attendance: Int,
    val place_hierarchies: List<List<String>>,
    val `private`: Boolean,
    val rank: Int,
    val relevance: Int,
    val scope: String,
    val start: String,
    val state: String,
    val timezone: String,
    val title: String,
    val updated: String
)