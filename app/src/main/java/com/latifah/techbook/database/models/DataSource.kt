package com.latifah.techbook.database.models

class DataSource {
    fun loadEvents(): MutableList<EventsToday> {
        return  mutableListOf<EventsToday>(
            EventsToday("java", "great"),
            EventsToday("Kotlin Expo", "New York"),
            EventsToday("Python", "Atlanta"),
            EventsToday("JavaScript", "Georgia"),
            EventsToday("PHP", "California")

        )
    }
}