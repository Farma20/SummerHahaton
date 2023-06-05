package com.example.summerhahaton.View.CalendarUI.Models


import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class CalendarDataUpdateClassItem(
    @SerializedName("date")
    val date: LocalDate,
    @SerializedName("shift_id")
    val shiftId: Int,
    @SerializedName("shift_time")
    val shiftTime: Int,
    @SerializedName("tasks")
    val taskUpdates: List<TaskUpdate>,
    @SerializedName("user_name")
    val userName: String
)