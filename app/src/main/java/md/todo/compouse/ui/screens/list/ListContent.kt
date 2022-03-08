package md.todo.compouse.ui.screens.list


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import md.todo.compouse.data.models.Priority
import md.todo.compouse.data.models.ToDoTask
import md.todo.compouse.ui.theme.*
import md.todo.compouse.until.RequestState
import md.todo.compouse.until.SearchAppBarState

@Composable
fun ListContent(
    allTasks: RequestState<List<ToDoTask>>,
    searchedTasks: RequestState<List<ToDoTask>>,
    searchAppBarState: SearchAppBarState,
    navigateToDoTask: (taskId: Int) -> Unit
) {
    if (searchAppBarState == SearchAppBarState.TRIGGERED) {
        if (searchedTasks is RequestState.Success) {
            HandleListComponent(
                tasks = searchedTasks.data,
                navigateToSearchScreen = navigateToDoTask
            )
        }
    }else{
        if (allTasks is RequestState.Success) {
            HandleListComponent(
                tasks = allTasks.data,
                navigateToSearchScreen = navigateToDoTask
            )
        }
    }
}

@Composable
fun HandleListComponent(
    tasks: List<ToDoTask>,
    navigateToSearchScreen: (taskId: Int) -> Unit

) {
    if (tasks.isEmpty()) {
        EmptyContent()
    } else {
        DisplayTasks(
            tasks = tasks,
            navigateToDoTask = navigateToSearchScreen
        )
    }
}

@Composable
fun DisplayTasks(
    tasks: List<ToDoTask>,
    navigateToDoTask: (taskId: Int) -> Unit
) {
    LazyColumn {
        items(
            items = tasks,
            key = { task -> task.id }
        ) { task ->
            TaskItem(
                toDoTask = task,
                navigateToDoTask = navigateToDoTask
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskItem(
    toDoTask: ToDoTask,
    navigateToDoTask: (taskId: Int) -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.taskItemColorBackground,
        elevation = TASK_ITEM_ELEVATION,
        onClick = {
            navigateToDoTask(toDoTask.id)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(all = LARGE_PADDING)
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    modifier = Modifier.weight(8f),
                    text = toDoTask.title,
                    color = MaterialTheme.colors.taskItemTextColor,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Canvas(
                        modifier = Modifier
                            .width(PRIORITY_INDICATOR_SIZE)
                            .height(PRIORITY_INDICATOR_SIZE)
                    ) {
                        drawCircle(color = toDoTask.priority.color)
                    }
                }
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = toDoTask.description,
                color = MaterialTheme.colors.taskItemTextColor,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun TaskItemPreview() {
    TaskItem(
        toDoTask = ToDoTask(
            id = 0,
            title = "title test",
            description = "this is description task",
            priority = Priority.LOW
        ),
        navigateToDoTask = {})
}