package md.todo.compouse.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import md.todo.compouse.R
import md.todo.compouse.ui.theme.fabBackgroundColor

@Composable
fun ListScreen(
    navToTaskScreen: (taskId: Int) -> Unit
) {
    Scaffold(
        topBar = {
            ListAppbar()
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

@Preview
@Composable
fun ListScreenPreview() {
    ListScreen(navToTaskScreen = {})
}