package md.todo.compouse.data.models

import androidx.compose.ui.graphics.Color
import md.todo.compouse.ui.theme.HighPriorityColor
import md.todo.compouse.ui.theme.LowPriorityColor
import md.todo.compouse.ui.theme.MediumPriorityColor
import md.todo.compouse.ui.theme.NonPriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NON(NonPriorityColor)
}