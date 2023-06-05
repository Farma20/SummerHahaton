package com.example.summerhahaton.Data.Server

import com.example.summerhahaton.Data.Models.CalendarDataClass
import retrofit2.http.POST

interface  API {
    @POST("hack23/shift/view")
    suspend fun getCalendarData():CalendarDataClass
}