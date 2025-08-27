package com.example.foodcredit15.ui.internals

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import kotlinx.coroutines.launch

/* ------------------------- DASHBOARD ------------------------- */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController,
                    userName: String = "Samson", userInitials: String = "SAM"
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                userName = userName,
                userInitials = userInitials,
                onItemClick = { route ->
                    scope.launch { drawerState.close() } // close drawer first
                    navController.navigate(route)       // then navigate
                },
                onLogout = { navController.navigate("userLogin") }
            )
        }
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Home", fontWeight = FontWeight.SemiBold) },
                    navigationIcon = {
                        Box(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFDFF5E2)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(userInitials, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    },
                    actions = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { padding ->
            DashboardContent(modifier = Modifier.padding(padding))
        }
    }
}

/* ------------------------- MAIN DASHBOARD CONTENT ------------------------- */

@Composable
fun DashboardContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF0F0F0)) // Light gray background to match the style
            .padding(12.dp)
    ) {
        // Row of three cards
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            DashboardCard(
                title = "Available Credit",
                value = "INR 125.50",
                valueColor = Color(0xFF2B7A30),
                icon = Icons.Default.CreditCard
            )
            DashboardCard(
                title = "Total Orders",
                value = "23",
                valueColor = Color.Black,
                icon = Icons.Default.ShoppingCart
            )
            DashboardCard(
                title = "Rewards Points",
                value = "1,250",
                valueColor = Color(0xFFE65100),
                icon = Icons.Default.Star
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Nearby Vending Machines Button
        Button(
            onClick = { /* TODO: Navigate to vending machines map */ },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location Icon",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Nearby Vending Machines", color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Recent Activity Section with Card
        Text("Recent Activity", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFFD3D3D3), RoundedCornerShape(16.dp))
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
            ) {
                ActivityItem(
                    title = "Order Placed",
                    amount = "-INR 120.50",
                    amountColor = Color.Red,
                    subtitle = "Downtown Vending",
                    time = "2h ago"
                )
                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp, modifier = Modifier.padding(vertical = 12.dp))

                ActivityItem(
                    title = "Credit Added",
                    amount = "+INR 100.00",
                    amountColor = Color(0xFF2B7A30),
                    subtitle = "Monthly allowance",
                    time = "3d ago"
                )
                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp, modifier = Modifier.padding(vertical = 12.dp))

                ActivityItem(
                    title = "Order Completed",
                    amount = "-INR 34.75",
                    amountColor = Color.Red,
                    subtitle = "Fresh Market Vending",
                    time = "1w ago"
                )
            }
        }
    }
}

@Composable
fun DashboardCard(title: String, value: String, valueColor: Color, icon: ImageVector) {
    Card(
        modifier = Modifier
            .height(110.dp)
            .border(1.dp, Color(0xFFD3D3D3), RoundedCornerShape(12.dp)), // Added border
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White), // Set card color to white
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(title, fontSize = 13.sp, color = Color.Gray)
            Text(value, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = valueColor)
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFEAF2EF)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = valueColor, modifier = Modifier.size(18.dp))
            }
        }
    }
}

@Composable
fun ActivityItem(title: String, amount: String, amountColor: Color, subtitle: String, time: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(title, fontWeight = FontWeight.SemiBold)
            Text(subtitle, fontSize = 12.sp, color = Color.Gray)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(amount, fontWeight = FontWeight.SemiBold, color = amountColor)
            Text(time, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

/* ------------------------- DRAWER CONTENT ------------------------- */

@Composable
fun DrawerContent(
    userName: String,
    userInitials: String,
    onItemClick: (String) -> Unit,
    onLogout: () -> Unit
) {
    ModalDrawerSheet {
        Column(modifier = Modifier.fillMaxSize()) {
            // Header
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
                    Text(userInitials, fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(userName, fontWeight = FontWeight.SemiBold)
                    Text("User", fontSize = 13.sp, color = Color.Gray)
                }
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
            }

            Divider()

            DrawerMenuItem("Home", Icons.Default.Home, true) { onItemClick("dashboard") }
            DrawerMenuItem("Account", Icons.Default.Person, false) { onItemClick("accountscreen") }
            DrawerMenuItem("Order", Icons.Default.Restaurant, false) { onItemClick("orderscreen") }
            DrawerMenuItem("History", Icons.Default.History, false) { onItemClick("history") }
            DrawerMenuItem("Settings", Icons.Default.Settings, false) { onItemClick("settings") }

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
                Icon(Icons.Default.ExitToApp,
                    contentDescription = null,
                    tint = Color.Red)
                Spacer(Modifier.width(6.dp))
                Text("Logout", color = Color.Red)
            }
        }
    }
}

@Composable
fun DrawerMenuItem(
    text: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    val bgColor = if (selected) Color(0xFF2B7A30) else Color.Transparent
    val textColor = if (selected) Color.White else Color.Black

    NavigationDrawerItem(
        label = { Text(text, color = textColor) },
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(icon, contentDescription = text, tint = if (selected) Color.White else Color.Black)
        },
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor)
    )
}

/* ------------------------- PREVIEW ------------------------- */

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardPreview() {
    // Preview can’t use real navController, so left blank
}