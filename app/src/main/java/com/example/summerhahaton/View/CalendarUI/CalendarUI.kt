package com.example.summerhahaton.View.CalendarUI

import android.annotation.SuppressLint
import android.content.ClipData.Item
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.summerhahaton.View.CalendarUI.Models.CalendarDataUpdateClass
import com.example.summerhahaton.View.CalendarUI.UIComponents.AdminDialog
import com.example.summerhahaton.View.CalendarUI.UIComponents.Calendar
import com.example.summerhahaton.View.CalendarUI.UIComponents.WorkCard
import com.example.summerhahaton.View.CalendarUI.ViewModel.CalendarViewModel
import com.example.summerhahaton.ui.theme.MainBlue
import java.time.LocalDate


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarUI(navController: NavController, calendarViewModel: CalendarViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val selectDate = remember { mutableStateOf(LocalDate.now()) }

        val openDialog = remember { mutableStateOf(false) }
        if (openDialog.value)
            AdminDialog(openDialog, calendarViewModel)

        Calendar(selectDate, calendarViewModel)
        println(selectDate.value)
        Surface(
            shape = RoundedCornerShape(24.dp, 24.dp),
            color = Color(0xFFe6f2ff),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
                .padding(horizontal = 0.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ){
                FloatingActionButton(
                    modifier = Modifier.padding(bottom = 20.dp, end = 20.dp),
                    onClick = { openDialog.value = !openDialog.value },
                    containerColor = MainBlue,
                    contentColor = Color.White,
                    content = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Добавить"
                        )
                    }
                )
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .padding(start = 28.dp)
                ) {
                    Text(
                        text = "Смены сотрудников:",
                        fontSize = 18.sp,
                    )
                }
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                ){
                    val shifts = selectEvents(calendarViewModel.allCalendarData, selectDate.value!!)
                    if (shifts == null){
                        item(){
                            Text(modifier = Modifier.padding(top = 150.dp),
                                text = "Сегодня смен нет")
                        }
                    }else{
                        items(shifts.size){
                            Spacer(modifier = Modifier.height(8.dp))
                            WorkCard(navController, shifts[it], calendarViewModel)
                        }
                        item(){
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }

}

fun selectEvents(allEvents: CalendarDataUpdateClass, day:LocalDate):CalendarDataUpdateClass?{
    if (allEvents.size == 0)
        return null


    val dayEventList = CalendarDataUpdateClass()
    for(item in allEvents){
        if (item.date == day)
            dayEventList.add(item)
    }
    if (dayEventList.isEmpty())
        return null

    return dayEventList
}


