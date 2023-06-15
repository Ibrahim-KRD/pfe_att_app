package com.example.pfe_att_app.presenter.pages.attendence

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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


    var enrollmentWithSeanceStudentModule = scheduleViewModel.getEnrollmentWithStudentModuleSeance(
        student_id!!.toInt(),
        seance_id!!.toInt()
    )



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
//
            enrollmentWithSeanceStudentModule!!
        )
    }
}

@Composable
fun StudentDetailsScreen(information: EnrollmentWithSeanceStudentModule) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Top section with student and class information
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            backgroundColor = lightRed,
            elevation = 2.dp,

            ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = information.seance.description,
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = information.student.speciality,
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
                            text = "Student:",
                            style = MaterialTheme.typography.subtitle2
                        )
                        Text(
                            text = "${information.student.firstName} ${information.student.lastName}",
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
                            text = information.student.group,
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
                            text = information.student.level,
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
                            text = information.seance.classroom,
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
                            text = information.seance.classType,
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
