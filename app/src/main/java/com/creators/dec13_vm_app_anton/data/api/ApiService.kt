package com.creators.dec13_vm_app_anton.data.api

import com.creators.dec13_vm_app_anton.data.model.People
import com.creators.dec13_vm_app_anton.data.model.Person
import com.creators.dec13_vm_app_anton.data.model.Room
import com.creators.dec13_vm_app_anton.data.model.RoomResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("people")
    suspend fun getPeople(): People

    @GET("people/{id}")
    suspend fun getPerson(@Path("id") id: String): Person

    @GET("rooms")
    suspend fun getRooms(): RoomResponse
}