package com.example.pfe_att_app.presenter.pages.dialogs

import PageDescription
import PageTitle
import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pfe_att_app.domain.entities.Module
import com.example.pfe_att_app.presenter.pages.modules.ModulesViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CreateModulePage(
    navController: NavController,
    modulesViewModel: ModulesViewModel = hiltViewModel()
) {
    val name = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val levelList = remember { mutableStateListOf("License 1", "License 2", "License 3", "Master 1", "Master 2") }
    val selectedLevel = remember { mutableStateOf("") }
    val speciality = remember { mutableStateOf("") }
    val isLevelDialogOpen = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Create New Module") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("modules") }) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            PageTitle(title = "Create New Module")
            PageDescription(description = "Fill in the details below to create a new module.")

            OutlinedTextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = description.value,
                onValueChange = { description.value = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            if (isLevelDialogOpen.value) {
                DropdownMenu(
                    expanded = true,
                    onDismissRequest = { isLevelDialogOpen.value = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    levelList.forEach { level ->
                        DropdownMenuItem(
                            onClick = {
                                selectedLevel.value = level
                                isLevelDialogOpen.value = false
                            }
                        ) {
                            Text(text = level)
                        }
                    }
                }
            }

            TextButton(
                onClick = { isLevelDialogOpen.value = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text(text = selectedLevel.value.ifEmpty { "Select Level" })
            }

            OutlinedTextField(
                value = speciality.value,
                onValueChange = { speciality.value = it },
                label = { Text("Speciality") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    modulesViewModel.AddModule(
                        Module(
                            name = name.value,
                            description = description.value,
                            level = selectedLevel.value,
                            speciality = speciality.value
                        )
                    )
                    navController.popBackStack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(text = "Create Module")
            }
        }
    }
}
