package com.example.foodcredit15.network

import com.google.gson.annotations.SerializedName

// ---------------- Users ----------------
data class UserRequest(
    val name: String,
    val email: String,
    @SerializedName("password_hash") val passwordHash: String,
    @SerializedName("qr_code") val qrCode: String
)

// ---------------- Employees ----------------
data class EmployeeRequest(
    val name: String,
    val email: String,
    @SerializedName("password_hash") val passwordHash: String
)

// ---------------- Dustbins ----------------
data class DustbinRequest(
    val location: String,
    val capacity: Int,
    val status: String
)

// ---------------- TrashLogs ----------------
data class TrashLogRequest(
    @SerializedName("user_id") val userId: Int,
    @SerializedName("dustbin_id") val dustbinId: Int,
    val weight: Int,
    @SerializedName("trash_type") val trashType: String
)

// ---------------- VendingMachines ----------------
data class VendingMachineRequest(
    val location: String,
    val status: String
)

// ---------------- Items ----------------
data class ItemRequest(
    val name: String,
    val points: Int,
    val stock: Int
)

// ---------------- Redemptions ----------------
data class RedemptionRequest(
    @SerializedName("user_id") val userId: Int,
    @SerializedName("item_id") val itemId: Int,
    val quantity: Int
)

// ---------------- Tasks ----------------
data class TaskRequest(
    @SerializedName("employee_id") val employeeId: Int,
    val description: String,
    val status: String
)

// ---------------- Refills ----------------
data class RefillRequest(
    @SerializedName("vending_machine_id") val vendingMachineId: Int,
    @SerializedName("item_id") val itemId: Int,
    val quantity: Int
)
//---------------- Login Request -----------------
data class LoginRequest(
    val email: String,
    val password: String
)
