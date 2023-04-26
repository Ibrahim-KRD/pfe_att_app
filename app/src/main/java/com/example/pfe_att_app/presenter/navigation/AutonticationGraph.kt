package com.example.pfe_att_app.presenter.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

import com.example.pfe_att_app.presenter.pages.authentication.LoginPage

import com.example.pfe_att_app.presenter.pages.authentication.RegisterPage
import com.example.pfe_att_app.presenter.pages.schedule.ScheduleViewModel


fun NavGraphBuilder.AuthenticationGraph(navHostController: NavHostController){

    navigation(
        route = Destination.Authentication.route,
        startDestination = Destination.Login.route
    ){
        composable(route = Destination.Login.route){
            LoginPage(navController = navHostController)
        }

        composable(route = Destination.Register.route){
            RegisterPage(navController = navHostController)
        }



    }


            }