package com.example.pfe_att_app.presenter.pages.authentication


import android.annotation.SuppressLint
import android.media.Image
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.input.PasswordVisualTransformation

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData

import androidx.navigation.NavController

import com.example.pfe_att_app.R
import com.example.pfe_att_app.domain.entities.Person
import com.example.pfe_att_app.domain.entities.Student
import com.example.pfe_att_app.domain.entities.Teacher
import com.example.pfe_att_app.presenter.navigation.Destination
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginPage(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val showDialog = remember { mutableStateOf(false) }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
        ) {
            Text(
                text = "Log In",
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(vertical = 32.dp)
            )
            Spacer(modifier = Modifier.weight(.5f))
            Image(
                painter = painterResource(id = R.drawable.app_logo_big),
                contentDescription = "Application Logo",
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Application Name",
                style = MaterialTheme.typography.h4,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(48.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = {
                    authenticationViewModel.login("mail", "password").observeForever { user ->
                        when (user) {
                            is Teacher -> navController.navigate(Destination.Main.route)
                            is Student -> navController.navigate(Destination.Main.route)
                            else -> {
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Invalid login credentials",
                                        actionLabel = "OK"
                                    )
                                }
                            }
                        }
                        Log.d("LoginPage", "User: $user")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
            ) {
                Text(text = "Log In", color = MaterialTheme.colors.onPrimary)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Don't have an account? ",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                TextButton(onClick = { navController.navigate(Destination.Register.route) }) {
                    Text(
                        text = "Register",
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
@Composable
fun showDialog(scaffoldState: ScaffoldState) {
    AlertDialog(
        onDismissRequest = { },
        title = { Text("Login Failed") },
        text = { Text("Invalid login credentials") },
        confirmButton = {
            TextButton(onClick = { scaffoldState.snackbarHostState.currentSnackbarData?.dismiss() }) {
                Text("OK")
            }
        }
    )
}