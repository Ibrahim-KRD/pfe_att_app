package com.example.pfe_att_app.presenter.student_pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.pfe_att_app.database.relations.EnrollmentWithSeanceStudentModule
import com.example.pfe_att_app.domain.entities.Student
import com.example.pfe_att_app.presenter.navigation.Destination
import com.example.pfe_att_app.presenter.pages.attendence.StudentDetailsScreen
import com.example.pfe_att_app.presenter.pages.schedule.ScheduleViewModel
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
println("attendece of student  ${student_id} and seance ${seance_id}")
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


    },
        floatingActionButton =
        {
            FloatingActionButton(
                onClick = {
                    navController.navigate("${Destination.QrCodeScannerPage.route}/${enrollmentWithSeanceStudentModule!!.enrollment.id}")
                },
                content = {
                    Text(text = "Scan QR COde")
                }
            )
        }
    ) {
if(enrollmentWithSeanceStudentModule != null)
        StudentDetailsScreen(enrollmentWithSeanceStudentModule!!)


    }
}
