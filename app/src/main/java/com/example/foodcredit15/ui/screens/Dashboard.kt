package com.example.foodcredit15.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.example.foodcredit15.R

// Data classes
data class Stat(
    val icon: Int,
    val value: String,
    val tint: Color
)

data class Product(
    val code: String,
    val name: String,
    val price: Int,
    val imageUrl: Int
)

@Composable
fun WeeklyStreakCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F2F1)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_flame),
                contentDescription = "Weekly Streak",
                modifier = Modifier.size(24.dp),
                tint = Color(0xFF4CAF50)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text("Weekly Streak", fontWeight = FontWeight.Bold)
                Text("Keep your streak going!", fontSize = 12.sp)
            }
            Text("3 days", fontWeight = FontWeight.Bold, color = Color(0xFF4CAF50))
        }
    }
}

@Composable
fun UserStatsCard() {
    val stats = listOf(
        Stat(R.drawable.ic_heart, "1", Color(0xFFF44336)),
        Stat(R.drawable.ic_coin, "10", Color(0xFFFFC107)),
        Stat(R.drawable.ic_food_credit, "50", Color(0xFF8BC34A)),
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            stats.forEach { stat ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(end = 12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = stat.icon),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = stat.tint
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(stat.value)
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { /* Add action */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = "Add",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun PersonalQRCodeCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Personal QR Code", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 16.dp))
            Image(
                painter = painterResource(id = R.drawable.qr_code_placeholder),
                contentDescription = "QR Code",
                modifier = Modifier
                    .size(150.dp)
                    .padding(bottom = 8.dp)
            )
            Text("John Doe", fontWeight = FontWeight.Bold)
            Text("ID: VDM_USER_12345", fontSize = 12.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = { /* Copy action */ }) {
                    Text("Copy")
                }
                TextButton(onClick = { /* Share action */ }) {
                    Text("Share")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Scan this code at any vending machine to quickly access your account",
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ProductsHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_bag),
            contentDescription = "Products",
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text("All Products", fontWeight = FontWeight.Bold)
            Text("Select an item to add to cart • 7 items available", fontSize = 12.sp)
        }
    }
}

@Composable
fun ProductsGrid() {
    val products = listOf(
        Product("A1", "Coca Cola", 3, R.drawable.coca_cola),
        Product("B1", "Chocolate Bar", 1, R.drawable.chocolate_bar),
        Product("A2", "Water Bottle", 2, R.drawable.water_bottle),
        Product("C1", "Chips", 2, R.drawable.chips)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.heightIn(max = 600.dp)
    ) {
        items(products) { product ->
            ProductCard(product)
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                product.code,
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier
                    .background(Color(0xFF4CAF50), RoundedCornerShape(4.dp))
                    .padding(horizontal = 8.dp, vertical = 2.dp)
                    .align(Alignment.Start)
            )
            Image(
                painter = painterResource(id = product.imageUrl),
                contentDescription = product.name,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(product.name, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_food_credit),
                    contentDescription = "Food Credit",
                    modifier = Modifier.size(16.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(product.price.toString())
            }
            Button(
                onClick = { /* Handle select */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Select")
            }
        }
    }
}

@Composable
fun TopBarWithDrawer(drawerState: DrawerState, scope: CoroutineScope) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { scope.launch { drawerState.open() } }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = "Menu",
                tint = Color.Black
            )
        }

        Row(
            modifier = Modifier
                .background(Color(0xFF4CAF50), RoundedCornerShape(16.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_flame),
                contentDescription = "Streak",
                modifier = Modifier.size(2.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text("3 days", color = Color.White, fontSize = 12.sp)
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_cart),
            contentDescription = "Cart",
            modifier = Modifier.size(24.dp),
            tint = Color.Black
        )
    }
}

@Composable
fun DrawerContent(
    userName: String,
    userInitials: String,
    onItemClick: (String) -> Unit,
    onLogout: () -> Unit
) {
    ModalDrawerSheet {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFDFF5E2)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(userInitials, fontWeight = FontWeight.Bold, color = Color.Black)
                }
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(userName, fontWeight = FontWeight.SemiBold, color = Color.Black)
                    Text("User ", fontSize = 13.sp, color = Color.Gray)
                }
            }

            Divider()

            DrawerMenuItem("Home", selected = true) { onItemClick("dashboard") }
            DrawerMenuItem("Account", selected = false) { onItemClick("accountscreen") }
            DrawerMenuItem("Order", selected = false) { onItemClick("orderscreen") }
            DrawerMenuItem("History", selected = false) { onItemClick("history") }
            DrawerMenuItem("Settings", selected = false) { onItemClick("settings") }

            Spacer(modifier = Modifier.weight(1f))

            OutlinedButton(
                onClick = onLogout,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red),
                border = ButtonDefaults.outlinedButtonBorder.copy(width = 1.dp, brush = SolidColor(Color.Red)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Logout", color = Color.Red)
            }

        }
    }
}

@Composable
fun DrawerMenuItem(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val bgColor = if (selected) Color(0xFFE8F5E9) else Color.Transparent
    val textColor = if (selected) Color(0xFF2B7A30) else Color.Black

    NavigationDrawerItem(
        label = { Text(text, color = textColor) },
        selected = selected,
        onClick = onClick,
        icon = null, // Add icons if needed
        modifier = Modifier.padding(horizontal = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = NavigationDrawerItemDefaults.colors(
            selectedContainerColor = bgColor,
            unselectedContainerColor = Color.Transparent
        )
    )
}

@Composable
fun DashboardScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                userName = "John Doe",
                userInitials = "JD",
                onItemClick = { route ->
                    scope.launch { drawerState.close() }
                    navController.navigate(route)
                },
                onLogout = { /* Handle logout */ }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF0F4F8))
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            TopBarWithDrawer(drawerState, scope)
            Spacer(modifier = Modifier.height(16.dp))
            WeeklyStreakCard()
            Spacer(modifier = Modifier.height(16.dp))
            UserStatsCard()
            Spacer(modifier = Modifier.height(16.dp))
            PersonalQRCodeCard()
            Spacer(modifier = Modifier.height(16.dp))
            ProductsHeader()
            Spacer(modifier = Modifier.height(8.dp))
            ProductsGrid()
        }
    }
}
