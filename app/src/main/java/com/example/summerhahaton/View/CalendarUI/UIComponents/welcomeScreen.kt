package com.example.summerhahaton.View.CalendarUI.UIComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.summerhahaton.Domain.LoadingAnimation
import com.example.summerhahaton.R
import com.example.summerhahaton.View.CalendarUI.Navigate.Screens
import com.example.summerhahaton.ui.theme.MainBlue
import kotlinx.coroutines.delay

@Composable
fun welcomeScreen(navController: NavController) {
    LaunchedEffect(key1 = "",){
        delay(2000)
        navController.navigate(Screens.CalendarScreen.route){
            popUpTo(0)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBlue),
        contentAlignment = Alignment.BottomCenter
    ){
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Image(
                modifier = Modifier
                    .size(370.dp)
                    .padding(bottom = 250.dp),
                painter = painterResource(id = R.drawable.group_1), contentDescription = ""
            )
            LoadingAnimation(
                modifier = Modifier.padding(top = 360.dp),
                circleSize = 8.dp,
                circleColor = Color.White,
                spaceBetween = 6.dp,
                travelDistance = 6.dp
            )
        }
        Text(
            text = "ООО «Арника»",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}