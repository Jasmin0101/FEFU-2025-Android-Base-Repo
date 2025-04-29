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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.feip.fefu2025.data.repository.RepositoryRepository
import co.feip.fefu2025.domain.usecase.GetRepositoriesUseCase
import co.feip.fefu2025.navigation.DefaultNavigator
import co.feip.fefu2025.navigation.Destination
import co.feip.fefu2025.presentation.ui.features.ui.GradientText
import co.feip.fefu2025.presentation.ui.states.home_page.MyStarsStatesPage
import co.feip.fefu2025.presentation.ui.states.my_stars.ui.ErrorMyStarsPage
import co.feip.fefu2025.presentation.ui.states.my_stars.ui.LoadedMyStarsPage
import co.feip.fefu2025.presentation.ui.states.my_stars.ui.LoadingMyStarsPage
import co.feip.fefu2025.presentation.viewmodel.RepositoriesViewModel
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyStarsPage(
    viewModel: RepositoriesViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val repositories by viewModel.repositories

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

        when (val state = viewModel.myStarsPageState) {
            is MyStarsStatesPage.Loading -> {
                LoadingMyStarsPage( paddingValues)
            }
            is MyStarsStatesPage.Loaded -> {
                LoadedMyStarsPage(viewModel, paddingValues )
            }
            is MyStarsStatesPage.Error -> {
                ErrorMyStarsPage(viewModel, paddingValues)
            }

            else -> {}
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
private fun  PreviewMyStars(){
    MyStarsPage(
        viewModel = RepositoriesViewModel(
            getRepositoriesUseCase = GetRepositoriesUseCase(
                repository = RepositoryRepository()
            ),
            navigator = DefaultNavigator(
                startDestination = Destination.BaseGraph
            )
        )
    )
}