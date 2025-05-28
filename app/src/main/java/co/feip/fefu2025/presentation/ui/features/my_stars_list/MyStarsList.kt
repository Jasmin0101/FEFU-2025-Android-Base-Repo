package co.feip.fefu2025.presentation.ui.features.my_stars_list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.presentation.ui.features.ui.gitlab.GitLabCard
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyStarsList(
    viewModel: MyStarsListViewModel = koinViewModel(),
    navigateHome: () -> Unit,
    navigateRepository: (Int) -> Unit,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    val repositories by viewModel.repositories
    val starredRepositories = repositories.filter { it.isMyRepo == true }
    val listState = rememberLazyListState()
    val visibleItems = viewModel.starredRepositories // <- используем не allRepositories


    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleItemIndex ->
                if (lastVisibleItemIndex != null &&
                    lastVisibleItemIndex >= visibleItems.size - 5 &&
                    !viewModel.isLoading
                ) {
                    viewModel.loadNextPage()
                }
            }
    }
    Column(
        modifier =
        Modifier
            .padding(paddingValues),
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "All Projects",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
        )

        LazyColumn(
            state = listState,
            modifier =
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {


            items(starredRepositories) { repo -> // Фильтруем репозитории


                GitLabCard(
                    repositoryName = repo.repositoryName,
                    description = repo.description ?: " ",

                    stars = repo.stars,
                    forks = repo.forks,
                    avatarRes = repo.avatarRes,

                    onCardClick = {
                        navigateRepository(repo.id)
                    }
                )

            }
        }

    }
}
