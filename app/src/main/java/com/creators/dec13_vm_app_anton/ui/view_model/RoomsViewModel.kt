package com.creators.dec13_vm_app_anton.ui.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creators.dec13_vm_app_anton.data.model.RoomResponse
import com.creators.dec13_vm_app_anton.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val TAG = "Rooms ViewModel"
    private val _roomsData = MutableLiveData<RoomResponse>()
    val roomsData: LiveData<RoomResponse> get() = _roomsData

    fun getRoomsData() {
        viewModelScope.launch {
            try {
                val response = repository.getRooms()
                _roomsData.postValue(response)
            } catch(e: Exception) {
                Log.d(TAG, "Exception: ${e.toString()}")
            }
        }
    }
}