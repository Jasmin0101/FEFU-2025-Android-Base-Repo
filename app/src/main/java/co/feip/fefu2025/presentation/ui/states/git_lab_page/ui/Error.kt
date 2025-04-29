package co.feip.fefu2025.presentation.ui.states.git_lab_page.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.data.repository.RepositoryRepository
import co.feip.fefu2025.domain.usecase.GetRepositoryUseCase
import co.feip.fefu2025.navigation.DefaultNavigator
import co.feip.fefu2025.navigation.Destination
import co.feip.fefu2025.presentation.ui.features.ui.shimmerEffect
import co.feip.fefu2025.presentation.ui.pages.CustomFlexBoxScreen
import co.feip.fefu2025.presentation.viewmodel.RepositoryViewModel
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ErrorGitLabState(

//    paddingValues: PaddingValues,
    repositoryId : Int,
    viewModel: RepositoryViewModel,
    modifier: Modifier = Modifier,
){
    val repository by viewModel.repository

    viewModel.loadRepository(repositoryId)
    Column(
        modifier = Modifier
//            .padding(paddingValues)
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.medium)
            .padding(16.dp),
    ) {
        Spacer(Modifier.height(50.dp))
        TextButton(
            onClick = { viewModel.retryRepository(repositoryId)},
            modifier = Modifier.padding(vertical = 8.dp),
        ) {
            Text(
                text = "Oops, something's wrong.:/ \n " +
                        "Repeat?",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

}




@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private  fun PreviewGitLab(){
    ErrorGitLabState(repositoryId = 1, viewModel = RepositoryViewModel(
        getRepositoryUseCase = GetRepositoryUseCase(repository = RepositoryRepository()),
        navigator =  DefaultNavigator(startDestination = Destination.SearchScreenPage)

    )
    )
}