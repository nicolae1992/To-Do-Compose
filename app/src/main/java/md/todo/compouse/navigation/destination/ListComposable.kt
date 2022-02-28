package md.todo.compouse.navigation.destination

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import md.todo.compouse.ui.screens.list.ListScreen
import md.todo.compouse.ui.viewmodels.ShareViewModel
import md.todo.compouse.until.Constants.LIST_ARGUMENT_KEY
import md.todo.compouse.until.Constants.LIST_SCREEN
import md.todo.compouse.until.toAction

@ExperimentalMaterialApi
fun NavGraphBuilder.listComposable(
    navToTaskScreen: (taskId: Int) -> Unit,
    shareViewModel: ShareViewModel
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) { navBackStack ->
        val action = navBackStack.arguments?.getString(LIST_ARGUMENT_KEY).toAction()
        LaunchedEffect(key1 = action){
            shareViewModel.action.value = action
        }
        ListScreen(
            navToTaskScreen = navToTaskScreen,
            shareViewModel = shareViewModel
        )
    }
}