package com.example.foodcredit15.ui.internals

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color

data class SettingOption(
    val title: String,
    val description: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("App Settings", fontSize = 18.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(12.dp))

            SettingCard("Notifications", "Manage notification preferences")
            Spacer(modifier = Modifier.height(12.dp))
            SettingCard("Payment Methods", "Add or update payment info")
            Spacer(modifier = Modifier.height(12.dp))
            SettingCard("Account Security", "Change password and security")
        }
    }
}

@Composable
fun SettingCard(title: String, description: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(title, style = MaterialTheme.typography.titleMedium)
                Text(description, fontSize = 12.sp, color = Color.Gray)
            }

            Button(
                onClick = { /* TODO: Navigate to respective screen */ },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Configure")
            }
        }
    }
}
