package co.feip.fefu2025.presentation.ui.pages

import android.annotation.SuppressLint
import android.os.Build
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import co.feip.fefu2025.presentation.custom.ProgrammingLanguageTag
import co.feip.fefu2025.presentation.ui.states.git_lab_page.GitLabPageState
import co.feip.fefu2025.presentation.ui.states.git_lab_page.ui.ErrorGitLabState
import co.feip.fefu2025.presentation.ui.states.git_lab_page.ui.LoadedGitLabState
import co.feip.fefu2025.presentation.ui.states.git_lab_page.ui.LoadingGitLabState
import co.feip.fefu2025.presentation.ui.states.home_page.HomePageState
import co.feip.fefu2025.presentation.ui.states.home_page.ui.ErrorHomePage
import co.feip.fefu2025.presentation.viewmodel.RepositoryViewModel
import org.koin.androidx.compose.koinViewModel
import views.FexBoxLayoutCustom
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalMaterial3Api
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GitLabPage(
    viewModel: RepositoryViewModel = koinViewModel(),
    repositoryId : Int,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
) {
    val repository by viewModel.repository

    viewModel.loadRepository(repositoryId)

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    repository?.let { Text(text = it.repositoryName) }
                },
                navigationIcon = {
                    IconButton(onClick = {viewModel.navigateHome()  }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ){

        paddingValues ->
        when (val state = viewModel.gitLabPageState) {
            is GitLabPageState.Loading -> {
                LoadingGitLabState(paddingValues)
            }
            is GitLabPageState.Loaded -> {
                LoadedGitLabState(repositoryId = repositoryId, viewModel = viewModel, paddingValues = paddingValues)
            }
            is GitLabPageState.Error -> {
                ErrorGitLabState(repositoryId, viewModel)
            }
        }
    }
}

@Composable
fun CustomFlexBoxScreen(
    array: List<String>,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

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
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background, shape = MaterialTheme.shapes.medium)
            .padding(16.dp),
    )
}

@ExperimentalMaterial3Api
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun PreviewPage(modifier: Modifier = Modifier) {
    GitLabPage(
        repositoryId = 1
    )
}

fun getRandomColor(): Color = Color(Random.nextInt(0, 256), Random.nextInt(0, 256), Random.nextInt(0, 256))
