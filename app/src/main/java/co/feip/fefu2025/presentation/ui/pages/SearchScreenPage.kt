package co.feip.fefu2025.presentation.ui.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import co.feip.fefu2025.presentation.ui.component.search_project_list.SearchProjectList
import co.feip.fefu2025.presentation.ui.component.search_project_list.SearchProjectListViewModel
import co.feip.fefu2025.presentation.ui.component.ui.SearchBar
import co.feip.fefu2025.presentation.viewmodel.HomePageViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(FlowPreview::class)
@Composable
fun SearchScreenPage(
    viewModel: HomePageViewModel = koinViewModel(),
    searchViewModel: SearchProjectListViewModel = koinViewModel(),
) {
    val queryState by searchViewModel.query.collectAsState()

    var searchQuery by remember { mutableStateOf(queryState) }

    LaunchedEffect(searchQuery) {
        snapshotFlow { searchQuery }
            .debounce(600)
            .distinctUntilChanged()
            .collect { debouncedQuery ->
                searchViewModel.searchRepositories(debouncedQuery)
            }
    }

    LaunchedEffect(queryState) {
        if (queryState != searchQuery) {
            searchQuery = queryState
        }
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets.statusBars)
            ) {
                SearchBar(
                    value = searchQuery,
                    onChange = {
                        searchQuery = it
                    }
                )
            }
        },
    ) { paddingValues ->

        Column(
            modifier = Modifier.padding(paddingValues),
        ) {
            SearchProjectList(
                navigateRepository = { id -> viewModel.navigateRepository(id) },
                paddingValues = paddingValues,
            )
        }
    }
}
