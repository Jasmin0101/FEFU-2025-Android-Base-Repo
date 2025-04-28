 package co.feip.fefu2025.presentation.ui.pages

import Loading

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import co.feip.fefu2025.data.repository.RepositoryRepository
import co.feip.fefu2025.domain.usecase.GetRepositoriesUseCase
import co.feip.fefu2025.navigation.DefaultNavigator
import co.feip.fefu2025.navigation.Destination
import co.feip.fefu2025.presentation.ui.states.home_page.HomePageState
import co.feip.fefu2025.presentation.ui.states.home_page.ui.Error
import co.feip.fefu2025.presentation.viewmodel.RepositoriesViewModel
import org.koin.androidx.compose.koinViewModel

 @RequiresApi(Build.VERSION_CODES.O)
 @Composable
 fun HomePage(viewModel: RepositoriesViewModel = koinViewModel()) {

     when (val state = viewModel.homePageState) {
         is HomePageState.Loading -> {
             Loading(viewModel)
         }
         is HomePageState.Loaded -> {
             Loaded(viewModel)
         }
         is HomePageState.Error -> {
          Error(viewModel)
         }
     }
 }

 @RequiresApi(Build.VERSION_CODES.O)
 @Preview
 @Composable
 fun PreviewHomePage() {
     HomePage(
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
