import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.pfe_att_app.domain.entities.Module
import com.example.pfe_att_app.domain.entities.Seance
import com.example.pfe_att_app.domain.entities.Student
import com.example.pfe_att_app.presenter.pages.modules.ModulesViewModel
import com.example.pfe_att_app.presenter.pages.schedule.ScheduleViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CreateSessionPage(
    navController: NavController,
    modulesViewModel: ModulesViewModel = hiltViewModel(),
    scheduleViewModel: ScheduleViewModel = hiltViewModel()
) {
    val moduleState = remember { mutableStateListOf<Module>() }

    val modules: LiveData<List<Module>> = modulesViewModel.getModules()

    // Observe the LiveData and update the state object
    val lifecycleOwner = LocalLifecycleOwner.current
    modules.observe(lifecycleOwner) { contactsList ->
        moduleState.clear()
        moduleState.addAll(contactsList)
    }
    val selectedModule = remember { mutableStateOf<Module?>(null) }


    val selectedDay = remember { mutableStateOf<DayOfWeek?>(null) }
    val selectedStartTime = remember { mutableStateOf<LocalTime?>(null) }
    val selectedEndTime = remember { mutableStateOf<LocalTime?>(null) }

    // Seance information section states
    val classTypeList = listOf("Lecture", "TP", "TD", "Examen")
    val selectedClassType = remember { mutableStateOf("") }

    val levelList = listOf("License 1", "License 2", "License 3", "Master 1", "Master 2")
    val selectedLevel = remember { mutableStateOf("") }

    val selectedClassroom = remember { mutableStateOf("") }

    // Retrieve user id information from SharedPreferences

    val context = LocalContext.current
    val sharedPreferences =
        remember { context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE) }

    val userId = sharedPreferences.getInt("USER_ID", 0)




    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = " Add New Session ") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("schedule") }) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            PageTitle(title = "Create New Session")
            PageDescription(description = "Fill in the details below to create a new session.")
            ModuleInformationSection(moduleList = moduleState, selectedModule)
            TimingSection(
                selectedDay = selectedDay,
                selectedStartTime = selectedStartTime,
                selectedEndTime = selectedEndTime,
                context = LocalContext.current
            )
            SeanceInformationSection(
                classTypeList = classTypeList,
                levelList = levelList,
                selectedClassType = selectedClassType,
                selectedLevel = selectedLevel,
                selectedClassroom = selectedClassroom
            )
            SaveButton(onSaveClicked = {

                val students = scheduleViewModel.getStudents()

                scheduleViewModel.AddSceanceToSchedule(
                    Seance(
                        module_id = selectedModule.value!!.id,
                        classType = selectedClassType.value,
                        startTime = selectedStartTime.value.toString(),
                        endTime = selectedEndTime.value.toString(),
                        group = selectedLevel.value,
                        classroom = selectedClassroom.value,
                        description = "Dummy session description",
                        responsible_id = userId!!,
                        level = selectedLevel.value

                    ),students

                )
                navController.popBackStack()
            })

            //  sections will go here
        }
    }
}

@Composable
fun TopBar(navController: NavController) {
    TopAppBar(
        title = { },
        navigationIcon = {
            IconButton(onClick = { navController.navigate("schedule") }) {
                Icon(Icons.Default.Close, contentDescription = "Close")
            }
        }
    )
}


