package com.example.summerhahaton.Data.Server

import com.example.summerhahaton.Data.Models.CalendarDataClass
import com.example.summerhahaton.Data.Models.PushNewTaskDataModel
import com.example.summerhahaton.Data.Models.UsersListDataModel
import retrofit2.http.Body
import retrofit2.http.POST

interface  API {
    @POST("hack23/shift/view")
    suspend fun getCalendarData():CalendarDataClass

    @POST("hack23/task/confirm")
    suspend fun conformTask(@Body task_id:Map<String, Int>)

    @POST("/hack23/user/view")
    suspend fun getAllUser(): UsersListDataModel

    @POST("hack23/task/add")
    suspend fun addNewTask(@Body task:PushNewTaskDataModel)
}