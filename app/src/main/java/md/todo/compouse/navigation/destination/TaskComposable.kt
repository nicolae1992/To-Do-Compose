package md.todo.compouse.navigation.destination

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import md.todo.compouse.ui.screens.task.TaskScreen
import md.todo.compouse.ui.viewmodels.ShareViewModel
import md.todo.compouse.until.Action
import md.todo.compouse.until.Constants.TASK_ARGUMENT_KEY
import md.todo.compouse.until.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    viewModel: ShareViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
        LaunchedEffect(key1 = taskId) {
            viewModel.getSelectTask(taskId = taskId)
        }
        val selectTask by viewModel.selectTask.collectAsState()

        LaunchedEffect(key1 = selectTask) {
            if (selectTask != null || taskId == -1) {
                viewModel.updateTaskFields(selectTask)
            }
        }

        TaskScreen(
            shareViewModel = viewModel,
            selectedTask = selectTask,
            navigateToListScreen = navigateToListScreen
        )
    }
}