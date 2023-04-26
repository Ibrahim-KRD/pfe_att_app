package com.example.pfe_att_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController

import com.example.pfe_att_app.presenter.navigation.RootNavigationGraph
import com.example.pfe_att_app.presenter.pages.schedule.ScheduleViewModel

import com.example.pfe_att_app.ui.theme.Pfe_att_appTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pfe_att_appTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    RootNavigationGraph(navHostController = rememberNavController())


                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello here $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Pfe_att_appTheme {
        Greeting("Android")
    }
}