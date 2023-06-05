package com.example.summerhahaton.Data.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.summerhahaton.Data.Models.CalendarDataClass
import com.example.summerhahaton.Data.Models.PushNewTaskDataModel
import com.example.summerhahaton.Data.Models.UsersListDataModel
import com.example.summerhahaton.Data.Server.HAHATOnRetrofit
import kotlinx.coroutines.launch

object MainRepository: ViewModel() {

    val calendarData = MutableLiveData<CalendarDataClass>()

    fun getCalendarData(){
        viewModelScope.launch {
            try {
                calendarData.postValue(HAHATOnRetrofit.retrofitService.getCalendarData())
                println(calendarData.value)
            }catch (e:Exception){
                println(e)
            }
        }
    }

    fun taskConform(taskId:Int){
        viewModelScope.launch {
            try {
                HAHATOnRetrofit.retrofitService.conformTask(mapOf("task_id" to taskId))
                getCalendarData()
            }catch (e:Exception){
                println(e)
            }
        }
    }

    val usersList = MutableLiveData<UsersListDataModel>()
    fun getAllUserDataFromServer(){
        viewModelScope.launch {
            try {
                usersList.postValue(HAHATOnRetrofit.retrofitService.getAllUser())
                println(usersList.value)
            }catch (e:Exception){
                println(e)
            }
        }
    }

    fun pushNewTaskOnServer(task:PushNewTaskDataModel){
        viewModelScope.launch {
            try {
                HAHATOnRetrofit.retrofitService.addNewTask(task)
                getCalendarData()
            }catch (e:Exception){
                println(e)
            }
        }
    }

    init {
        getAllUserDataFromServer()
        getCalendarData()
    }
}
