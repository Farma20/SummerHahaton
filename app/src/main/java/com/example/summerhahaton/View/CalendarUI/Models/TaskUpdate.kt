package com.example.summerhahaton.View.CalendarUI.Models


import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class TaskUpdate(
    @SerializedName("begin")
    val begin: LocalDate,
    @SerializedName("end")
    val end: LocalDate,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    var status: String,
    @SerializedName("value")
    val value: String
)