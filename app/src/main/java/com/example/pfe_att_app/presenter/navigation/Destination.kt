package com.example.pfe_att_app.presenter.navigation

sealed class Destination(val route: String) {


    //region teacher screens
    object Login : Destination("login")
    object Register : Destination("register")
    object Schedule : Destination("schedule")
    object Modules : Destination("modules")
    object ClassDetails : Destination("classDetails")
    object AttendenceInformation : Destination("attendenceInformation")
    object ModuleDetailsPage : Destination("moduleDetails")
    object AddNewSessionPage : Destination("newSessoin")
    object AddNewModule : Destination("new_module")
    object SearchPage : Destination("search_page")
    object ProfilePage : Destination("profile_page")


//endregion


    //region student screen
    object StudentSchedule : Destination("student_schedule")
    object StudentAttendqnceInformation : Destination("student_attendence_information")
    object QrCodeScannerPage : Destination("qr_code_scanner_page")

    //endregion
    object Root : Destination("root")
    object Authentication : Destination("authentication")
    object Main : Destination("main")
    object StudenMainGraph : Destination("student_main_graph")










}
