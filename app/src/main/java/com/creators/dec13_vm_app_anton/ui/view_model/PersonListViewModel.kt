package com.creators.dec13_vm_app_anton.ui.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creators.dec13_vm_app_anton.data.model.People
import com.creators.dec13_vm_app_anton.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonListViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val TAG = "PersonList ViewModel"

    private val _personListData = MutableLiveData<People>()
    val personListData: LiveData<People> get() = _personListData

    fun getPersonListData() {
        viewModelScope.launch {
            try {
                val response = repository.getPeople()
                _personListData.postValue(response)
            } catch(e: Exception) {
                Log.d(TAG, "Exception: ${e.toString()}")
            }
        }
    }
}