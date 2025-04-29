package co.feip.fefu2025.presentation.ui.pages

import LoadingHomePage
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.data.repository.RepositoryRepository
import co.feip.fefu2025.domain.usecase.GetRepositoriesUseCase
import co.feip.fefu2025.navigation.DefaultNavigator
import co.feip.fefu2025.navigation.Destination
import co.feip.fefu2025.presentation.ui.features.gitlabui.ui.GitLabCard
import co.feip.fefu2025.presentation.ui.features.ui.GradientText
import co.feip.fefu2025.presentation.ui.states.home_page.HomePageState
import co.feip.fefu2025.presentation.ui.states.home_page.MyStarsStatePage
import co.feip.fefu2025.presentation.ui.states.home_page.ui.ErrorHomePage
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
            is MyStarsStatePage.Loading -> {
                LoadingMyStarsPage( paddingValues)
            }
            is MyStarsStatePage.Loaded -> {
                LoadedMyStarsPage(viewModel, paddingValues )
            }
            is MyStarsStatePage.Error -> {
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