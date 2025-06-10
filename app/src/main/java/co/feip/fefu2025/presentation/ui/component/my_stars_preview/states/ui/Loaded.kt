package co.feip.fefu2025.presentation.ui.component.my_stars_preview.states.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.domain.model.RepositoryModel
import co.feip.fefu2025.presentation.ui.component.ui.gitlab.GitLabCard

@Composable
fun Loaded(
    repositories: List<RepositoryModel>, navigateRepository: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(repositories) { repo ->
            Box(
                modifier = Modifier.border(
                    width = 4.dp, brush = Brush.linearGradient(
                        colors = listOf(Color(0xffbf02b3), Color(0xffd18006))
                    ), shape = RoundedCornerShape(10.dp)
                )
            ) {
                GitLabCard(repositoryName = repo.repositoryName,
                    description = repo.description ?: " ",
                    stars = repo.stars,
                    forks = repo.forks,
                    avatarUrl = repo.avatarUrl,
                    modifier = Modifier.width(350.dp),
                    onCardClick = { navigateRepository(repo.id) })
            }
        }
    }
}
