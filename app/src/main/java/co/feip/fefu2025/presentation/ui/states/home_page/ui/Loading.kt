import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.data.repository.RepositoryRepository
import co.feip.fefu2025.domain.usecase.GetRepositoriesUseCase
import co.feip.fefu2025.navigation.DefaultNavigator
import co.feip.fefu2025.navigation.Destination
import co.feip.fefu2025.presentation.ui.features.gitlabui.ui.GitLabCard
import co.feip.fefu2025.presentation.ui.features.gitlabui.ui.ShimmerGitLabCard
import co.feip.fefu2025.presentation.ui.pages.HomePage
import co.feip.fefu2025.presentation.viewmodel.RepositoriesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Loading(
            viewModel: RepositoriesViewModel = koinViewModel(),
            modifier: Modifier =Modifier
) {
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                    .padding(vertical = 12.dp, horizontal = 16.dp,),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Spacer(Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Search...") },
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(16.dp))
                            .border(
                                width = 2.dp,
                                brush = Brush.linearGradient(
                                    colors = listOf(Color(0xffbf02b3), Color(0xffd18006)),
                                ),
                                shape = RoundedCornerShape(16.dp),
                            )
                            .background(color = Color(0xffffffff)),
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
            }
        },
    ) { paddingValues ->
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Spacer(Modifier.height(16.dp))

        TextButton(
            onClick = { viewModel.navigateMyStars() },
            modifier = Modifier.padding(vertical = 8.dp)
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
            item {
                ShimmerGitLabCard()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "All Projects",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            repeat(5) {
                ShimmerGitLabCard()
            }
        }
    }
}
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
private fun PreviewHomePage() {

    Loading(    viewModel = RepositoriesViewModel(
        getRepositoriesUseCase = GetRepositoriesUseCase(
            repository = RepositoryRepository()
        ),
        navigator = DefaultNavigator(
            startDestination = Destination.BaseGraph
        )
    )
    )
}


