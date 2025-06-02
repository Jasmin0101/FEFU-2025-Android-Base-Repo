package co.feip.fefu2025.presentation

import SearchScreenPage
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import co.feip.fefu2025.navigation.Destination
import co.feip.fefu2025.navigation.NavigationAction
import co.feip.fefu2025.navigation.Navigator
import co.feip.fefu2025.navigation.ObserveAsEvents
import co.feip.fefu2025.presentation.ui.pages.GitLabPage
import co.feip.fefu2025.presentation.ui.pages.HomePage
import co.feip.fefu2025.presentation.ui.pages.MyStarsPage
import org.koin.compose.koinInject


class MainActivity : ComponentActivity() {

    @ExperimentalMaterial3Api
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navigator = koinInject<Navigator>()
            val navController = rememberNavController()

            ObserveAsEvents(flow = navigator.navigationAction) { action ->
                when(action) {
                    is NavigationAction.Navigate -> navController.navigate(
                        action.destination
                    ) {
                        action.navOptions(this)
                    }
                    NavigationAction.NavigateUp -> navController.navigateUp()
                }
            }

            NavHost(
                navController = navController,
                startDestination = Destination.BaseGraph
            ){

                navigation<Destination.BaseGraph>(
                    startDestination = Destination.HomePage
                ){
                    composable<Destination.HomePage> {
                        HomePage()
                    }
                    composable<Destination.MyStars> {
                        MyStarsPage()
                    }
                    composable<Destination.SearchScreenPage> {
                        SearchScreenPage()
                    }

                    composable<Destination.Repository> {
                        val args = it.toRoute<Destination.Repository>()

                        GitLabPage(
                            repositoryId = args.id,
                        )
                    }

                }
            }
        }
    }
}
