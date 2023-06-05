package com.example.summerhahaton.View.CalendarUI.UIComponents

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.summerhahaton.R
import com.example.summerhahaton.View.CalendarUI.ViewModel.CalendarViewModel
import com.example.summerhahaton.ui.theme.LightGreen
import com.example.summerhahaton.ui.theme.MainBlue
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

private val dayOfWeek = listOf("ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ", "ВС")
private val convertNumInMonth = mapOf<Int, String>(
    1 to "Январь",
    2 to "Фервраль",
    3 to "Март",
    4 to "Апрель",
    5 to "Май",
    6 to "Июнь",
    7 to "Июль",
    8 to "Август",
    9 to "Сентябрь",
    10 to "Октябрь",
    11 to "Ноябрь",
    12 to "Декабрь",
)

@Composable
fun Calendar(selectedDate: MutableState<LocalDate?>, calendarViewModel: CalendarViewModel) {

    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(100) }
    val endMonth = remember { currentMonth.plusMonths(100) }
    val daysOfWeek = remember { daysOfWeek() }

    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = daysOfWeek.first(),
    )



    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CalendarControl(state)
        HorizontalCalendar(
            state = state,
            dayContent = { it->
                Day(it, isSelected = selectedDate.value == it.date, calendarViewModel.allShiftsDate){ day ->
                    selectedDate.value = if (selectedDate.value == day.date) day.date else day.date
                }
            },
            monthHeader = {DaysOfWeekTitle(dayOfWeek)},
            contentPadding = PaddingValues(horizontal = 26.dp),
            monthContainer = { _, container ->
                Card(
                    modifier = Modifier.padding(end = 26.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 3.dp
                    ),
                    shape = RoundedCornerShape(26.dp)
                ) {
                    container()
                }
            }
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CalendarControl(state:CalendarState){
    val scope = rememberCoroutineScope()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp)
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MainBlue
        ),
        shape = RoundedCornerShape(24.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .clickable {
                        scope.launch {
                            state.animateScrollToMonth(state.firstVisibleMonth.yearMonth.minusMonths(1))
                        }
                    },
                contentAlignment = Alignment.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.controllarrow),
                    contentDescription = "arrow"
                )
            }
            AnimatedContent(
                modifier = Modifier.clip(RoundedCornerShape(24.dp)),
                targetState = state.firstVisibleMonth.yearMonth.month.value,
                transitionSpec = {
                    addAnimation().using(
                        SizeTransform(clip = true)
                    )
                }
            ) { targetMonth ->
                Text(
                    text = "${convertNumInMonth[targetMonth]}",
                    color = Color.White
                )
            }
            Box(
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .clickable {
                        scope.launch {
                            state.animateScrollToMonth(state.firstVisibleMonth.yearMonth.plusMonths(1))
                        }
                    },
                contentAlignment = Alignment.Center
            ){
                Image(
                    modifier = Modifier.rotate(180f),
                    painter = painterResource(id = R.drawable.controllarrow),
                    contentDescription = "arrow"
                )
            }
        }
    }
}

@ExperimentalAnimationApi
fun addAnimation(duration: Int = 800): ContentTransform {
    return slideInHorizontally(animationSpec = tween(durationMillis = duration)) { height -> height } + fadeIn(
        animationSpec = tween(durationMillis = duration)
    ) with slideOutHorizontally(animationSpec = tween(durationMillis = duration)) { height -> -height } + fadeOut(
        animationSpec = tween(durationMillis = duration)
    )
}

@Composable
fun DaysOfWeekTitle(daysOfWeek: List<String>) {
    Row(
        modifier = Modifier.fillMaxWidth(0.96f),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (dayOfWeek in daysOfWeek) {
            Box(
                modifier = Modifier
                    .size(35.dp),
                contentAlignment = Alignment.BottomCenter
            ){
                Text(
                    textAlign = TextAlign.Center,
                fontSize = 10.sp,
                fontWeight = FontWeight.Light,
                    text = dayOfWeek,
                )
            }
        }
    }
}

@Composable
fun Day(date: CalendarDay, isSelected: Boolean, allShiftDate: List<LocalDate>,onClick: (CalendarDay) -> Unit) {

    val currentDate = LocalDate.now()

    val calendarYear = date.date.year
    val calendarMonth = date.date.month.value
    val calendarDay = date.date.dayOfMonth

    val calendarLocalDateDay = LocalDate.of(calendarYear,calendarMonth, calendarDay)

    val isCurrentDay: Boolean = (
                currentDate.year == calendarYear &&
                currentDate.month.value == calendarMonth &&
                currentDate.dayOfMonth == calendarDay
            )

    val isDayOfThisMonth: Boolean = date.position == DayPosition.MonthDate


    Box(
        modifier = Modifier
            .size(35.dp)
            .clip(CircleShape)
            .background(if (calendarLocalDateDay in allShiftDate) Color(0xD280FEC1) else Color.Transparent)
            .border(1.dp, if (isSelected) MainBlue else Color.Transparent, CircleShape)
            .clickable(
                enabled = date.position == DayPosition.MonthDate,
                onClick = { onClick(date) }
            ),
        contentAlignment = Alignment.BottomCenter
    ){
        Text(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(),
            text = date.date.dayOfMonth.toString(),
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            color = if (isDayOfThisMonth) Color.Black else Color.LightGray
        )
        if(isCurrentDay){
            Box(
                modifier = Modifier
                    .padding(bottom = 2.dp)
                    .size(5.dp)
                    .clip(CircleShape)
                    .background(MainBlue),
            )
        }
    }
}
