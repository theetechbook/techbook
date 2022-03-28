package com.latifah.techbook.network

data class Entity(
    val category: String?,
    val entity_id: String?,
    val formatted_address: String?,
    val labels: List<String>??,
    val name: String?,
    val type: String?
)