package co.feip.fefu2025.presentation.ui.features.my_stars_preview

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.presentation.ui.features.ui.gitlab.GitLabCard
import org.koin.androidx.compose.koinViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyStarsPreview(
    viewModel: MyStarsPreviewViewModel = koinViewModel(),
    navigateMyStars: () -> Unit,
    navigateRepository: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = { navigateMyStars },
        modifier = Modifier.padding(vertical = 8.dp),
    ) {
        Text(
            text = "My Stars",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }

    if (viewModel.starredRepositories.isEmpty()) {

        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(96.dp).padding(start = 16.dp, end = 16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xffeee6f6)
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "There's nothing here.😭😭😭",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }



        LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        items(viewModel.starredRepositories) { repo ->
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
                    description = repo.description ?: " ",
                    stars = repo.stars,
                    forks = repo.forks,
                    avatarRes = repo.avatarRes,
                    avatarUrl = repo.avatarUrl,
                    onCardClick = {
                        navigateRepository(repo.id)
                    }
                )
            }
        }
    }

}