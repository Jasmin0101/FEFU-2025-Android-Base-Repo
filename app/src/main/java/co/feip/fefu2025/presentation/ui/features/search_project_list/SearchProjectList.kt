package co.feip.fefu2025.presentation.ui.features.search_project_list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.presentation.ui.features.ui.gitlab.GitLabCard
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SearchProjectList(
    viewModel: SearchProjectListViewModel = koinViewModel(),
    navigateRepository: (Int) -> Unit,
    listState: LazyListState,
    searchQuery :String,
) {
    val visibleItems = viewModel.repositories.value // <- используем не allRepositories
    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleItemIndex ->
                if (lastVisibleItemIndex != null &&
                    lastVisibleItemIndex >= visibleItems.size - 5 &&
                    !viewModel.isLoading
                ) {
                    viewModel.loadNextPage(searchQuery)
                }
            }
    }


        LazyColumn(
            state = listState, // <-- добавили
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(visibleItems) { repo ->
                GitLabCard(
                    repositoryName = repo.repositoryName,
                    description = repo.description ?: " ",
                    stars = repo.stars,
                    forks = repo.forks,
                    avatarRes = repo.avatarRes,
                    avatarUrl = repo.avatarUrl,
                    onCardClick = {
                        navigateRepository(repo.id)
                    }
                )
            }

    }
}


