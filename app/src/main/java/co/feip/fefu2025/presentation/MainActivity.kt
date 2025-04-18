package co.feip.fefu2025.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import co.feip.fefu2025.navigation.Destination
import co.feip.fefu2025.navigation.Navigator
import co.feip.fefu2025.presentation.ui.pages.HomePage
import org.koin.compose.koinInject


class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navigator = koinInject<Navigator>()
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = navigator.startDestination
            ){

                navigation<Destination>(
                    startDestination = Destination.HomePage
                ){
                    composable<Destination.HomePage> {
                        HomePage()
                    }
                }
            }


        }


    }

}
