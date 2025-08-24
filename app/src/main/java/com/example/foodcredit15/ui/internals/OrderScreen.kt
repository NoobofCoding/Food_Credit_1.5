package com.example.foodcredit15.ui.internals

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

/* ------------------------- DATA CLASS ------------------------- */
data class FoodItem(
    val name: String,
    val price: String,
    val rating: Double
)

/* ------------------------- ORDER SCREEN ------------------------- */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(
    navController: NavController,
    userInitials: String = "SAM"
) {
    val foodItems = listOf(
        FoodItem("Water Bottle", "INR 1.50", 4.5),
        FoodItem("Soft Drink", "INR 2.00", 4.2),
        FoodItem("Burger", "INR 5.00", 4.8),
        FoodItem("Pizza Slice", "INR 3.50", 4.6)
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Order", fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        IconButton(onClick = { /* Drawer action */ }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                        Box(
                            modifier = Modifier
                                .padding(start = 4.dp)
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFDFF5E2)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(userInitials, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
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
                .padding(12.dp)
        ) {
            Text("Popular Items", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(foodItems) { item ->
                    FoodItemCard(item)
                }
            }
        }
    }
}

/* ------------------------- FOOD CARD ------------------------- */
@Composable
fun FoodItemCard(item: FoodItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Placeholder image box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF1F1F1)),
                contentAlignment = Alignment.Center
            ) {
                Text("🍴", fontSize = 28.sp, color = Color.Gray)
            }

            Text(item.name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(item.price, fontWeight = FontWeight.SemiBold, color = Color(0xFF2B7A30))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, contentDescription = "Rating", tint = Color(0xFFFFC107))
                    Text(item.rating.toString(), fontSize = 13.sp)
                }

                Button(
                    onClick = { /* Add to cart logic */ },
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2B7A30))
                ) {
                    Text("Add to Cart", fontSize = 13.sp, color = Color.White)
                }
            }
        }
    }
}
