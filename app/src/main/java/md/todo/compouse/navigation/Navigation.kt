package md.todo.compouse.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import md.todo.compouse.navigation.destination.listComposable
import md.todo.compouse.navigation.destination.taskComposable
import md.todo.compouse.until.Constants.LIST_SCREEN

@Composable
fun SetUpNavigation(
    navHostController: NavHostController
) {
    val screen = remember(navHostController) {
        Screens(navController = navHostController)
    }

    NavHost(navController = navHostController, startDestination = LIST_SCREEN) {
        listComposable(
            navToTaskScreen = screen.task
        )
        taskComposable(
            navigateToListScreen = screen.list
        )
    }
}