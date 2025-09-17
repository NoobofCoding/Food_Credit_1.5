package com.example.foodcredit15.ui.viewmodels

data class Product(
    val code: String,   // ✅ Matches "selectedProduct.code"
    val name: String,   // ✅ Matches "selectedProduct.name"
    val price: Int,     // ✅ Matches "selectedProduct.price"
    val imageUrl: Int   // ✅ Matches "selectedProduct.imageUrl"
)


