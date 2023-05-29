package com.example.weathercompose.data.models.constance
import androidx.compose.ui.graphics.Color
import com.example.weathercompose.ui.theme.HighPriorityColor
import com.example.weathercompose.ui.theme.LowPriorityColor
import com.example.weathercompose.ui.theme.MediumPriorityColor
import com.example.weathercompose.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}