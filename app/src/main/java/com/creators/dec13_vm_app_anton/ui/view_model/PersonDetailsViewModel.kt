package com.creators.dec13_vm_app_anton.ui.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creators.dec13_vm_app_anton.data.model.People
import com.creators.dec13_vm_app_anton.data.model.Person
import com.creators.dec13_vm_app_anton.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonDetailsViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val TAG = "PersonList ViewModel"

    private val _personDetailsData = MutableLiveData<Person>()
    val personDetailsData: LiveData<Person> get() = _personDetailsData

    fun getPersonDetailsData(id: String) {
        viewModelScope.launch {
            try {
                val response = repository.getPerson(id)
                _personDetailsData.postValue(response)
            } catch(e: Exception) {
                Log.d(TAG, "Exception: $e")
            }
        }
    }
}