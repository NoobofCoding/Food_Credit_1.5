package com.example.foodcredit15.network

import com.google.gson.annotations.SerializedName

// ---------------- Users ----------------
data class UserResponse(
    @SerializedName("user_id") val userId: Int,
    val name: String,
    val email: String,
    @SerializedName("password_hash") val passwordHash: String,
    @SerializedName("qr_code") val qrCode: String,
    val points: Int,
    @SerializedName("created_at") val createdAt: String
)

// ---------------- Employees ----------------
data class EmployeeResponse(
    @SerializedName("employee_id") val employeeId: Int,
    val name: String,
    val email: String,
    @SerializedName("password_hash") val passwordHash: String,
    @SerializedName("created_at") val createdAt: String
)

// ---------------- Dustbins ----------------
data class DustbinResponse(
    @SerializedName("dustbin_id") val dustbinId: Int,
    val location: String,
    val capacity: Int,
    val status: String
)

// ---------------- TrashLogs ----------------
data class TrashLogResponse(
    @SerializedName("log_id") val logId: Int,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("dustbin_id") val dustbinId: Int,
    val weight: Int,
    @SerializedName("trash_type") val trashType: String,
    @SerializedName("created_at") val createdAt: String
)

// ---------------- VendingMachines ----------------
data class VendingMachineResponse(
    @SerializedName("machine_id") val machineId: Int,
    val location: String,
    val status: String
)

// ---------------- Items ----------------
data class ItemResponse(
    @SerializedName("item_id") val itemId: Int,
    val name: String,
    val points: Int,
    val stock: Int
)

// ---------------- Redemptions ----------------
data class RedemptionResponse(
    @SerializedName("redemption_id") val redemptionId: Int,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("item_id") val itemId: Int,
    val quantity: Int,
    @SerializedName("created_at") val createdAt: String
)

// ---------------- Tasks ----------------
data class TaskResponse(
    @SerializedName("task_id") val taskId: Int,
    @SerializedName("employee_id") val employeeId: Int,
    val description: String,
    val status: String
)

// ---------------- Refills ----------------
data class RefillResponse(
    @SerializedName("refill_id") val refillId: Int,
    @SerializedName("vending_machine_id") val vendingMachineId: Int,
    @SerializedName("item_id") val itemId: Int,
    val quantity: Int,
    @SerializedName("created_at") val createdAt: String
)
