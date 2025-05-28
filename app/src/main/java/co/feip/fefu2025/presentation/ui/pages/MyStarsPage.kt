package co.feip.fefu2025.presentation.ui.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import co.feip.fefu2025.presentation.ui.features.my_stars_list.MyStarsList
import co.feip.fefu2025.presentation.ui.features.ui.GradientText
import co.feip.fefu2025.presentation.viewmodel.HomePageViewModel
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyStarsPage(
    viewModel: HomePageViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {

                    GradientText("My Stars")
                },
                navigationIcon = {
                    IconButton(onClick = { viewModel.navigateHome() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->


        MyStarsList(
            navigateHome = { viewModel.navigateHome() },
            navigateRepository = { id -> (viewModel.navigateRepository(id)) },
            paddingValues = paddingValues,
        )


    }
}


