package md.todo.compouse.ui.screens.list

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import md.todo.compouse.R
import md.todo.compouse.ui.theme.fabBackgroundColor
import md.todo.compouse.ui.viewmodels.ShareViewModel
import md.todo.compouse.until.SearchAppBarState

@Composable
fun ListScreen(
    navToTaskScreen: (taskId: Int) -> Unit,
    shareViewModel: ShareViewModel
) {
    val searchAppBarState:SearchAppBarState by shareViewModel.searchAppBarState
    val searchTextState:String by shareViewModel.searchTextState
    Scaffold(
        topBar = {
            ListAppbar(
                shareViewModel = shareViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = {},
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