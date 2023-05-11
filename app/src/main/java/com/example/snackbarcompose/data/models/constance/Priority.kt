package com.example.snackbarcompose.data.models.constance
import androidx.compose.ui.graphics.Color
import com.example.snackbarcompose.ui.theme.HighPriorityColor
import com.example.snackbarcompose.ui.theme.LowPriorityColor
import com.example.snackbarcompose.ui.theme.MediumPriorityColor
import com.example.snackbarcompose.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}