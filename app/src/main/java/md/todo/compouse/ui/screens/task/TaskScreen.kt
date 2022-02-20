package md.todo.compouse.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import md.todo.compouse.data.models.ToDoTask
import md.todo.compouse.until.Action

@Composable
fun TaskScreen(
    selectedTask:ToDoTask?,
    navigateToListScreen: (Action) -> Unit
) {
    Scaffold(
        topBar = {
            TaskAppBar(selectedTask = selectedTask, navigateToListScreen = navigateToListScreen)
        },
        content = {

        }
    )
}