package com.example.foodcredit15.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.foodcredit15.R
import com.example.foodcredit15.ui.viewmodels.LoginViewModelFactory
import com.example.foodcredit15.ui.viewmodels.LoginViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foodcredit15.network.ApiService
import com.example.foodcredit15.ui.internals.QrCodeView
import com.example.foodcredit15.ui.viewmodels.LoginUiState

@Composable
fun UserLoginScreen(
    navController: NavController,
    api: ApiService
) {
    val viewModel: LoginViewModel = viewModel(
        factory = LoginViewModelFactory(api)
    )
    val uiState by viewModel.uiState.collectAsState()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE8F5E9))
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.arrow),
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(60.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Food Credit",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = "Recycle, Redeem, Relish",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 16.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Welcome back",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text(
                    text = "Sign in to your account",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = rememberMe,
                            onCheckedChange = { rememberMe = it },
                            colors = CheckboxDefaults.colors(checkedColor = Color(0xFF1EB980))
                        )
                        Text(text = "Remember me", fontSize = 14.sp)
                    }
                    Text(
                        text = "Forgot password?",
                        fontSize = 14.sp,
                        color = Color(0xFF1EB980),
                        modifier = Modifier.clickable { /* Handle forgot password */ }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { viewModel.login(email, password) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1EB980)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Sign In", color = Color.White, modifier = Modifier.padding(vertical = 4.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))

                when (val state = uiState) {
                    is LoginUiState.Loading -> CircularProgressIndicator()
                    is LoginUiState.Success -> {
                        val user = state.user
                        Text("Welcome ${user.name}!", fontWeight = FontWeight.Bold)
                        QrCodeView(data = user.qrCode)

                        Button(onClick = {
                            navController.navigate("dashboard") {
                                popUpTo("userLogin") { inclusive = true }
                            }
                        }) {
                            Text("Continue")
                        }
                    }
                    is LoginUiState.Error -> Text("Error: ${state.message}", color = Color.Red)
                    else -> {}
                }

                Text(
                    text = "Don't have an account? Sign up",
                    fontSize = 14.sp,
                    color = Color(0xFF1EB980),
                    modifier = Modifier.clickable { navController.navigate("registration") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Or continue with", fontSize = 14.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(
                        onClick = { /* Google login */ },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.google),
                            contentDescription = "Google Sign In",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Google", color = Color.Black)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    OutlinedButton(
                        onClick = { /* Facebook login */ },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.facebook),
                            contentDescription = "Facebook Sign In",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Facebook", color = Color.Black)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "By signing in, you agree to our\nTerms of Service and Privacy Policy",
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}
