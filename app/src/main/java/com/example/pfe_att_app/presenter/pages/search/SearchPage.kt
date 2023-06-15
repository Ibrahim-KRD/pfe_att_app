package com.example.pfe_att_app.presenter.pages.search

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pfe_att_app.presenter.pages.mainScreen.AppDrawer
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchPage(
navController:NavController
) {
    var searchQuery by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Search") },
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }

                    }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                }
            )
        },
        drawerContent = {
            // Drawer content
            AppDrawer(navController, scaffoldState)
        }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                label = { Text("Search") },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search Icon",
                        modifier = Modifier.size(18.dp)
                    )
                },
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colors.primary,
                    unfocusedBorderColor = MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = { /* Perform search action here */ }
                )
            )

            // Search Results
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Search Results",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Preview of search results
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(10) { index ->
                        SessionResultItem(index)
                        Divider()
                    }
                }
            }
        }
    }
}

@Composable
fun SessionResultItem(index: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { /* Handle item click */ }
            .padding(horizontal = 16.dp)
    ) {
        // Item content
        Column(Modifier.weight(1f)) {
            Text(
                text = "Session Title $index",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Session Description $index",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Session Date $index",
                style = MaterialTheme.typography.caption
            )
        }

        Icon(
            Icons.Default.ArrowForward,
            contentDescription = "Navigate Icon",
            modifier = Modifier.size(18.dp)
        )
    }
}
