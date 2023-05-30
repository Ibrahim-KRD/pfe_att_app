package com.example.pfe_att_app.presenter.pages.modules

import android.annotation.SuppressLint

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pfe_att_app.domain.entities.Module
import com.example.pfe_att_app.presenter.navigation.Destination
import com.example.pfe_att_app.presenter.pages.authentication.AuthenticationViewModel
import com.example.pfe_att_app.presenter.pages.mainScreen.AppDrawer
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ModulesPage(
    navController: NavController,
    modulesViewModel: ModulesViewModel = hiltViewModel(),

) {



    val coroutineScope = rememberCoroutineScope()

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Modules")
                }
                ,
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                }
            )
        },

        drawerContent = {
            // Drawer content
            AppDrawer(navController, scaffoldState)
        }
        ,
        floatingActionButton  = {
            FloatingActionButton(
                onClick = {
                   modulesViewModel.AddModule(
                       Module("Database",
                           "data structor and stuff like that",
                           "License 1",
                           "Informatic")
                   )
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")

            }

        }
    ) {


        ModulePageContent(modulesViewModel.modules,navController)

    }
}

@Composable
fun ModulePageContent(modules: List<Module>,navController: NavController) {


    var searchText by remember { mutableStateOf("") }

    val filteredModules = modules.filter {
        it.name.contains(searchText, ignoreCase = true)
                || it.speciality.contains(searchText, ignoreCase = true)
                || it.level.contains(searchText, ignoreCase = true)

    }

    Column {
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier
                .fillMaxWidth()

                .padding(20.dp, 16.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
        )

Text(text = "Modules")

        LazyColumn {
            items(modules) { module ->

                ModuleListRow(module, navController)


            }
        }



    }



}

@Composable
fun ModuleListRow(module: Module,navController: NavController) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth()
            .clickable(onClick = { navController.navigate(Destination.ModuleDetailsPage.route) }),
        elevation = 8.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = module.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = module.speciality,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "Level: ${module.level}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "Created on: 10/12/2023",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
    }
}