@Composable
fun PageTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.h4,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@Composable
fun PageDescription(description: String) {
    Text(
        text = description,
        style = MaterialTheme.typography.body1,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@Composable
fun ModuleInformationSection(
    moduleList: List<Module>,
    selectedModule: MutableState<Module?>,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier.padding(top = 16.dp)) {
        Text(text = "module information")
        Button(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = selectedModule.value?.name ?: "Select Module",
                modifier = Modifier.padding(end = 8.dp)
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Expand",
                modifier = Modifier.size(24.dp)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            moduleList.forEach { module ->
                DropdownMenuItem(
                    onClick = {
                        selectedModule.value = module
                        expanded = false
                    }
                ) {
                    Text(text = module.name)
                }
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun TimingSection(
    selectedDay: MutableState<DayOfWeek?>,
    selectedStartTime: MutableState<LocalTime?>,
    selectedEndTime: MutableState<LocalTime?>,
    context: Context,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(vertical = 16.dp)) {
        SectionTitle(title = "Timing")

        DayPicker(
            selectedDay = selectedDay.value,
            onDaySelected = { day ->
                selectedDay.value = day
            },
            context = context,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TimePicker(
                selectedTime = selectedStartTime.value,
                onTimeSelected = { time ->
                    selectedStartTime.value = time
                },
                context = context,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(16.dp))

            TimePicker(
                selectedTime = selectedEndTime.value,
                onTimeSelected = { time ->
                    selectedEndTime.value = time
                },
                context = context,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun DayPicker(
    selectedDay: DayOfWeek?,
    onDaySelected: (DayOfWeek) -> Unit,
    context: Context,
    modifier: Modifier = Modifier
) {
    val daysOfWeek = listOf(
        DayOfWeek.MONDAY,
        DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY,
        DayOfWeek.FRIDAY,
        DayOfWeek.SATURDAY,
        DayOfWeek.SUNDAY
    )

    Box(modifier = modifier) {
        Text(
            text = selectedDay?.name ?: "Select Day",
            modifier = Modifier
                .clickable {
                    // Open day picker dialog
                    val currentDay = selectedDay ?: DayOfWeek.MONDAY
                    val datePickerDialog = DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            val selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
                            val selectedDayOfWeek = selectedDate.dayOfWeek
                            onDaySelected(selectedDayOfWeek)
                        },
                        2023,
                        11,
                        23
                    )
                    datePickerDialog.show()
                }
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun TimePicker(
    selectedTime: LocalTime?,
    onTimeSelected: (LocalTime) -> Unit,
    context: Context,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Text(
            text = selectedTime?.format(DateTimeFormatter.ISO_LOCAL_TIME) ?: "Select Time",
            modifier = Modifier
                .clickable {
                    // Open time picker dialog
                    val currentTime = selectedTime ?: LocalTime.now()
                    val timePickerDialog = TimePickerDialog(
                        context,
                        { _, hourOfDay, minute ->
                            val selectedTime = LocalTime.of(hourOfDay, minute)
                            onTimeSelected(selectedTime)
                        },
                        currentTime.hour,
                        currentTime.minute,
                        true
                    )
                    timePickerDialog.show()
                }
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun SeanceInformationSection(
    classTypeList: List<String>,
    levelList: List<String>,
    selectedClassType: MutableState<String>,
    selectedLevel: MutableState<String>,
    selectedClassroom: MutableState<String>
) {
    // Dialog state variables
    val dialogClassTypeOpen = remember { mutableStateOf(false) }
    val dialogLevelOpen = remember { mutableStateOf(false) }

    Column {
        SectionTitle(title = "Seance Information")

        Button(
            onClick = { dialogClassTypeOpen.value = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Class Type: ${selectedClassType.value}")
        }

        Button(
            onClick = { dialogLevelOpen.value = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Level: ${selectedLevel.value}")
        }

        OutlinedTextField(
            value = selectedClassroom.value,
            onValueChange = { selectedClassroom.value = it },
            label = { Text(text = "Classroom") },
            modifier = Modifier.fillMaxWidth()
        )
    }

    // Class Type Dialog
    if (dialogClassTypeOpen.value) {
        AlertDialog(
            onDismissRequest = { dialogClassTypeOpen.value = false },
            title = { Text(text = "Select Class Type") },
            buttons = {
                classTypeList.forEach { classType ->
                    TextButton(onClick = {
                        selectedClassType.value = classType
                        dialogClassTypeOpen.value = false
                    }) {
                        Text(text = classType)
                    }
                }
            }
        )
    }

    // Level Dialog
    if (dialogLevelOpen.value) {
        AlertDialog(
            onDismissRequest = { dialogLevelOpen.value = false },
            title = { Text(text = "Select Level") },
            buttons = {
                levelList.forEach { level ->
                    TextButton(onClick = {
                        selectedLevel.value = level
                        dialogLevelOpen.value = false
                    }) {
                        Text(text = level)
                    }
                }
            }
        )
    }
}

@Composable
fun ClassTypeDialog(
    classTypeList: List<String>,
    selectedClassType: MutableState<String>
) {
    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text(text = "Select Class Type") },
            buttons = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    classTypeList.forEach { classType ->
                        Button(
                            onClick = {
                                selectedClassType.value = classType
                                showDialog.value = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = classType)
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun LevelDialog(
    levelList: List<String>,
    selectedLevel: MutableState<String>
) {
    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text(text = "Select Level") },
            buttons = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    levelList.forEach { level ->
                        Button(
                            onClick = {
                                selectedLevel.value = level
                                showDialog.value = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = level)
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun SaveButton(onSaveClicked: () -> Unit) {
    Button(
        onClick = {
            onSaveClicked()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
    ) {
        Text(text = "Save New Session", color = Color.White)
    }
}
