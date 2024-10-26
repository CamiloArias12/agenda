import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.agenda.ui.pages.agenda.CreateAgenda
import com.example.agenda.ui.pages.agenda.UpdateAgenda

@Composable
fun NavigationStack(navController: NavHostController) {

    NavHost(navController = navController,  startDestination = Screen.Main.route) {
        composable(route = Screen.Main.route) {
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.View.route + "?id={id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {
            ViewAgenda(id = it.arguments?.getString("id"),navController = navController)
        }
        composable(
            route = Screen.Edit.route + "?id={id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {
            UpdateAgenda(id = it.arguments?.getString("id"),navController = navController)
        }
        composable(
            route = Screen.Create.route
        ) {
            CreateAgenda(navController = navController)
        }
    }
}