package com.example.foodcredit15.ui.internals

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.Modifier
import com.example.foodcredit15.utils.QrCodeGenerator

@Composable
fun QrCodeView(data: String?, modifier: Modifier = Modifier) {
    // Only generate QR if data is not null or blank
    val bmp = remember(data) {
        data?.takeIf { it.isNotBlank() }?.let { QrCodeGenerator.generateQrCode(it) }
    }
    bmp?.let {
        Image(bitmap = it.asImageBitmap(), contentDescription = "QR Code", modifier = modifier)
    }
}
