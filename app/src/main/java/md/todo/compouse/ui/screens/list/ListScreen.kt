package md.todo.compouse.ui.screens.list

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.launch
import md.todo.compouse.R
import md.todo.compouse.ui.theme.fabBackgroundColor
import md.todo.compouse.ui.viewmodels.ShareViewModel
import md.todo.compouse.until.Action
import md.todo.compouse.until.SearchAppBarState
import javax.annotation.meta.When

@ExperimentalMaterialApi
@Composable
fun ListScreen(
    navToTaskScreen: (taskId: Int) -> Unit,
    shareViewModel: ShareViewModel
) {
    LaunchedEffect(key1 = true) {
        shareViewModel.getAllTasks()
    }
    val action by shareViewModel.action
    val allTasks by shareViewModel.allTask.collectAsState()
    val searchedTasks by shareViewModel.searchTasks.collectAsState()

    val searchAppBarState: SearchAppBarState by shareViewModel.searchAppBarState
    val searchTextState: String by shareViewModel.searchTextState
    val scaffoldState = rememberScaffoldState()

    DisplaySnackBar(
        scaffoldState = scaffoldState,
        handleDatabaseAction = { shareViewModel.handleDatabaseAction(action = action) },
        onUndoClicked = { shareViewModel.action.value = it },
        taskTitle = shareViewModel.title.value,
        action = action
    )
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ListAppbar(
                shareViewModel = shareViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = {
            ListContent(
                allTasks = allTasks,
                searchedTasks = searchedTasks,
                searchAppBarState = searchAppBarState,
                navigateToDoTask = navToTaskScreen
            )
        },
        floatingActionButton = {
            ListFab(fabOnClick = navToTaskScreen)
        }
    )
}

@Composable
fun ListFab(fabOnClick: (taskId: Int) -> Unit) {
    FloatingActionButton(
        onClick = {
            fabOnClick(-1)
        },
        backgroundColor = MaterialTheme.colors.fabBackgroundColor
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.add_button_description),
            tint = Color.White
        )
    }
}

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    handleDatabaseAction: () -> Unit,
    onUndoClicked: (Action) -> Unit,
    taskTitle: String,
    action: Action
) {
    handleDatabaseAction()

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            scope.launch {
                val snackBarResult =
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = setMessage(action, taskTitle),
                        actionLabel = setActionLabel(action)
                    )
                undoDeleteTask(
                    action = action,
                    snackBarResult = snackBarResult,
                    onUndoClicked = onUndoClicked
                )
            }
        }
    }
}

private fun setMessage(action: Action, taskTitle: String): String {
    return when (action) {
        Action.DELETE_ALL -> "All Tasks Removed"
        else -> "${action.name}: $taskTitle"
    }
}

private fun setActionLabel(action: Action): String {
    return if (action.name == Action.DELETE.toString()) {
        "UNDO"
    } else {
        "Ok"
    }
}

private fun undoDeleteTask(
    action: Action,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit
) {
    if (snackBarResult == SnackbarResult.ActionPerformed && action == Action.DELETE) {
        onUndoClicked(Action.UNDO)
    }
}