package com.example.pfe_att_app.presenter.pages.mainScreen



import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.example.pfe_att_app.R
import com.example.pfe_att_app.domain.entities.Teacher

import com.example.pfe_att_app.presenter.navigation.Destination


@Composable
fun StudentAppDrawer(
    navController: NavController,

    scaffoldState: ScaffoldState,


    ) {


    var cs = rememberCoroutineScope()

//region get saved logged in information
    val context = LocalContext.current
    val sharedPreferences = remember { context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE) }

    // Retrieve user information from SharedPreferences
    val userId = sharedPreferences.getInt("USER_ID", 0)
    val userType = sharedPreferences.getString("USER_TYPE", "")
    val firstName = sharedPreferences.getString("FIRST_NAME", "")
    val lastName = sharedPreferences.getString("LAST_NAME", "")
//endregion

    StudentDrawerBody(
        Teacher(firstName!!, lastName!!, "123 Main St", "2022", "Mathematics", userType!!,"","",""),
        navController.currentDestination,
        { navController.navigate(Destination.StudentSchedule.route) },
        { navController.navigate(Destination.StudentSchedule.route) },
        { navController.navigate(Destination.StudentSchedule.route) },
        { navController.navigate(Destination.StudentSchedule.route) },
        { navController.navigate(Destination.Authentication.route) }
    )


}


@Composable
fun StudentDrawerBody(
    loggedInUser: Teacher,
    currentDestination: NavDestination?,
    navigateToSearch: () -> Unit,
    navigateToSchedule: () -> Unit,
    navigateToModules: () -> Unit,
    navigateToSettings: () -> Unit,
    logout: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Header section
        Box(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(96.dp),
            contentAlignment = Alignment.CenterStart) {
            // Application logo
            Image(
                painter = painterResource(id = R.drawable.app_logo_big),
                contentDescription = null,
                modifier = Modifier.size(72.dp)
            )
            // User info
            Column(
                modifier = Modifier.padding(start = 96.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Welcome, ${loggedInUser.firstName} ${loggedInUser.lastName}",
                    style = MaterialTheme.typography.h6)
                Text(text = loggedInUser.role,
                    style = MaterialTheme.typography.body1)
            }
        }
        // Menu items
        Column(modifier = Modifier.weight(1f)) {

            NavigationItem(
                text = "Schedule",
                icon = Icons.Default.DateRange,
                isSelected = currentDestination?.route == "schedule",
                onClick = navigateToSchedule
            )

            NavigationItem(
                text = "Settings",
                icon = Icons.Default.Settings,
                isSelected = currentDestination?.route == "settings",
                onClick = navigateToSettings
            )
        }
        // Logout button
        Button(
            onClick = { },
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(text = "Log out")
        }
    }
}

@Composable
private fun NavigationItem(
    text: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val color = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
    val backgroundColor = if (isSelected) MaterialTheme.colors.primaryVariant else Color.Transparent
    val modifier = Modifier
        .fillMaxWidth()
        .height(48.dp)
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .background(backgroundColor)
        .clickable { onClick() }

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            color = color
        )
    }
}