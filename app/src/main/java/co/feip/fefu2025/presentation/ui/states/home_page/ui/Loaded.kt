package co.feip.fefu2025.presentation.ui.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.data.repository.RepositoryRepository
import co.feip.fefu2025.data.repository_mock.repositories
import co.feip.fefu2025.domain.usecase.GetRepositoriesUseCase
import co.feip.fefu2025.navigation.DefaultNavigator
import co.feip.fefu2025.navigation.Destination
import co.feip.fefu2025.presentation.ui.features.gitlabui.ui.GitLabCard
import co.feip.fefu2025.presentation.viewmodel.RepositoriesViewModel
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Loaded(
    viewModel: RepositoriesViewModel = koinViewModel(),
    paddingValues: PaddingValues,
    modifier: Modifier =Modifier
) {

        Column(
            modifier =
            Modifier
                .padding(paddingValues),
        ) {
            Spacer(Modifier.height(16.dp))
            TextButton(
                onClick = {  viewModel.navigateMyStars() },
                modifier = Modifier.padding(vertical = 8.dp),
            ) {
                Text(
                    text = "My Stars",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(repositories.filter { it.isMyRepo == true }.take(10)) { repo -> // Фильтруем репозитории
                    Box(
                        modifier = Modifier
                            .border(
                                width = 4.dp,
                                brush = Brush.linearGradient(
                                    colors = listOf(Color(0xffbf02b3), Color(0xffd18006))
                                ),
                                shape = RoundedCornerShape(10.dp)
                            )
                    ) {
                        GitLabCard(
                            repositoryName = repo.repositoryName,
                            description = repo.description,

                            stars = repo.stars,
                            forks = repo.forks,
                            avatarRes = repo.avatarRes,

                            onCardClick = {

                                viewModel.navigateRepository(repo.id)
                            }  // Если avatarRes отсутствует, использовать дефолтный
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "All Projects",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            )

            LazyColumn(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(repositories.take(10)) { repo ->

                    GitLabCard(
                        repositoryName = repo.repositoryName,
                        description = repo.description,
                        stars = repo.stars,
                        forks = repo.forks,
                        avatarRes = repo.avatarRes,
                        onCardClick = {
                            viewModel.navigateRepository(repo.id)
                        }
                    )

                }
            }
        }

}


//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//@Preview
//private fun PreviewHomePage(modifier: Modifier = Modifier) {
//
//    Loaded(    viewModel = RepositoriesViewModel(
//        getRepositoriesUseCase = GetRepositoriesUseCase(
//            repository = RepositoryRepository()
//        ),
//        navigator = DefaultNavigator(
//            startDestination = Destination.BaseGraph
//        )
//    ))
//}

