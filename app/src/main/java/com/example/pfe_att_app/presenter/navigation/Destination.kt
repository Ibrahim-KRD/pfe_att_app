package com.example.pfe_att_app.presenter.navigation

sealed class Destination(val route: String) {


    //screens
    object Login : Destination("login")
    object Register : Destination("register")
    object Schedule : Destination("schedule")
    object Modules : Destination("modules")
    object ClassDetails : Destination("classDetails")
    object AttendenceInformation : Destination("attendenceInformation")
    object ModuleDetailsPage : Destination("moduleDetails")
    object AddNewSessionPage : Destination("newSessoin")

    object Root : Destination("root")
    object Authentication : Destination("authentication")
    object Main : Destination("main")









}
