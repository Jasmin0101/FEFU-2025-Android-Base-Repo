package co.feip.fefu2025.presentation.ui.states.git_lab_page.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.presentation.ui.pages.CustomFlexBoxScreen
import co.feip.fefu2025.presentation.viewmodel.RepositoryViewModel
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LoadedGitLabState(
    paddingValues: PaddingValues,
    viewModel: RepositoryViewModel = koinViewModel(),
    repositoryId: Int,
    modifier: Modifier = Modifier
) {
    val repository by viewModel.repository
    val favorites by viewModel.favorites.collectAsState() // 👈 наблюдаем избранные

    viewModel.loadRepository(repositoryId)

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.medium)
            .padding(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
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
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                )
            } else {
                Box(
                    modifier = Modifier
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

            Spacer(modifier = Modifier.width(12.dp))

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
                modifier = Modifier.padding(top = 8.dp),
            )

            Text(
                text = "📅 Created: ${it.date.dayOfMonth}.${it.date.monthValue}.${it.date.year}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp),
            )

            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(text = "⭐ Stars: ${it.stars}", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "🔄 Forks: ${it.forks}", fontSize = 14.sp, fontWeight = FontWeight.Medium)
            }

            it.languages.takeIf { langs -> langs.isNotEmpty() }?.let { languages ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                        .background(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(16.dp),
                ) {
                    Text(text = "Languages:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomFlexBoxScreen(languages.keys.toList())
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            val isStarred = favorites.contains(it.id)

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = { viewModel.toggleFavorite(it.id) }) {
                    Icon(
                        imageVector = if (isStarred) Icons.Filled.Star else Icons.Outlined.StarBorder,
                        contentDescription = "Toggle Star",
                        tint = if (isStarred) Color(0xFFFFC107) else Color.Gray
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (isStarred) "Starred" else "Mark as Favorite",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}
