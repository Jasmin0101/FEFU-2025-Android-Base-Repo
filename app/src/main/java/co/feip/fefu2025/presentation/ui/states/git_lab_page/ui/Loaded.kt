package co.feip.fefu2025.presentation.ui.states.git_lab_page.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.domain.model.RepositoryModel
import co.feip.fefu2025.presentation.ui.component.git_lab_page.GitLabPageViewModel
import co.feip.fefu2025.presentation.ui.pages.CustomFlexBoxScreen
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LoadedGitLabState(
    paddingValues: PaddingValues,
    viewModel: GitLabPageViewModel = koinViewModel(),
    repositoryId: Int,
    repository: RepositoryModel,
    isStared: Boolean,
    modifier: Modifier = Modifier
) {


    Column(
        modifier = modifier
            .padding(paddingValues)
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.medium)
            .padding(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                    shape = MaterialTheme.shapes.medium
                )
                .padding(16.dp),
        ) {
            if (repository?.avatarUrl != null) {
                AsyncImage(
                    model = repository?.avatarUrl,
                    contentDescription = "Project Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .size(48.dp)
                        .clip(CircleShape)
                )
            } else {
                Box(
                    modifier = modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = repository?.repositoryName?.firstOrNull()?.uppercase() ?: "?",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = modifier.width(12.dp))

            repository?.let {
                Text(
                    text = it.repositoryName,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }

        repository?.let {
            Text(
                text = it.description ?: " ",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = modifier.padding(top = 8.dp),
            )

            Text(
                text = "📅 Created: ${it.date.dayOfMonth.toString().padStart(2 , '0')}.${it.date.monthValue.toString().padStart(2 , '0')}.${it.date.year}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = modifier.padding(top = 4.dp),
            )

            Row(modifier = modifier.padding(top = 8.dp)) {
                Text(
                    text = "⭐ Stars: ${it.stars}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = modifier.width(16.dp))
                Text(
                    text = "🔄 Forks: ${it.forks}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            it.languages.takeIf { langs -> langs.isNotEmpty() }?.let { languages ->
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                        .background(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(16.dp),
                ) {
                    Text(text = "Languages:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Spacer(modifier = modifier.height(8.dp))
                    CustomFlexBoxScreen(languages.keys.toList())
                }
            }

            Spacer(modifier = modifier.height(16.dp))

            Button(
                onClick = {

                    viewModel.toggleStarred(repositoryId, repository, !isStared)
                },
                modifier = modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Icon(
                        imageVector = if (isStared) Icons.Default.Star else Icons.Default.StarBorder,
                        contentDescription = if (isStared) "Unstar" else "Star",
                        tint = if (isStared) Color.Yellow else Color.Gray
                    )
                    Spacer(modifier = modifier.width(8.dp))
                    Text(
                        text = if (isStared) "Starred" else "Mark as Favorite",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}
