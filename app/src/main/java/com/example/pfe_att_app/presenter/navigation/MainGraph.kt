package com.example.pfe_att_app.presenter.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.pfe_att_app.presenter.pages.attendence.AttendenceInformationPage
import com.example.pfe_att_app.presenter.pages.modules.ModuleDetailsScreen
import com.example.pfe_att_app.presenter.pages.modules.ModulesPage
import com.example.pfe_att_app.presenter.pages.schedule.ClassDetailsPage
import com.example.pfe_att_app.presenter.pages.schedule.SchedulePage
import com.example.pfe_att_app.presenter.pages.schedule.ScheduleViewModel

fun NavGraphBuilder.MainGraph(navController: NavController) {

    navigation(

        route = Destination.Main.route,
        startDestination = Destination.Schedule.route,

    ) {
        composable(route = Destination.Schedule.route) {

            SchedulePage(navController)

        }

        composable(route = Destination.ClassDetails.route + "/{seance_id}",
            arguments = listOf(
                navArgument("seance_id"){
                    type = NavType.StringType
                    defaultValue = "1"
                    nullable=true
                }
            )

        ) {entry ->
            ClassDetailsPage( seance_id = entry.arguments?.getString("enrollement_id"),navController)
        }
        composable(route = Destination.AttendenceInformation.route,
            arguments = listOf(
            navArgument("enrollement_id"){
                type = NavType.StringType
                defaultValue = "1"
                nullable=true
            }
        ))
        {entry ->
            AttendenceInformationPage(enrollement_id = entry.arguments?.getString("enrollement_id"),
                navController)
        }


        composable(route = Destination.Modules.route) {
            ModulesPage(navController)
        }


        composable(route = Destination.ModuleDetailsPage.route) {
            ModuleDetailsScreen(navController)
        }
    }

}