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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import co.feip.fefu2025.presentation.ui.features.gitlabui.GitLabCard
import co.feip.fefu2025.presentation.viewmodel.RepositoriesViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    viewModel: RepositoriesViewModel = RepositoriesViewModel(),
    modifier: Modifier =Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    val repositories by viewModel.repositories

    Scaffold(
        topBar = {
            TopAppBar(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .height(100.dp), // Ограниченная высота AppBar
                colors =
                TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                ),
                title = {
                    Row(
                        modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            placeholder = { Text("Search...") },
                            modifier =
                            Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(16.dp))
                                .border(
                                    width = 2.dp,
                                    brush =
                                    Brush.linearGradient(
                                        colors = listOf(Color(0xffbf02b3), Color(0xffd18006)),
                                    ),
                                    shape = RoundedCornerShape(16.dp),
                                ).background(color = Color(0xffffffff)),
                            singleLine = true,
                        )
                        Button(
                            onClick = { },
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.height(56.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Search,
                                contentDescription = "Search",
                                modifier = Modifier.size(32.dp),
                            )
                        }
                    }
                },
            )
        },
    ) { paddingValues ->
        Column(
            modifier =
            Modifier
                .padding(paddingValues),
        ) {
            Spacer(Modifier.height(16.dp))
            Text(
                text = "My Stars",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            )

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
                            avatarRes = repo.avatarRes  // Если avatarRes отсутствует, использовать дефолтный

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
                            avatarRes = repo.avatarRes

                        )

                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
private fun PreviewHomePage(modifier: Modifier = Modifier) {
    HomePage()
}

