package com.example.summerhahaton.View.CalendarUI.UIComponents

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.summerhahaton.Data.Models.PushNewTaskDataModel
import com.example.summerhahaton.Data.Repository.MainRepository
import com.example.summerhahaton.Domain.LoadingAnimation
import com.example.summerhahaton.R
import com.example.summerhahaton.View.CalendarUI.ViewModel.CalendarViewModel
import com.example.summerhahaton.ui.theme.MainBlue
import java.time.LocalDate
import java.time.format.DateTimeFormatter



@Composable
fun AdminDialog(openDialog: MutableState<Boolean>, calendarViewModel:CalendarViewModel) {
    val context = LocalContext.current

    var newTask = remember {
        mutableStateOf(PushNewTaskDataModel())
    }

    Dialog(onDismissRequest = { openDialog.value = false }) {
        Card(
            modifier = Modifier
                .padding(vertical = 30.dp)
                .clip(
                    RoundedCornerShape(7.dp)
                ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 3.dp
            ),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
                    .padding(top = 0.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val spacerPadding = 20.dp

                Spacer(modifier = Modifier.height(spacerPadding))
                RegistrationSecondName(newTask, calendarViewModel)
                Spacer(modifier = Modifier.height(spacerPadding))
                RegistrationBirthday(context,newTask, calendarViewModel)
                Spacer(modifier = Modifier.height(spacerPadding))
                RegistrationEnd(context,newTask, calendarViewModel)
                Spacer(modifier = Modifier.height(spacerPadding))
                RegistrationTaskName(newTask, calendarViewModel)
                Spacer(modifier = Modifier.height(spacerPadding))
                RegistrationTaskText(newTask, calendarViewModel)
                Spacer(modifier = Modifier.height(spacerPadding))
                RegistrationSubmitButton(newTask, calendarViewModel)
                Spacer(modifier = Modifier.height(spacerPadding))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RegistrationSecondName(newTask: MutableState<PushNewTaskDataModel>, calendarViewModel: CalendarViewModel){
    var name by remember {
        mutableStateOf(" ")
    }
    var expe by remember {
        mutableStateOf(false)
    }

    Column(){
        TextField(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .onFocusChanged { expe = it.isFocused },
            value = name,
            onValueChange = {
               name = it
            },
            isError = false,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            label = { Text(text = "Сотрудник")},
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
            ),
        )

        DropdownMenu(
            expanded = expe,
            onDismissRequest = { expe = false },
            modifier = Modifier.fillMaxWidth(0.6f)
        ) {
            calendarViewModel.allUsers.forEach { user ->
                DropdownMenuItem(onClick = {
                    newTask.value.userId = user.userId
                    name = user.userName
                    expe = false
                },
                text = { Text(text = "${user.userName}")})
            }
        }
    }
}

@Composable
private fun RegistrationBirthday(context: Context, newTask: MutableState<PushNewTaskDataModel>, calendarViewModel: CalendarViewModel){
    val date = LocalDate.now()
    val year:Int = date.year
    val month: Int = date.month.value-1
    val day:Int = date.dayOfMonth

    var curDateName by remember {
        mutableStateOf("")
    }

    val datePickerDialog = DatePickerDialog(context,
        { _: DatePicker, year: Int, month: Int, day: Int ->
            newTask.value.begin = LocalDate.of(year, month+1, day).toString()
            curDateName = newTask.value.begin
        },year, month, day
    )

    Column() {
        Row(
            modifier = Modifier.fillMaxWidth(0.9f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Дата начала",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light
            )
            Card(
                modifier = Modifier.fillMaxWidth(0.9f),
                elevation = CardDefaults.cardElevation(3.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                TextButton(
                    modifier = Modifier
                        .width(200.dp)
                        .height(32.dp),
                    onClick = {
                        datePickerDialog.show()
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MainBlue
                    )
                ) {
                        if (curDateName == ""){
                            Text(
                                text =  "Выбрать",
                                fontSize = 12.sp
                            )
                        }else{
                            Text(text = newTask.value.begin)
                        }
                    }

                }
            }
        }
    }

@Composable
private fun RegistrationEnd(context: Context, newTask: MutableState<PushNewTaskDataModel>, calendarViewModel: CalendarViewModel){
    val date = LocalDate.now()
    val year:Int = date.year
    val month: Int = date.month.value-1
    val day:Int = date.dayOfMonth

    var curDateName by remember {
        mutableStateOf("")
    }

    val datePickerDialog = DatePickerDialog(context,
        { _: DatePicker, year: Int, month: Int, day: Int ->
            newTask.value.end = LocalDate.of(year, month+1, day).toString()
            curDateName = newTask.value.end
        },year, month, day
    )

    Column() {
        Row(
            modifier = Modifier.fillMaxWidth(0.9f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Дата конца  ",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light
            )
            Card(
                modifier = Modifier.fillMaxWidth(0.9f),
                elevation = CardDefaults.cardElevation(3.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                TextButton(
                    modifier = Modifier
                        .width(200.dp)
                        .height(32.dp),
                    onClick = {
                        datePickerDialog.show()
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MainBlue
                    )
                ) {
                    if (curDateName == ""){
                        Text(
                            text =  "Выбрать",
                            fontSize = 12.sp
                        )
                    }else{
                        Text(text = newTask.value.end)
                    }
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RegistrationTaskName(newTask: MutableState<PushNewTaskDataModel>, calendarViewModel: CalendarViewModel){

    var textName by remember {
        mutableStateOf("")
    }

    Column(){
        TextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = textName,
            onValueChange = {
                newTask.value.name = it
                textName = it
            },
            isError = false,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            label = { Text(text = " Название задания")},
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
            ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RegistrationTaskText(newTask: MutableState<PushNewTaskDataModel>, calendarViewModel: CalendarViewModel){
    var textName by remember {
        mutableStateOf("")
    }
    Column(){
        TextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = textName,
            onValueChange = {
                newTask.value.value = it
                textName = it
            },
            isError = false,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            label = { Text(text = "Описание задания")},
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
            ),
        )
    }
}

@Composable
private fun RegistrationSubmitButton(newTask: MutableState<PushNewTaskDataModel>, calendarViewModel: CalendarViewModel){
    var loadingAnimationOn by remember { mutableStateOf(false) }
    LaunchedEffect(""){
        loadingAnimationOn = false
    }
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        onClick = {
            MainRepository.pushNewTaskOnServer(newTask.value)
        },
        colors = ButtonDefaults.textButtonColors(
            containerColor =  MainBlue,
            contentColor = Color.White
        ),
    ) {
        if (!loadingAnimationOn){
            Text(
                text = "Отправить задание"
            )
        }else{
            LoadingAnimation(
                circleSize = 6.dp,
                circleColor = Color.White,
                spaceBetween = 4.dp,
                travelDistance = 6.dp
            )
        }
    }
}