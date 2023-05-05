package com.example.pfe_att_app.presenter.pages.schedule


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pfe_att_app.domain.entities.Module
import com.example.pfe_att_app.domain.entities.Sceance
import com.example.pfe_att_app.domain.entities.Teacher
import com.example.pfe_att_app.presenter.navigation.Destination
import com.example.pfe_att_app.presenter.pages.mainScreen.AppDrawer
import com.example.pfe_att_app.ui.theme.darkRed
import com.example.pfe_att_app.ui.theme.lightRed
import kotlinx.coroutines.launch
import java.time.format.TextStyle
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SchedulePage(
    navController: NavController,
    scheduleViewModel: ScheduleViewModel = hiltViewModel()
) {


    val coroutineScope = rememberCoroutineScope()

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Schedule")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                }
            )
        },
        floatingActionButton  = {
            FloatingActionButton(
                onClick = {
                  scheduleViewModel.AddSceanceToSchedule(
                      Sceance(scheduleViewModel.sciences.size.toString(),
                          Teacher("John", "Doe", "123 Main St", "2022", "Mathematics", "Professor"),
                          Module("Mathematics", "This is a math course", "Advanced", "Mathematics"),
                          "Lecture","08:00","12:32","A","inf 3","my new added sceance"
                      )
                  )
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")

            }

        },
        drawerContent = {
            // Drawer content
            AppDrawer(navController, scaffoldState)
        }

    ) {


        ScheduleContent(navController, scheduleViewModel)
    }

}


@Composable
fun ScheduleContent(navController: NavController, scheduleViewModel: ScheduleViewModel) {

    Column  {
        Column (
            Modifier.background(lightRed)
                .padding(0.dp,  5.dp)

        ) {
            DayNavigator(scheduleViewModel)

            Text(text = scheduleViewModel.getFullDayInformation(), textAlign = TextAlign.Start)
        }


        
        SeanceList(scheduleViewModel.sciences,navController)
    }





}


@Composable
fun DayNavigator(scheduleViewModel: ScheduleViewModel) {
    Column(

    ) {
        // Navigation arrows and month/year display
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 24.dp)
        ) {
            IconButton(onClick = {
                scheduleViewModel.decrementMonth()
            }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Previous Month")
            }
            Text(text = scheduleViewModel.getMonthName())
            IconButton(onClick = {
                scheduleViewModel.incrementMonth()
            }) {
                Icon(Icons.Filled.ArrowForward, contentDescription = "Next Month")
            }
        }

        val days = scheduleViewModel.getCurrentMonthDays(scheduleViewModel.currentDate.value)

        val listState = rememberLazyListState()

// Get the index of the current day in the list of days
        val currentDayIndex = scheduleViewModel.currentDate.value.dayOfMonth - 3

// Scroll the LazyColumn to the current day
        LaunchedEffect(currentDayIndex) {
            listState.animateScrollToItem(currentDayIndex)
        }

        // Horizontal list of days in the month
        LazyRow(
            state = listState,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp)
        ) {
            items(days) { day ->
                val dayOfWeek = day.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                val dayOfMonth = day.dayOfMonth
                DayNode(DayItem(dayOfWeek, dayOfMonth))
            }
        }
    }
}

@Composable
fun DayNode(scheduleItem: DayItem) {
    Column(
        modifier = Modifier.width(40.dp)

            .padding(5.dp)

           ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = scheduleItem.day.toString(),
            fontWeight = FontWeight.Bold,
        )
        Text(text = scheduleItem.dayOfWeek, color = darkRed)
    }
}


data class DayItem(val dayOfWeek: String, val day: Int)


@Composable
fun SeanceList(seances: List<Sceance>,navController: NavController) {
    LazyColumn {
        items(seances) { seance ->
            SceanceCard(seance,navController)
        }
    }
}


@Composable
fun SceanceCard(sceance: Sceance,navController: NavController) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(Destination.ClassDetails.route)

            }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = sceance.module.name,
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Group: ${sceance.group}",
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Time: ${sceance.startTime} - ${sceance.endTime}",
                style = MaterialTheme.typography.body1
            )
        }
    }
}