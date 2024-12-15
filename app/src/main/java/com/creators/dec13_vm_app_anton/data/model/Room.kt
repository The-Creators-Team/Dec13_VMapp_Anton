package com.creators.dec13_vm_app_anton.data.model

data class Room (
    val createdAt: String,
    val isOccupied: Boolean,
    val maxOccupancy: Long,
    val id: Int
)
