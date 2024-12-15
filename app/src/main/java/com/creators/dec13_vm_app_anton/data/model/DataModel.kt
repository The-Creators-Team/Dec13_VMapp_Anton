package com.creators.dec13_vm_app_anton.data.model


import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("body")
    val body: String? = "",
    @SerializedName("fromId")
    val fromId: String? = "",
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("meetingid")
    val meetingid: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("toId")
    val toId: String? = ""
)