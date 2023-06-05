package com.example.summerhahaton.Data.Repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.summerhahaton.Data.Models.CalendarDataClass
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

    init {
        getCalendarData()
    }
}
