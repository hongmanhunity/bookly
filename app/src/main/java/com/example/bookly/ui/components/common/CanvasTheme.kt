package com.example.bookly.ui.components.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp

@Composable
fun CanvasTheme(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        drawCircle(
            color = Color(0x154EBA87),
            radius = 70.dp.toPx(),
            center = Offset(size.width * 0.2f, size.height * 0.12f)
        )

        drawCircle(
            color = Color(0x184EBA87),
            radius = 24.dp.toPx(),
            center = Offset(size.width * 0.85f, size.height * 0.15f)
        )

        val offsetY = size.height * 0.05f
        val trianglePath = Path().apply {
            moveTo(size.width * 0.76f, size.height * 0.08f - offsetY)
            lineTo(size.width * 0.76f, size.height * 0.14f - offsetY)
            lineTo(size.width * 0.58f, size.height * 0.15f - offsetY)
            close()
        }

        drawPath(
            path = trianglePath,
            color = Color(0x104EBA87)
        )
    }
}
