package md.todo.compouse.ui.screens.list

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import md.todo.compouse.R
import md.todo.compouse.ui.theme.fabBackgroundColor
import md.todo.compouse.ui.viewmodels.ShareViewModel
import md.todo.compouse.until.SearchAppBarState

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

    val searchAppBarState: SearchAppBarState by shareViewModel.searchAppBarState
    val searchTextState: String by shareViewModel.searchTextState

    shareViewModel.handleDatabaseAction(action = action)

    Scaffold(
        topBar = {
            ListAppbar(
                shareViewModel = shareViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = {
            ListContent(tasks = allTasks, navigateToDoTask = navToTaskScreen)
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