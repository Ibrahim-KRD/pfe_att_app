import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun AddScheduleDialog(onSave: (ScheduleData) -> Unit, onDismiss: () -> Unit) {
    var selectedModule by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    var selectedStartTime by remember { mutableStateOf("") }
    var selectedEndTime by remember { mutableStateOf("") }
    var selectedClassType by remember { mutableStateOf("") }
    var selectedLevel by remember { mutableStateOf("") }
    var selectedSpeciality by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Select Module")
                OutlinedTextField(
                    value = selectedModule,
                    onValueChange = { selectedModule = it },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Other input fields...

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val scheduleData = ScheduleData(
                            module = selectedModule,
                            date = selectedDate,
                            startTime = selectedStartTime,
                            endTime = selectedEndTime,
                            classType = selectedClassType,
                            level = selectedLevel,
                            speciality = selectedSpeciality
                        )
                        onSave(scheduleData)
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = "Save Schedule")
                }
            }
        }
    }
}

@Composable
fun ShowAddScheduleDialog() {
    var showDialog by remember { mutableStateOf(false) }

    Button(
        onClick = { showDialog = true }
    ) {
        Text(text = "Add Schedule")
    }

    if (showDialog) {
        AddScheduleDialog(
            onSave = { /* Handle save action */ },
            onDismiss = { showDialog = false }
        )
    }
}

@Preview
@Composable
fun PreviewAddScheduleDialog() {
    ShowAddScheduleDialog()
}

data class ScheduleData(
    val module: String,
    val date: String,
    val startTime: String,
    val endTime: String,
    val classType: String,
    val level: String,
    val speciality: String
)
