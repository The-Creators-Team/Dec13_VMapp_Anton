package com.creators.dec13_vm_app_anton.data.repository

import com.creators.dec13_vm_app_anton.data.api.ApiService
import com.creators.dec13_vm_app_anton.data.model.People
import com.creators.dec13_vm_app_anton.data.model.Person
import com.creators.dec13_vm_app_anton.data.model.Room
import javax.inject.Inject

class Repository @Inject constructor (private val apiService: ApiService){
    suspend fun getPeople(): People {
        return apiService.getPeople()
    }

    suspend fun getPerson(id: String): Person {
        return apiService.getPerson(id)
    }

    suspend fun getRooms(): List<Room> {
        return apiService.getRooms()
    }
}