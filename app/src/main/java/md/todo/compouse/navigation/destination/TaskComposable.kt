package md.todo.compouse.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import md.todo.compouse.until.Action
import md.todo.compouse.until.Constants
import md.todo.compouse.until.Constants.TASK_ARGUMENT_KEY
import md.todo.compouse.until.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments?.getInt(TASK_ARGUMENT_KEY) ?: -1
       // navigateToListScreen(Action.ADD)
        println("${TASK_ARGUMENT_KEY}:-> $taskId")
    }
}