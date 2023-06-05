package com.example.summerhahaton.View.CalendarUI.Navigate


sealed class Screens(val route:String) {
    object CalendarScreen: Screens("calendarScreen")
    object TaskScreen: Screens("taskScreen")
}