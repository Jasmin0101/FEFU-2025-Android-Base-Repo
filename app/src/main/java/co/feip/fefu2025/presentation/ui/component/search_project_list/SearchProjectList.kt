package co.feip.fefu2025.presentation.ui.component.search_project_list


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import co.feip.fefu2025.presentation.ui.component.ui.gitlab.GitLabCard
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable

fun SearchProjectList(
    viewModel: SearchProjectListViewModel = koinViewModel(),
    navigateRepository: (Int) -> Unit,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    val query by viewModel.query.collectAsState()
    val items = viewModel.items.collectAsLazyPagingItems()

    var isQueryLoading by remember { mutableStateOf(false) }

    LaunchedEffect(query) {
        isQueryLoading = true
    }

    LaunchedEffect(items.loadState.refresh) {
        if (items.loadState.refresh !is LoadState.Loading) {
            isQueryLoading = false
        }
    }

    when {
        query.isBlank() -> {

            Box(
                modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(text = " Puk Puk, you haven't written anything. ")
            }
        }

        isQueryLoading -> {
            Box(
                modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        else -> {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(12.dp)),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(count = items.itemCount) { index ->
                    if (index == 0) {
                        Spacer(modifier.height(16.dp))
                    }
                    val repo = items[index]
                    repo?.let {
                        GitLabCard(repositoryName = it.repositoryName,
                            description = it.description ?: " ",
                            stars = it.stars,
                            forks = it.forks,
                            avatarUrl = it.avatarUrl,
                            modifier = modifier.padding(horizontal = 16.dp),
                            onCardClick = { navigateRepository(it.id) })
                    }
                }

                when {
                    items.loadState.append is LoadState.Loading -> {
                        item { CircularProgressIndicator() }
                    }

                    items.loadState.refresh is LoadState.Error -> {
                        val e = items.loadState.refresh as LoadState.Error
                        item { Text("Ошибка загрузки: ${e.error.message}") }
                    }
                }

                item {
                    Spacer(modifier = modifier.height(paddingValues.calculateBottomPadding()))
                }
            }
        }
    }
}
