package com.example.pfe_att_app.presenter.pages.profile

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberImagePainter
import com.example.pfe_att_app.R
import com.example.pfe_att_app.domain.entities.Person

@Composable
fun ProfilePage(navController: NavController) {

    var cs = rememberCoroutineScope()
    val context = LocalContext.current
    val sharedPreferences = remember { context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE) }

    // Retrieve user information from SharedPreferences
    val userId = sharedPreferences.getInt("USER_ID", 0)
    val userType = sharedPreferences.getString("USER_TYPE", "")
    val firstName = sharedPreferences.getString("FIRST_NAME", "")
    val lastName = sharedPreferences.getString("LAST_NAME", "")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberImagePainter(data = R.drawable.people_default),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(2.dp, Color.White, CircleShape)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "${ firstName.toString() } ${lastName}",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = userType.toString(),
            style = MaterialTheme.typography.body1,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* TODO: Handle edit profile */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Edit Profile")
        }
    }
}
