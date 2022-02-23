package com.latifah.techbook.database.models

class DataSource {
    fun loadEvents(): MutableList<EventsToday> {
        return  mutableListOf<EventsToday>(
            EventsToday(1,"java", "great", 12, 12/2/22,"online","img","description"),
            EventsToday(1,"Kotlin Expo", "New York",12, 12/2/22,"online","img","description"),
            EventsToday(1,"Python", "Atlanta",12, 12/2/22,"online","img","description"),
            EventsToday(1,"JavaScript", "Georgia",12, 12/2/22,"online","img","description"),
            EventsToday(1,"PHP", "California",12, 12/2/22,"online","img","description")

        )
    }
}