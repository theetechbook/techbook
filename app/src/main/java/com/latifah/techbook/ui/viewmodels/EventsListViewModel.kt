package com.latifah.techbook.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.latifah.techbook.network.Event
import com.latifah.techbook.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsListViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    val eventsList: MutableLiveData<List<Event?>?> = MutableLiveData()

    fun getEvents(): LiveData<List<Event?>?> {
        viewModelScope.launch {
            eventsList.value = mainRepository.getEvents().results
        }
        return eventsList
    }
}