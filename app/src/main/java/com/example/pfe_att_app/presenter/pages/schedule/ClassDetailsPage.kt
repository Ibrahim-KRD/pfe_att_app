package com.example.pfe_att_app.presenter.pages.schedule

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email

import androidx.compose.runtime.Composable

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pfe_att_app.domain.entities.Student
import com.example.pfe_att_app.presenter.navigation.Destination
import com.example.pfe_att_app.presenter.pages.dialogs.QRCodeBottomSheetDialog
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ClassDetailsPage(navController: NavController,scheduleViewModel: ScheduleViewModel = hiltViewModel()) {

    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            QRCodeBottomSheetDialog(
                classInfo = "Module: Math\nClassroom: A101\nTime: 9:00 AM - 10:30 AM"
            ) { coroutineScope.launch {
                bottomSheetState.hide()
            } }
        }
    ) {
        SeanceDetailsPage(navController, scheduleViewModel, bottomSheetState)
    }


}


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SeanceDetailsPage(
    navController: NavController,
    scheduleViewModel: ScheduleViewModel,
    bsdialog: ModalBottomSheetState,

    ) {
var selectedSceanceIndex = 1 
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = scheduleViewModel.sciences.get(selectedSceanceIndex).module.name) },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            navController.navigate(Destination.Schedule.route)
                        }
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "back")
                    }
                }

            )
        },

        floatingActionButton = {
            FloatingActionButton(
                 onClick = { /*impliment the add student feature*/  },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            ) {
                Row (modifier = Modifier.padding(4.dp)){
                    Icon(Icons.Default.Add, contentDescription = "Add Student")
                    Text("Add Student")
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Card(

                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Group: ${scheduleViewModel.sciences.get(selectedSceanceIndex).group}",
                        style = MaterialTheme.typography.body1
                    )
                    Text(
                        text = "Classroom: ${scheduleViewModel.sciences.get(selectedSceanceIndex).classroom}",
                        style = MaterialTheme.typography.body1
                    )
                    Text(
                        text = "Description: ${scheduleViewModel.sciences.get(selectedSceanceIndex).description}",
                        style = MaterialTheme.typography.body1
                    )
                    Button(
                        onClick = {coroutineScope.launch { bsdialog.show() }}, // Call the lambda function when the button is clicked
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(top = 8.dp)
                    ) {
                        Text("Generate QR Code")
                    }
                }
            }
            Text(
                text = "Affected Students",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(top = 16.dp)
            )
            BottomSection(scheduleViewModel, navController)
        }
    }
}

@Composable
fun BottomSection(scheduleViewModel: ScheduleViewModel,navController: NavController){
    StudentList(scheduleViewModel.getStudentOf(),navController)
}

@Composable
fun StudentList(students: List<Student>,navController: NavController) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(students) { student ->

            StudentRow(student = student,PresenceState("present" , color = Color.Green),10.0f ,true, navController)
        }
    }
}


@Composable
fun StudentRow(student: Student, presenceState: PresenceState, mark: Float, hasAttachment: Boolean,navController: NavController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable(onClick = {
                navController.navigate(Destination.AttendenceInformation.route)
            })

    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "${student.firstName} ${student.lastName}",
                style = MaterialTheme.typography.h6
            )
            Text(
                text = "Mark: $mark",
                style = MaterialTheme.typography.body1
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(presenceState.color, CircleShape)
                )
                Text(
                    text = presenceState.name,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        if (hasAttachment) {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Attachment",
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}
data class PresenceState(
    val name: String,
    val color: Color
)