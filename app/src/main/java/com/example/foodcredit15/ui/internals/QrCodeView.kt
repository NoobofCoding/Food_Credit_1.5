package com.example.foodcredit15.ui.internals

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.Modifier
import com.example.foodcredit15.utils.QrCodeGenerator

@Composable
fun QrCodeView(data: String, modifier: Modifier = Modifier) {
    val bmp = remember(data) { QrCodeGenerator.generateQrCode(data) }
    bmp?.let {
        Image(bitmap = it.asImageBitmap(), contentDescription = "QR Code", modifier = modifier)
    }
}
