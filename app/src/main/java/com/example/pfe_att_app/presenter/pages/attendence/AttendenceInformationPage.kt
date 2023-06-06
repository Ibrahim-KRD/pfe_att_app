package com.example.pfe_att_app.presenter.pages.attendence

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.pfe_att_app.database.relations.EnrollmentWithSeanceStudentModule
import com.example.pfe_att_app.domain.entities.Module
import com.example.pfe_att_app.domain.entities.Student
import com.example.pfe_att_app.presenter.pages.schedule.ScheduleViewModel
import com.example.pfe_att_app.ui.theme.lightRed
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AttendenceInformationPage(
    student_id: String?,
    seance_id: String?,
    navController: NavController,
    scheduleViewModel: ScheduleViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()


    val dataState = remember { mutableStateOf<EnrollmentWithSeanceStudentModule?>(null) }

    val data: LiveData<EnrollmentWithSeanceStudentModule?> =
        scheduleViewModel.getEnrollmentWithStudentModuleSeance(
            student_id!!.toInt(),
            seance_id!!.toInt()
        )

// Observe the LiveData and update the state object
    val _lifecycleOwner = LocalLifecycleOwner.current
    data.observe(_lifecycleOwner) { fetched_seance ->
        dataState.value = fetched_seance
    }



    Scaffold(topBar = {

        TopAppBar(

            title = { Text("Atendence details") },
            navigationIcon = {
                IconButton(onClick = {
                    coroutineScope.launch {
                        navController.popBackStack()
                    }
                }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "back")
                }
            }
        )


    }) {
        StudentDetailsScreen(
             Student(seance_id, student_id, "456 Elm St", "2021", "654321", "Bachelor", 2, "Mathematics","","")
                 //student = dataState.value!!.student
        )
    }
}

@Composable
fun StudentDetailsScreen(student: Student) {


    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        // Top section with student and class information
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(1.dp)
                .background(lightRed)

        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "module.name",
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Speciality",
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Student: ${student.firstName} ${student.lastName} ",
                            style = MaterialTheme.typography.subtitle2
                        )
                        Text(
                            text = "${student.firstName} ${student.lastName}",
                            style = MaterialTheme.typography.body1
                        )
                    }
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Group:",
                            style = MaterialTheme.typography.subtitle2
                        )
                        Text(
                            text = "A",
                            style = MaterialTheme.typography.body1
                        )
                    }
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Level:",
                            style = MaterialTheme.typography.subtitle2
                        )
                        Text(
                            text = "License",
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Classroom:",
                            style = MaterialTheme.typography.subtitle2
                        )
                        Text(
                            text = "inf03",
                            style = MaterialTheme.typography.body1
                        )
                    }
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Class Type:",
                            style = MaterialTheme.typography.subtitle2
                        )
                        Text(
                            text = "TP",
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }
        }

        // Button to set the student's mark and presence state
        Button(
            onClick = { /* TODO */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Set Mark and Presence")
        }

        // List of attachments sent by the student
        Text(
            text = "Attachments",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(top = 16.dp)
        )

    }
}
