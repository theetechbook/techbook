package com.latifah.techbook.database.models

class DataSource {
    fun loadEvents(): MutableList<EventsToday> {
        return  mutableListOf<EventsToday>(
            EventsToday(1,"java", "great",12,2/3/22,"online", "img","description"),
            EventsToday(2,"java", "great",12,2/3/22,"online", "img","description"),
            EventsToday(3,"java", "great",12,2/3/22,"online", "img","description"),
            EventsToday(4,"java", "great",12,2/3/22,"online", "img","description"),
            EventsToday(5,"java", "great",12,2/3/22,"online", "img","description"),

        )
    }
}