package com.example.summerhahaton.View.CalendarUI.ViewModel

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.summerhahaton.Data.Models.CalendarDataClass
import com.example.summerhahaton.Data.Repository.MainRepository
import com.example.summerhahaton.View.CalendarUI.Models.CalendarDataUpdateClass
import com.example.summerhahaton.View.CalendarUI.Models.CalendarDataUpdateClassItem
import com.example.summerhahaton.View.CalendarUI.Models.TaskUpdate
import java.time.LocalDate

@SuppressLint("MutableCollectionMutableState")
class CalendarViewModel:ViewModel() {

    var allCalendarData by mutableStateOf(CalendarDataUpdateClass())
    var allShiftsDate by mutableStateOf<List<LocalDate>>(listOf())
    var selectedShift by mutableStateOf<CalendarDataUpdateClassItem?>(null)

    init {
        MainRepository.calendarData.observeForever(){
            allCalendarData = convertToDateTime(data = it)
            allShiftsDate = getAllDate(allCalendarData)
        }

    }
}

private fun getAllDate(allCalendarData:CalendarDataUpdateClass):List<LocalDate>{
    val list = mutableListOf<LocalDate>()
    if (allCalendarData.isEmpty())
        return list
    for (item in allCalendarData){
            list.add(item.date)
    }
    return list
}


private fun convertToDateTime(data: CalendarDataClass):CalendarDataUpdateClass{
    var updateData: CalendarDataUpdateClass = CalendarDataUpdateClass()

    for (shift in data){

        var updateTask = mutableListOf<TaskUpdate>()
        for (task in shift.tasks){
            val startDateTime:List<String> = task.begin.split("T".toRegex())
            val endDateTime: List<String> = task.end.split("T".toRegex())
            val dateListStart = startDateTime[0].split("-")
            val dateListEnd = endDateTime[0].split("-")
            val dateStart = LocalDate.of(dateListStart[0].toInt(), dateListStart[1].toInt(), dateListStart[2].toInt())
            val dateEnd = LocalDate.of(dateListEnd[0].toInt(), dateListEnd[1].toInt(), dateListEnd[2].toInt())

            val updateTasks = TaskUpdate(
                begin = dateStart,
                end = dateEnd,
                id = task.id,
                name = task.name,
                status = task.status,
                value = task.value
            )
            updateTask.add(updateTasks)
        }

        val startDateTime:List<String> = shift.date.split("T".toRegex())
        val dateListStart = startDateTime[0].split("-")
        val dateStart = LocalDate.of(dateListStart[0].toInt(), dateListStart[1].toInt(), dateListStart[2].toInt())
        var updateShift = CalendarDataUpdateClassItem(
            date = dateStart,
            shiftId = shift.shiftId,
            shiftTime = shift.shiftTime,
            taskUpdates = updateTask,
            userName = shift.userName
        )
        updateData.add(updateShift)
    }
    return updateData
}