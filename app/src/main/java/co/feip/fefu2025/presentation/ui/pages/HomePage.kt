package co.feip.fefu2025.presentation.ui.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.presentation.ui.component.all_projects_list.AllProjectsList
import co.feip.fefu2025.presentation.ui.component.my_stars_preview.MyStarsPreview
import co.feip.fefu2025.presentation.ui.component.ui.SearchBar
import co.feip.fefu2025.presentation.viewmodel.HomePageViewModel
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomePage(viewModel: HomePageViewModel = koinViewModel(), modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            Column(
                modifier = modifier.windowInsetsPadding(WindowInsets.statusBars)
            ) {
                SearchBar(
                    value = "Search...", onFocus = {
                        viewModel.navigateSearchScreen()
                    }, readOnly = true
                )
            }
        },
    ) { paddingValues ->

        val listState = rememberLazyListState()

        Column(
        ) {
            Spacer(modifier.height(paddingValues.calculateTopPadding()))

            MyStarsPreview(navigateMyStars = { viewModel.navigateMyStars() },
                navigateRepository = { id -> viewModel.navigateRepository(id) })

            Spacer(modifier = modifier.height(24.dp))

            AllProjectsList(
                listState = listState,
                navigateRepository = { id -> viewModel.navigateRepository(id) },
                paddingValues = paddingValues,
            )
        }
    }
}
