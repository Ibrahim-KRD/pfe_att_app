package com.example.pfe_att_app.presenter.pages.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pfe_att_app.R
import com.example.pfe_att_app.domain.entities.Student
import com.example.pfe_att_app.domain.entities.Teacher
import com.example.pfe_att_app.presenter.navigation.Destination

@Composable
fun RegisterPage(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel = hiltViewModel()
) {


    var firstName = remember { mutableStateOf("") }


    var lastName = remember { mutableStateOf("") }
    var adress = remember { mutableStateOf("") }
    var subscritionYear = remember { mutableStateOf("") }
    var speciality = remember { mutableStateOf("") }
    var role = remember { mutableStateOf("") }
    var email = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    var matricul = remember { mutableStateOf("") }
    var level = remember { mutableStateOf("") }
    var group = remember { mutableStateOf("") }
    var phoneNumber = remember { mutableStateOf("") }


    var student = Student(
        firstName = firstName.value,
        lastName = lastName.value,
        adress = adress.value,
        subscriptionYear = subscritionYear.value,
        speciality = speciality.value,

        email = email.value,
        password = password.value,
        group = group.value,
        matricule = matricul.value,
        level = level.value,
        phone = phoneNumber.value

    )

    var teacher = Teacher(
        firstName = firstName.value,
        lastName = lastName.value,
        adress = adress.value,
        subscriptionYear = subscritionYear.value,
        speciality = speciality.value,
        role = role.value,
        email = email.value,
        password = password.value,
        phone = phoneNumber.value
    )


    var selectedTab by remember { mutableStateOf(0) }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        RegisterContent(
        navController =     navController,
      selectedTab =       selectedTab,
        authenticationViewModel =     authenticationViewModel,
        student =     student,
            teacher = teacher,
         firstName =    firstName,
           lastName =  lastName,
        speciality =     speciality,
        adress =     adress,
        subscritionYear =     subscritionYear,
       role =      role,
        email =     email,
       password =      password,
         level =    level,
          group =   group,
         matricul =    matricul,
phoneNumber = phoneNumber
            )
        TabRow(
            selectedTabIndex = selectedTab,
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                icon = { Icon(Icons.Default.Person, contentDescription = "Student") },
                text = { Text("Student") }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                icon = { Icon(Icons.Default.Build, contentDescription = "Teacher") },
                text = { Text("Teacher") }
            )
        }
    }
}

@Composable
fun RegisterContent(
    navController: NavController,
    selectedTab: Int,
    authenticationViewModel: AuthenticationViewModel,
    student: Student,
    teacher: Teacher,
    firstName: MutableState<String>,
    lastName: MutableState<String>,
    adress: MutableState<String>,
    subscritionYear: MutableState<String>,
    role: MutableState<String>,
    email: MutableState<String>,
    password: MutableState<String>,
    level: MutableState<String>,
    speciality: MutableState<String>,
    matricul: MutableState<String>,
    group: MutableState<String>,
    phoneNumber: MutableState<String>


    ) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(55.dp))
            RegisterHeader(selectedTab)
            Spacer(modifier = Modifier.height(32.dp))
            if (selectedTab == 0) {
                StudentInfoSection(
                    firstName = firstName,
                    lastName = lastName,
                    adress = adress,
                    level = level,
                    group = group,
                    subscritionYear = subscritionYear,
                    speciality = speciality,
                    matricul = matricul,
                    email = email,
                    phoneNumber = phoneNumber
                )
            } else {
                TeacherInfoSection(
                    firstName = firstName,
                    lastName = lastName,
                    adress = adress,

                    subscritionYear = subscritionYear,
                    role = role,
                    email = email,
                    speciality = speciality
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            SecurityInfoSection(password)

            Spacer(modifier = Modifier.height(32.dp))
            RegisterButton(selectedTab) {
                if (selectedTab == 0) {
                    authenticationViewModel.StudentRegister(
                        student
                    )
                } else {
                    authenticationViewModel.TeacherRegister(
                        teacher
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            LogInButton(onLogIn = {
                navController.navigate(Destination.Login.route)
            })
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun RegisterHeader(selectedTab: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.app_logo_big),
            contentDescription = "App Logo",
            modifier = Modifier.size(80.dp)
        )
        Text(
            text = when (selectedTab) {
                0 -> "Student Register"
                else -> "Teacher Register"
            },
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun StudentInfoSection(
    firstName: MutableState<String>,
    lastName: MutableState<String>,
    adress: MutableState<String>,
    level: MutableState<String>,
    email: MutableState<String>,
    group: MutableState<String>,
    subscritionYear: MutableState<String>,
    phoneNumber: MutableState<String>,

    speciality: MutableState<String>,
    matricul: MutableState<String>
) {


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Student Information",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = firstName.value,
            onValueChange = { firstName.value = it },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = lastName.value,
            onValueChange = { lastName.value = it },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = phoneNumber.value,
            onValueChange = {phoneNumber.value = it},
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = adress.value,
            onValueChange = { adress.value = it },
            label = { Text("Address") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = subscritionYear.value,
            onValueChange = { subscritionYear.value = it },
            label = { Text("Subscription Year") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = matricul.value,
            onValueChange = { matricul.value = it },
            label = { Text("Matricule") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = level.value,
            onValueChange = { level.value = it },
            label = { Text("Level") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = group.value,
            onValueChange = { group.value = it },
            label = { Text("Group") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = speciality.value,
            onValueChange = { speciality.value = it },
            label = { Text("Specialty") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun TeacherInfoSection(
    firstName: MutableState<String>,
    lastName: MutableState<String>,
    adress: MutableState<String>,

    subscritionYear: MutableState<String>,
    email: MutableState<String>,
    role: MutableState<String>,

    speciality: MutableState<String>,
) {


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Teacher Information",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = firstName.value,
            onValueChange = { firstName.value = it },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = lastName.value,
            onValueChange = { lastName.value = it },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = adress.value,
            onValueChange = { adress.value = it },
            label = { Text("Address") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = subscritionYear.value,
            onValueChange = { subscritionYear.value = it },
            label = { Text("Subscription Year") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = speciality.value,
            onValueChange = { speciality.value = it },
            label = { Text("Speciality") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = role.value,
            onValueChange = { role.value = it },
            label = { Text("Role") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun SecurityInfoSection(password: MutableState<String>) {
var password_confirmation = remember { mutableStateOf("")}
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Security Information",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password_confirmation.value,
            onValueChange = {password_confirmation.value = it},
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun RegisterButton(selectedTab: Int, onRegister: () -> Unit) {
    Button(
        onClick = onRegister,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary
        )
    ) {
        Text(
            text = when (selectedTab) {
                0 -> "Register as Student"
                else -> "Register as Teacher"
            },
            color = MaterialTheme.colors.onPrimary,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}

@Composable
fun LogInButton(onLogIn: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 24.dp)) {
        Text(text = "Already have an account? ")
        TextButton(
            onClick = onLogIn,
        ) {
            Text(
                text = "Log In",
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}
