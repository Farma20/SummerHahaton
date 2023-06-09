package com.example.summerhahaton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.summerhahaton.Data.Repository.MainRepository
import com.example.summerhahaton.View.CalendarUI.CalendarUI
import com.example.summerhahaton.View.CalendarUI.Navigate.CalendarNavigate
import com.example.summerhahaton.View.CalendarUI.UIComponents.PersonTaskPage
import com.example.summerhahaton.View.CalendarUI.UIComponents.welcomeScreen
import com.example.summerhahaton.ui.theme.SummerHahatonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val repositori = MainRepository.calendarData
            SummerHahatonTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    CalendarNavigate()
                }
            }
        }
    }
}

