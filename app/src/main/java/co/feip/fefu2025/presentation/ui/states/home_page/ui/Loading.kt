import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.presentation.ui.features.gitlabui.ui.ShimmerGitLabCard
import co.feip.fefu2025.presentation.viewmodel.RepositoriesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoadingHomePage(
            viewModel: RepositoriesViewModel = koinViewModel(),
            paddingValues: PaddingValues,
            modifier: Modifier = Modifier,
) {
    var searchQuery by remember { mutableStateOf("") }

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


//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//@Preview
//private fun PreviewHomePage() {
//
//    Loading(    viewModel = RepositoriesViewModel(
//        getRepositoriesUseCase = GetRepositoriesUseCase(
//            repository = RepositoryRepository()
//        ),
//        navigator = DefaultNavigator(
//            startDestination = Destination.BaseGraph
//        )
//    )
//    )
//}


