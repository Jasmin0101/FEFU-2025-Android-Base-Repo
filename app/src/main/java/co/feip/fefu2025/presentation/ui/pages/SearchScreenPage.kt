import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import co.feip.fefu2025.presentation.ui.features.search_project_list.SearchProjectListViewModel
import co.feip.fefu2025.presentation.ui.pages.SearchBar
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
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        snapshotFlow { searchQuery }
            .debounce(600)
            .distinctUntilChanged()
            .collect { debouncedQuery ->
                searchViewModel.searchRepositories(debouncedQuery)
            }
    }

    Scaffold(
        topBar = {
            SearchBar(
                value = searchQuery,
                onChange = {
                    searchQuery = it // только обновляем локальное состояние
                }
            )
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
