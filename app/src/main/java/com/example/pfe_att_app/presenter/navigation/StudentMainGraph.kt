package com.example.pfe_att_app.presenter.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.pfe_att_app.presenter.student_pages.AttendenceInformationPage
import com.example.pfe_att_app.presenter.student_pages.QrCodeScannerPage
import com.example.pfe_att_app.presenter.student_pages.SchedulePage

fun NavGraphBuilder.StudentMainGraph(

    navController: NavController
) {
    navigation(
        route = Destination.StudenMainGraph.route,
        startDestination = Destination.StudentSchedule.route
    ) {

        composable(route = Destination.QrCodeScannerPage.route + "/{enrollment_id}",
            arguments = listOf(
                navArgument("enrollment_id") {
                    type = NavType.StringType
                    defaultValue = "1"
                    nullable = true
                }
            )) { entry ->
            QrCodeScannerPage(
                enrollment_id = entry.arguments?.getString("enrollment_id"),
                navController = navController
            )
        }

        composable(route = Destination.StudentSchedule.route) {
            SchedulePage(navController)
        }

        composable(route = Destination.StudentAttendqnceInformation.route + "/{student_id}/{seance_id}",
            arguments = listOf(
                navArgument("student_id") {
                    type = NavType.StringType
                    defaultValue = "1"
                    nullable = true
                },
                navArgument("seance_id") {
                    type = NavType.StringType
                    defaultValue = "1"
                    nullable = true
                }
            ))
        { entry ->
            AttendenceInformationPage(
                student_id = entry.arguments?.getString("student_id"),
                seance_id = entry.arguments?.getString("seance_id"),
                navController
            )
        }
    }
}