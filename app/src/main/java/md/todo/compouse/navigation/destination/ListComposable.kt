package md.todo.compouse.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import md.todo.compouse.ui.screens.list.ListScreen
import md.todo.compouse.until.Constants.LIST_ARGUMENT_KEY
import md.todo.compouse.until.Constants.LIST_SCREEN

fun NavGraphBuilder.listComposable(
    navToTaskScreen: (taskId: Int) -> Unit
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) {
        ListScreen(navToTaskScreen = navToTaskScreen)
    }
}