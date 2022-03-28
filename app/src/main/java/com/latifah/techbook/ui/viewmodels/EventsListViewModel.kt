package com.latifah.techbook.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.latifah.techbook.network.Event
import com.latifah.techbook.network.Place
import com.latifah.techbook.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsListViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    val _eventsList: MutableLiveData<MutableList<Event?>?> = MutableLiveData()
    var eventsList : LiveData<MutableList<Event?>?> = _eventsList
    val eventSearchLocation: MutableLiveData<Place?> = MutableLiveData()
    private var location: String = ""
    var locationLiveData: MutableLiveData<String> = MutableLiveData(location)

    fun getEvents(location: String) {
        viewModelScope.launch {
            Log.d("getEvents location parameter", location)
            _eventsList.value = mainRepository.getEvents(location).results as MutableList<Event?>?
            Log.d("eventsList is now", "${_eventsList.value}")
        }

    }

    fun checkEventsList() {
        Log.d("checking eventsList", "${_eventsList.value }")
        if (_eventsList.value == null)
        eventsList = _eventsList
    }

    fun getPlace(place: String): LiveData<Place?> {
        viewModelScope.launch {
            eventSearchLocation.value = mainRepository.getPlace(place).results[0]
        }
        return eventSearchLocation
    }

    fun setLocation(userEntry: String): MutableLiveData<String> {
        Log.d("userEntry is ", userEntry)
        location = userEntry
        Log.d("location is ", location)
        locationLiveData.value = location
        return locationLiveData
    }

}