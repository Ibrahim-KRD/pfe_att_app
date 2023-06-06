package com.example.pfe_att_app.presenter.pages.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
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
import com.example.pfe_att_app.domain.entities.Teacher
import com.example.pfe_att_app.presenter.navigation.Destination

@Composable
fun RegisterPage(navController: NavController,

) {
    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        RegisterContent(navController)
    }

}



@Composable
fun RegisterScreen() {
 }


@Composable
fun RegisterContent(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel = hiltViewModel()
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
            Spacer(modifier = Modifier.height(32.dp))
            RegisterHeader()
            Spacer(modifier = Modifier.height(32.dp))
            PersonalInfoSection()
            Spacer(modifier = Modifier.height(32.dp))
            CareerInfoSection()
            Spacer(modifier = Modifier.height(32.dp))
            SecurityInfoSection()
            Spacer(modifier = Modifier.height(32.dp))
            RegisterButton({
                authenticationViewModel.register(
                    Teacher(
                        "John",
                        "Doe",
                        "123 Main St",
                        "2022",
                        "Mathematics",
                        "Professor"
                    )

                )
            })
            Spacer(modifier = Modifier.height(16.dp))
            LogInButton(onLogIn = {

                navController.navigate(Destination.Login.route)
            })
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun RegisterHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_logo_big),
            contentDescription = "App Logo",
            modifier = Modifier.size(80.dp)
        )
        Text(
            text = "Register",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun PersonalInfoSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Personal Information",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
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
            value = "",
            onValueChange = {},
            label = { Text("Address") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Composable
fun CareerInfoSection(

) {
    Column(  horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "CAREER INFORMATION", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = "matricule",
            onValueChange = {  },
            label = { Text("Matricule") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = "specialty",
            onValueChange = {  },
            label = { Text("Specialty") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun SecurityInfoSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Security Information",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Composable
fun RegisterButton(onRegister: () -> Unit) {
    Button(
        onClick =  onRegister ,
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
            text = "Register",
            color = MaterialTheme.colors.onPrimary,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}

@Composable
fun LogInButton(onLogIn: () -> Unit) {
    Row (verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 24.dp)){
        Text(text = "you have an account ? ")
        TextButton(
            onClick =  onLogIn ,

        ) {
            Text(
                text = "LogIn",
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}