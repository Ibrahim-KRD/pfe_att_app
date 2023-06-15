package com.example.pfe_att_app.presenter.navigation

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost




@Composable
fun RootNavigationGraph(navHostController: NavHostController){

    NavHost(navController =navHostController , route = Destination.Root.route,
    startDestination = Destination.Authentication.route
        ){

        AuthenticationGraph(navHostController)

        MainGraph(navHostController)

        StudentMainGraph(navHostController)

    }
}
