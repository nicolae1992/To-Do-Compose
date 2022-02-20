package md.todo.compouse.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import md.todo.compouse.navigation.destination.listComposable
import md.todo.compouse.navigation.destination.taskComposable
import md.todo.compouse.ui.viewmodels.ShareViewModel
import md.todo.compouse.until.Constants.LIST_SCREEN

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SetUpNavigation(
    navHostController: NavHostController,
    shareViewModel: ShareViewModel
) {
    val screen = remember(navHostController) {
        Screens(navController = navHostController)
    }

    NavHost(navController = navHostController, startDestination = LIST_SCREEN) {
        listComposable(
            navToTaskScreen = screen.task,
            shareViewModel = shareViewModel
        )
        taskComposable(
            navigateToListScreen = screen.list
        )
    }
}