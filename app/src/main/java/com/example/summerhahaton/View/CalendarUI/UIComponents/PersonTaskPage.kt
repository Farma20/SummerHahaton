package com.example.summerhahaton.View.CalendarUI.UIComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.summerhahaton.View.CalendarUI.Models.TaskUpdate
import com.example.summerhahaton.View.CalendarUI.ViewModel.CalendarViewModel
import com.example.summerhahaton.ui.theme.MainBlue

@Composable
fun PersonTaskPage(calendarViewModel: CalendarViewModel) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MainBlue)
        ){
            WorkCard(null, calendarViewModel.selectedShift, calendarViewModel)
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            items(calendarViewModel.selectedShift!!.taskUpdates.size){
                TaskCard(calendarViewModel.selectedShift!!.taskUpdates[it])
            }
        }
    }
}

@Composable
fun TaskCard(task: TaskUpdate){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .padding(horizontal = 9.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp,
        ),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = task.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,

            )
            Text(
                modifier = Modifier.padding(vertical = 9.dp),
                text = task.value,
                fontSize = 14.sp
            )
            Button(
                modifier = Modifier.padding(top = 4.dp),
                onClick = {}
            ) {
                Text(text = "Отметить выполнение")
            }

        }
    }
}