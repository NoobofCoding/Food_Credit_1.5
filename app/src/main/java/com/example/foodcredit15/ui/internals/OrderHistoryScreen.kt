package com.example.foodcredit15.ui.internals

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Data model for orders
data class Order(
    val name: String,
    val date: String,
    val orderId: String,
    val price: String,
    val status: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderHistoryScreen(orders: List<Order>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("History") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("Order History", fontSize = 18.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(12.dp))

            orders.forEach { order ->
                OrderCard(order)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun OrderCard(order: Order) {
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
                Text(order.name, style = MaterialTheme.typography.titleMedium)
                Text(order.date, fontSize = 12.sp, color = Color.Gray)
                Text("Order ID: ${order.orderId}", fontSize = 12.sp, color = Color.Gray)
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(order.price, fontSize = 16.sp, color = Color.Black)
                Text(
                    order.status,
                    fontSize = 12.sp,
                    color = Color(0xFF4CAF50) // Green color
                )
            }
        }
    }
}
