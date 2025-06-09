package co.feip.fefu2025.presentation.ui.pages

import android.os.Build
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import co.feip.fefu2025.presentation.custom.ProgrammingLanguageTag
import co.feip.fefu2025.presentation.ui.component.git_lab_page.GitLabPageState
import co.feip.fefu2025.presentation.ui.component.git_lab_page.GitLabPageViewModel
import co.feip.fefu2025.presentation.ui.states.git_lab_page.ui.ErrorGitLabState
import co.feip.fefu2025.presentation.ui.states.git_lab_page.ui.LoadedGitLabState
import co.feip.fefu2025.presentation.ui.states.git_lab_page.ui.LoadingGitLabState
import org.koin.androidx.compose.koinViewModel
import views.FexBoxLayoutCustom
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalMaterial3Api
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GitLabPage(
    viewModel: GitLabPageViewModel = koinViewModel(),
    repositoryId: Int,
    navController: NavController
) {
    val repository = when (val state = viewModel.state) {
        is GitLabPageState.Loading -> {
            null
        }

        is GitLabPageState.Loaded -> {
            state.repositoryModel
        }

        is GitLabPageState.Error -> {
            null
        }
    }

    LaunchedEffect(Unit) {
        viewModel.refresh(repositoryId)
    }


    Scaffold(topBar = {
        TopAppBar(title = {
            repository?.let { Text(text = it.repositoryName) }
        }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }

        })
    }) {

            paddingValues ->
        when (val state = viewModel.state) {
            is GitLabPageState.Loading -> {
                LoadingGitLabState(paddingValues)
            }

            is GitLabPageState.Loaded -> {
                LoadedGitLabState(
                    repositoryId = repositoryId,
                    viewModel = viewModel,
                    paddingValues = paddingValues,
                    repository = state.repositoryModel,
                    isStared = state.isStared,

                    modifier = Modifier,
                )
            }

            is GitLabPageState.Error -> {
                ErrorGitLabState()
            }
        }
    }
}

@Composable
fun CustomFlexBoxScreen(
    array: List<String>,
    modifier: Modifier = Modifier,
) {

    AndroidView(
        factory = { ctx ->
            FexBoxLayoutCustom(ctx).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                )

                array.forEach { language ->
                    val tag = ProgrammingLanguageTag(ctx)
                    tag.setProperties(language, getRandomColor(), Random.nextFloat() * 100)
                    addView(tag)
                }

                invalidate()
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background, shape = MaterialTheme.shapes.medium)
            .padding(16.dp),
    )
}


fun getRandomColor(): Color =
    Color(Random.nextInt(0, 256), Random.nextInt(0, 256), Random.nextInt(0, 256))
