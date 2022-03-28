package com.latifah.techbook.network

data class EventsResponse(
    val count: Int?,
    val next: String?,
    val overflow: Boolean?,
    val previous: Any?,
    val results: List<Event?>?
)