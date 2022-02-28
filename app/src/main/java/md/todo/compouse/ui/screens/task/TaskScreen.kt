package md.todo.compouse.ui.screens.task

import android.content.Context
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import md.todo.compouse.data.models.Priority
import md.todo.compouse.data.models.ToDoTask
import md.todo.compouse.ui.viewmodels.ShareViewModel
import md.todo.compouse.until.Action

@Composable
fun TaskScreen(
    shareViewModel: ShareViewModel,
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
) {
    val title: String by shareViewModel.title
    val description: String by shareViewModel.description
    val priority: Priority by shareViewModel.priority
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = { action ->
                    if (action == Action.NO_ACTION) {
                        navigateToListScreen(action)
                    } else {
                        if (shareViewModel.validateFields()) {
                            navigateToListScreen(action)
                        } else {
                            displayToast(context = context)
                        }
                    }
                }
            )
        },
        content = {
            TaskContent(
                title = title,
                onTitleChange = { shareViewModel.updateTitle(it) },
                description = description,
                onDescriptionChange = { shareViewModel.description.value = it },
                priority = priority,
                onPrioritySelected = { shareViewModel.priority.value = it }
            )
        }
    )
}

fun displayToast(context: Context) {
    Toast.makeText(context, "Fields can't be empty", Toast.LENGTH_SHORT).show()
}
