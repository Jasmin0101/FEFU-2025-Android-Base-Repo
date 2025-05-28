package co.feip.fefu2025.presentation.ui.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import co.feip.fefu2025.presentation.ui.features.search_project_list.SearchProjectList
import co.feip.fefu2025.presentation.ui.features.search_project_list.SearchProjectListViewModel
import co.feip.fefu2025.presentation.viewmodel.HomePageViewModel
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SearchScreenPage(
    viewModel: HomePageViewModel = koinViewModel(),
    searchViewModel: SearchProjectListViewModel = koinViewModel(),
) {


    var searchQuery by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            SearchBar(
                value = searchQuery,
                onChange = {
                    searchQuery = it
                    searchViewModel.searchRepositories(it)
                }
            )
        },
    ) { paddingValues ->

        val listState = rememberLazyListState()

        Column(
            modifier = Modifier.padding(paddingValues),
        ) {

            SearchProjectList(
                navigateRepository = { id -> (viewModel.navigateRepository(id)) },
                listState = listState,
                searchQuery = searchQuery
            )
        }
    }

}

//@RequiresApi(Build.VERSION_CODES.O)
//@Preview
//@Composable
//private fun PreviewHomePage() {
//    SearchScreenPage(
//        viewModel = HomePageViewModel(
//            getRepositoriesUseCase = GetRepositoriesUseCase(
//                repository = RepositoryRepository()
//            ),
//            navigator = DefaultNavigator(
//                startDestination = Destination.BaseGraph
//            )
//        )
//    )
//}


