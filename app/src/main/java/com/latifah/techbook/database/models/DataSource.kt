package com.latifah.techbook.database.models

class DataSource {
    fun loadEvents(): MutableList<EventsToday> {
        return  mutableListOf<EventsToday>(
            EventsToday(1,"java", "great"),
            EventsToday(1,"Kotlin Expo", "New York"),
            EventsToday(1,"Python", "Atlanta"),
            EventsToday(1,"JavaScript", "Georgia"),
            EventsToday(1,"PHP", "California")

        )
    }
}