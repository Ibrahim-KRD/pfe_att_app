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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.pfe_att_app.database.relations.EnrollmentWithStudent
import com.example.pfe_att_app.database.relations.SeanceWithModule
import com.example.pfe_att_app.domain.entities.Seance
import com.example.pfe_att_app.domain.entities.Student
import com.example.pfe_att_app.presenter.navigation.Destination
import com.example.pfe_att_app.presenter.pages.dialogs.QRCodeBottomSheetDialog
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ClassDetailsPage(
    seance_id: String?,
    navController: NavController,
    scheduleViewModel: ScheduleViewModel = hiltViewModel(),

    ) {

    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()


    val enrollmentsState = remember { mutableStateListOf<EnrollmentWithStudent>() }

    val enrollments: LiveData<List<EnrollmentWithStudent>> =
        scheduleViewModel.getStudentOf(seance_id!!.toInt())

    // Observe the LiveData and update the state object
    val lifecycleOwner = LocalLifecycleOwner.current
    enrollments.observe(lifecycleOwner) { enrollments ->
        enrollmentsState.clear()
        enrollmentsState.addAll(enrollments)
    }


    val seanceState = remember { mutableStateOf<Seance?>(null) }

    val seance: LiveData<Seance> = scheduleViewModel.getSeanceById(seance_id.toInt())

// Observe the LiveData and update the state object
    val _lifecycleOwner = LocalLifecycleOwner.current
    seance.observe(_lifecycleOwner) { fetched_seance ->
        seanceState.value = fetched_seance
    }

val seance_module : SeanceWithModule? = scheduleViewModel.getSeanceWithModule(seance_id.toInt())


    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            QRCodeBottomSheetDialog(
                classInfo = "Module: ${seance_module!!.module.name}\nClassroom: ${seance_module!!.seance.classroom}\nTime: ${seance_module!!.seance.startTime} AM - ${seance_module!!.seance.endTime} AM"
            ) {
                coroutineScope.launch {
                    bottomSheetState.hide()
                }
            }
        }
    ) {
        SeanceDetailsPage(
            navController,
            scheduleViewModel,
            bottomSheetState,
            enrollmentsState,
            seanceState,
            seance_module
        )
    }


}


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SeanceDetailsPage(
    navController: NavController,
    scheduleViewModel: ScheduleViewModel,
    bsdialog: ModalBottomSheetState,
    enrollmentsState: SnapshotStateList<EnrollmentWithStudent>,
    seanceState: MutableState<Seance?>,
    seance_module: SeanceWithModule?,

    ) {
    var selectedSceanceIndex = 1
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = seance_module!!.module.name) },
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
                onClick = { /*impliment the add student feature*/ },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            ) {
                Row(modifier = Modifier.padding(4.dp)) {
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
                        text = "Group: ${seanceState.value?.group}",
                        style = MaterialTheme.typography.body1
                    )
                    Text(
                        text = "Classroom: ${seanceState.value?.classroom}",
                        style = MaterialTheme.typography.body1
                    )
                    Text(
                        text = "Description: ${seanceState.value?.description}",
                        style = MaterialTheme.typography.body1
                    )
                    Button(
                        onClick = { coroutineScope.launch { bsdialog.show() } }, // Call the lambda function when the button is clicked
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
            BottomSection(enrollmentsState, scheduleViewModel, navController,seance_module)
        }
    }
}

@Composable
fun BottomSection(
    enrollmentsState: SnapshotStateList<EnrollmentWithStudent>,
    scheduleViewModel: ScheduleViewModel,
    navController: NavController,
    seance_module: SeanceWithModule?
) {
    StudentList(
        enrollmentsState, navController,seance_module
    )
}

@Composable
fun StudentList(
    students: SnapshotStateList<EnrollmentWithStudent>,
    navController: NavController,
    seance_module: SeanceWithModule?
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(students) { student ->

            StudentRow(
                student.student,
                PresenceState("present", color = Color.Green),
                10.0f,
                false,
                navController,seance_module!!.seance.id
            )
        }
    }
}


@Composable
fun StudentRow(
    student: Student,
    presenceState: PresenceState,
    mark: Float,
    hasAttachment: Boolean,
    navController: NavController,
    seance_id: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable(onClick = {
                navController.navigate("${Destination.AttendenceInformation.route}/${student.id}/${seance_id}")
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