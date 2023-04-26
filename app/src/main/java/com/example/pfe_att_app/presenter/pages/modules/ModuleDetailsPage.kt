package com.example.pfe_att_app.presenter.pages.modules

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pfe_att_app.domain.entities.Module
import com.example.pfe_att_app.domain.entities.Sceance
import com.example.pfe_att_app.presenter.navigation.Destination
import com.example.pfe_att_app.ui.theme.lightRed

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ModuleDetailsScreen(navController: NavController,modulesViewModel: ModulesViewModel = hiltViewModel()) {
    val tabs = listOf("TP", "TD", "Courses", "Exams")
    var selectedTab by remember { mutableStateOf(0) }
val module =     modulesViewModel.modules.get(1)
    Scaffold(
topBar = {
    TopAppBar(
        title = { Text(text = "module information") },
        navigationIcon = {
            IconButton(onClick = { /*todo back*/ navController.navigateUp() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
},
        content = {

            Column {
                ModuleDetailsHeader(module)

                ClassTabView(modulesViewModel.generateSceances(),
                navigateTostudentAttendence = {navController.navigate(Destination.AttendenceInformation.route)})

            }

        }

    )
}

@Composable
fun ClassList(sceances: List<Sceance>, navigateTostudentAttendence: () -> Unit) {
    LazyColumn {
        items(sceances) { sceance ->
            ClassRow(sceance, navigateTostudentAttendence)
            Divider()
        }
    }
}

@Composable
fun ClassRow(sceance: Sceance, navigateTostudentAttendence: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()

            .padding(16.dp)
            .clickable {
                navigateTostudentAttendence()
            }
            ,

        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "08:00",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "10:30",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "License 3",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Group ${sceance.group}",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = sceance.classroom,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Duration 60 min ",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}



@Composable
fun ModuleDetailsHeader(module: Module) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(1.dp)
            .background(lightRed)

    ) {
        Text(
            text = module.name,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = module.speciality,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Level", style = MaterialTheme.typography.caption)
                Text(text = module.level, style = MaterialTheme.typography.body1)
            }
            Button(
                onClick = {  },

            ) {
                Text("View Student Grades")
            }


        }
        Spacer(modifier = Modifier.height(24.dp))

    }
}


@Composable
fun ClassTabView(classList: List<Sceance>, navigateTostudentAttendence: () -> Unit) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("TP", "TD", "Courses", "Exams")

    Column {
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index }
                ) {
                    Text(title, fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal,
                    modifier = Modifier.padding(10.dp))

                }
            }
        }
        Divider()

        when (selectedTabIndex) {
            0 -> ClassList(classList, navigateTostudentAttendence)
            1 -> ClassList(classList,navigateTostudentAttendence)
            2 -> ClassList(classList,navigateTostudentAttendence)
            3 -> ClassList(classList,navigateTostudentAttendence)
        }
    }
}