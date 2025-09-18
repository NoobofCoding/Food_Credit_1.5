package com.example.foodcredit15.ui.internals

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import androidx.compose.runtime.Composable

// --- New Color Palette for Gradient and UI elements ---
val DarkerGreen = Color(0xFFf0fdf4)
val LighterGreen = Color(0xFFecfdf5)
val CardBackground = Color.White
val PrimaryAccent = Color(0xFF2ECC71)
val StreakChipColor = Color(0xFFD5F5E3)
val FireOrange = Color(0xFFFFA500)
val TextGrey = Color.Gray

@Composable
fun DashboardScreen(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawerContent(
                onDestinationClicked = { route ->
                    scope.launch { drawerState.close() }
                    // navController.navigate(route)
                }
            )
        },
        gesturesEnabled = true
    ) {
        DashboardContent(
            onMenuClick = {
                scope.launch {
                    drawerState.open()
                }
            }
        )
    }
}

// Custom Composable for the Weekly Streak Card
@Composable
fun WeeklyStreakCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocalFireDepartment, contentDescription = "Streak", tint = FireOrange)
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text("Weekly Streak", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text("Keep your streak going!", fontSize = 12.sp, color = TextGrey)
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.CalendarToday, contentDescription = "Calendar", tint = TextGrey)
                Spacer(modifier = Modifier.width(8.dp))
                DayOfWeekCircle("M", isActive = true)
                DayOfWeekCircle("T", isActive = true)
                DayOfWeekCircle("W", isActive = true)
                DayOfWeekCircle("T", isActive = false)
                DayOfWeekCircle("F", isActive = false)
                DayOfWeekCircle("S", isActive = false)
                DayOfWeekCircle("S", isActive = false)
            }
        }
    }
}

// Helper for the day circles
@Composable
fun DayOfWeekCircle(day: String, isActive: Boolean) {
    Box(
        modifier = Modifier
            .padding(2.dp)
            .size(20.dp)
            .clip(CircleShape)
            .background(if (isActive) PrimaryAccent else StreakChipColor),
        contentAlignment = Alignment.Center
    ) {
        Text(day, fontSize = 10.sp, color = if (isActive) Color.White else TextGrey)
    }
}

// Custom Composable for the Currency Bar
@Composable
fun CurrencyBar() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CurrencyItem(icon = Icons.Default.Eco, amount = "1", color = Color(0xFF2ECC71))
            CurrencyItem(icon = Icons.Default.Favorite, amount = "7", color = Color.Red)
            CurrencyItem(icon = Icons.Default.Adjust, amount = "10", color = TextGrey)
            CurrencyItem(icon = Icons.Default.Star, amount = "50", color = Color(0xFFFFC107))
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun RowScope.CurrencyItem(icon: androidx.compose.ui.graphics.vector.ImageVector, amount: String, color: Color) {
    Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(18.dp))
    Spacer(modifier = Modifier.width(4.dp))
    Text(amount, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    Spacer(modifier = Modifier.width(16.dp))
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardContent(onMenuClick: () -> Unit) {
    var manualCode by remember { mutableStateOf("") }

    // Define the gradient brush
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(DarkerGreen, LighterGreen)
    )

    // The Scaffold no longer needs a container color.
    // The background is applied to the main Column instead.
    Scaffold(containerColor = Color.Transparent) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = gradientBrush) // Apply the gradient here
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(onClick = onMenuClick) {
                    Icon(Icons.Outlined.GridView, contentDescription = "Menu")
                }
                WeeklyStreakCard(modifier = Modifier.weight(1f))
            }

            CurrencyBar()

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = CardBackground),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.QrCodeScanner, contentDescription = null, tint = PrimaryAccent)
                        Spacer(Modifier.width(8.dp))
                        Text("Personal QR Code", style = MaterialTheme.typography.titleMedium)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Icon(Icons.Default.QrCode, contentDescription = "QR Code", modifier = Modifier.size(150.dp))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("John Doe", fontWeight = FontWeight.Bold)
                    Text("ID: VDM_USER_12345", color = TextGrey, fontSize = 12.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        Button(
                            onClick = { /* Copy */ },
                            // Use a lighter, neutral color for buttons on the new background
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF0F0F0)),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(Icons.Default.ContentCopy, contentDescription = "Copy", tint = TextGrey)
                            Spacer(Modifier.width(8.dp))
                            Text("Copy", color = Color.Black)
                        }
                        Button(
                            onClick = { /* Share */ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF0F0F0)),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(Icons.Default.Share, contentDescription = "Share", tint = TextGrey)
                            Spacer(Modifier.width(8.dp))
                            Text("Share", color = Color.Black)
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        "💡 Scan this code at any vending machine to quickly access your account",
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        color = TextGrey
                    )
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = CardBackground),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Welcome to VendMint", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "🔍 Scan a QR code to get started • 48 total items available",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextGrey,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = CardBackground),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Enter Code Manually", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("If you can't scan, enter the machine code here.", style = MaterialTheme.typography.bodyMedium, color = TextGrey)
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = manualCode,
                        onValueChange = {},
                        label = { Text("Machine Code") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        shape = RoundedCornerShape(8.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = { /* Handle manual code connection logic here */ },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryAccent)
                    ) {
                        Text("Connect to Machine", color = Color.White, modifier = Modifier.padding(vertical = 8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun AppDrawerContent(onDestinationClicked: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(Color.White).padding(16.dp)) {
        Text("Navigation", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 12.dp))
        NavigationDrawerItem(
            label = { Text("Home") },
            selected = true,
            onClick = { onDestinationClicked("dashboard") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            colors = NavigationDrawerItemDefaults.colors(selectedContainerColor = StreakChipColor)
        )
        NavigationDrawerItem(
            label = { Text("Cart") },
            selected = false,
            onClick = { onDestinationClicked("cart") },
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cart") },
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.Transparent
            ),
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            label = { Text("Map") },
            selected = false,
            onClick = { onDestinationClicked("map") },
            icon = { Icon(Icons.Default.Map, contentDescription = "Map") },
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.Transparent
            ),
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            label = { Text("Account") },
            selected = false,
            onClick = { onDestinationClicked("account") },
            icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Account") },
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.Transparent
            ),
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            label = { Text("Settings") },
            selected = false,
            onClick = { onDestinationClicked("settings") },
            icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.Transparent
            ),
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
    }
}

@Preview(showBackground = true, widthDp = 380)
@Composable
fun DashboardPreview() {
    DashboardScreen(navController = rememberNavController())
}