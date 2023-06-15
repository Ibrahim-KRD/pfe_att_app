package com.example.pfe_att_app.presenter.student_pages

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.pfe_att_app.database.relations.SceancewithResponsibleAndModule
import com.example.pfe_att_app.presenter.navigation.Destination
import com.example.pfe_att_app.presenter.pages.mainScreen.StudentAppDrawer
import com.example.pfe_att_app.presenter.pages.schedule.ScheduleViewModel
import com.example.pfe_att_app.ui.theme.darkRed
import kotlinx.coroutines.launch
import java.time.format.TextStyle
import java.util.Locale

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SchedulePage(navController: NavController,scheduleViewModel: ScheduleViewModel= hiltViewModel()){


    val context = LocalContext.current
    val sharedPreferences = remember { context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE) }

    // Retrieve user information from SharedPreferences
    val userId = sharedPreferences.getInt("USER_ID", 1)


//region database section
    val coroutineScope = rememberCoroutineScope()

    val scaffoldState = rememberScaffoldState()


    val scheduleState = remember { mutableStateListOf<SceancewithResponsibleAndModule>() }

    val seances: LiveData<List<SceancewithResponsibleAndModule>> = scheduleViewModel.getStudentSchedule(userId)

    // Observe the LiveData and update the state object
    val lifecycleOwner = LocalLifecycleOwner.current
    seances.observe(lifecycleOwner) { contactsList ->
        scheduleState.clear()
        scheduleState.addAll(contactsList)
    }

//endregion



    Scaffold(scaffoldState = scaffoldState, topBar = {
        TopAppBar(title = {
            Text(text = "Schedule ")
        }, navigationIcon = {
            IconButton(onClick = {
                coroutineScope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
        })
    }, drawerContent = {
        // Drawer content
        StudentAppDrawer(navController, scaffoldState)
    }

    ) {
      ScheduleContent(
            navController,
            scheduleViewModel,
            scheduleState,userId
        )
    }

}



//region ui components
@Composable
fun ScheduleContent(
    navController: NavController,
    scheduleViewModel: ScheduleViewModel,
    seances: List<SceancewithResponsibleAndModule>,
    userId: Int
) {

    Column {
        Column(
            Modifier
                .background(Color.White)
                .shadow(1.dp)
                .padding(0.dp, 2.dp)
                .padding(0.dp, 5.dp)

        ) {
            DayNavigator(scheduleViewModel)

            Text(text = scheduleViewModel.getFullDayInformation(), textAlign = TextAlign.Start)
        }



        SeanceList(seances, navController, scheduleViewModel,userId)
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
        modifier = Modifier
            .width(40.dp)

            .padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally
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
fun SeanceList(
    seances: List<SceancewithResponsibleAndModule>,
    navController: NavController,
    scheduleViewModel: ScheduleViewModel,
    userId: Int
) {
    LazyColumn {
        items(seances) { seance ->
            SceanceCard(seance, navController, scheduleViewModel,userId)
        }
    }
}


@Composable
fun SceanceCard(
    sceance: SceancewithResponsibleAndModule,
    navController: NavController,
    scheduleViewModel: ScheduleViewModel,
    userId: Int
) {
    Card(shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable {

            //    navController.navigate("${Destination.StudentAttendqnceInformation.route}/${userId}/${sceance.sceance.id}")
                navController.navigate("${Destination.StudentAttendqnceInformation.route}/${userId}/3")

            }) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Module: ${sceance.module.name}", style = MaterialTheme.typography.h6
                )
                Text(
                    text = "Speciality: ${sceance.module.speciality}",
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = "Level: ${sceance.module.level}", style = MaterialTheme.typography.body1
                )
                Text(
                    text = "Group: ${sceance.sceance.group}", style = MaterialTheme.typography.body1
                )
                Text(
                    text = "Classroom: ${sceance.sceance.classroom}",
                    style = MaterialTheme.typography.body1
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            com.example.pfe_att_app.presenter.pages.schedule.ClassTimeline(
                startTime = sceance.sceance.startTime,
                endTime = sceance.sceance.endTime,
                20,

                )
        }
    }
}

@Composable
fun ClassTimeline(startTime: String, endTime: String, duration: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Filled.DateRange, contentDescription = "Start time"
        )
        Text(
            text = " ${startTime} - ${endTime}", style = MaterialTheme.typography.caption
        )
    }



}


