package com.example.foodcredit15.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodcredit15.R // Assuming your logo and arrow are in drawables


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(navController: NavController) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Select your gender") }
    val genderOptions = listOf("Select your gender", "Male", "Female", "Other")
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var confirmPasswordVisibility by remember { mutableStateOf(false) }
    var termsAccepted by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    val greenBg = Color(0xFFE8F5E9)
    val darkGreen = Color(0xFF4CAF50)

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

            }
        },
        containerColor = greenBg
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Food Credit Logo",
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Food Credit",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = "Your smart food companion",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Create Account",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "Sign up for your user account",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = fullName,
                        onValueChange = { fullName = it },
                        label = { Text("Full Name") },
                        placeholder = { Text("Enter your full name") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        placeholder = { Text("Enter your email") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        label = { Text("Phone Number") },
                        placeholder = { Text("Enter your phone number") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = gender,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Gender") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor()
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            genderOptions.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option) },
                                    onClick = {
                                        gender = option
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        placeholder = { Text("Create a password") },
                        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            val image = if (passwordVisibility)
                                Icons.Filled.Visibility
                            else Icons.Filled.VisibilityOff

                            IconButton(onClick = {
                                passwordVisibility = !passwordVisibility
                            }) {
                                Icon(imageVector = image, "Toggle password visibility")
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = { Text("Confirm Password") },
                        placeholder = { Text("Confirm your password") },
                        visualTransformation = if (confirmPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            val image = if (confirmPasswordVisibility)
                                Icons.Filled.Visibility
                            else Icons.Filled.VisibilityOff

                            IconButton(onClick = {
                                confirmPasswordVisibility = !confirmPasswordVisibility
                            }) {
                                Icon(imageVector = image, "Toggle confirm password visibility")
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = termsAccepted,
                            onCheckedChange = { termsAccepted = it },
                            colors = CheckboxDefaults.colors(checkedColor = darkGreen)
                        )
                        Text(
                            text = buildAnnotatedString {
                                append("I agree to the ")
                                withStyle(style = SpanStyle(color = Color(0xFF1EB980), fontWeight = FontWeight.Bold)) {
                                    append("Terms of Service")
                                }
                                append(" and ")
                                withStyle(style = SpanStyle(color = Color(0xFF1EB980), fontWeight = FontWeight.Bold)) {
                                    append("Privacy Policy")
                                }
                            },
                            fontSize = 13.sp,
                            modifier = Modifier.clickable { /* Handle link clicks */ }
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            println("Account created")
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1EB980)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Create Account", color = Color.White, modifier = Modifier.padding(vertical = 4.dp))
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = buildAnnotatedString {
                            append("Already have an account? ")
                            withStyle(style = SpanStyle(color = Color(0xFF1EB980), fontWeight = FontWeight.Bold)) {
                                append("Sign in")
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                navController.navigate("userLogin")
                            }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreen(navController = rememberNavController())
}