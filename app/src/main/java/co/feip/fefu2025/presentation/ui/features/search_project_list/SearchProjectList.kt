import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import co.feip.fefu2025.presentation.ui.features.search_project_list.SearchProjectListViewModel
import co.feip.fefu2025.presentation.ui.features.ui.gitlab.GitLabCard
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(FlowPreview::class)
@Composable
fun SearchProjectList(
    viewModel: SearchProjectListViewModel = koinViewModel(),
    navigateRepository: (Int) -> Unit,
    paddingValues: PaddingValues,

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

    if (isQueryLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(12.dp)),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(count = items.itemCount) { index ->
                if (index == 0) {
                    Spacer(Modifier.height(16.dp))
                }
                val repo = items[index]
                repo?.let {
                    GitLabCard(
                        repositoryName = it.repositoryName,
                        description = it.description ?: " ",
                        stars = it.stars,
                        forks = it.forks,
                        avatarUrl = it.avatarUrl,
                        modifier = Modifier.padding(horizontal = 16.dp),
                        onCardClick = { navigateRepository(it.id) }
                    )
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
                Spacer(modifier = Modifier.height(paddingValues.calculateBottomPadding()))
            }
        }
    }
}
