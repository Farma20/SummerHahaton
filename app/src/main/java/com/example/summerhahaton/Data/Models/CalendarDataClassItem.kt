package com.example.summerhahaton.Data.Models


import com.google.gson.annotations.SerializedName

data class CalendarDataClassItem(
    @SerializedName("date")
    val date: String,
    @SerializedName("shift_id")
    val shiftId: Int,
    @SerializedName("shift_time")
    val shiftTime: Int,
    @SerializedName("tasks")
    val tasks: List<Task>,
    @SerializedName("user_name")
    val userName: String
)