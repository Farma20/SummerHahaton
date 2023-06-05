package com.example.summerhahaton.Data.Models


import com.google.gson.annotations.SerializedName

data class Task(
    @SerializedName("begin")
    val begin: String,
    @SerializedName("end")
    val end: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("value")
    val value: String
)