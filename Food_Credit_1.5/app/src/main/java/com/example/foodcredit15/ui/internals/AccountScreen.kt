package com.example.foodcredit15.ui.internals

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

/* ------------------------- ACCOUNT SCREEN ------------------------- */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    navController: NavController?,
    userInitials: String = "SAM",
    userName: String = "Samson",
    email: String = "samson@email.com",
    phone: String = "+1 (555) 123-4567",
    memberSince: String = "March 2024",
    premium: Boolean = true
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Account", fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    // ✅ Back button to navigate back to Dashboard
                    IconButton(onClick = { navController?.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            // Profile Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("Profile Information", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)

                    // Row with profile avatar + details
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFDFF5E2)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(userInitials, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        }
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(userName, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text("Member since $memberSince", fontSize = 13.sp, color = Color.Gray)
                            if (premium) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(Color(0xFFDFF5E2))
                                        .padding(horizontal = 8.dp, vertical = 2.dp)
                                ) {
                                    Text("Premium Member", fontSize = 12.sp, color = Color(0xFF2B7A30))
                                }
                            }
                        }
                    }

                    // Email Section
                    Column {
                        Text("Email", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                        OutlinedTextField(
                            value = email,
                            onValueChange = {},
                            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                            readOnly = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            enabled = false
                        )
                    }

                    // Phone Section
                    Column {
                        Text("Phone", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                        OutlinedTextField(
                            value = phone,
                            onValueChange = {},
                            leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null) },
                            readOnly = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            enabled = false
                        )
                    }
                }
            }
        }
    }
}

/* ------------------------- PREVIEW ------------------------- */

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AccountPreview() {
    AccountScreen(
        navController = null // ✅ null in preview to avoid navigation errors
    )
}
