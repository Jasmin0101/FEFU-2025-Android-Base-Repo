package co.feip.fefu2025.presentation.ui.states.git_lab_page.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
    repositoryId : Int,
    modifier: Modifier = Modifier
){
    val repository by viewModel.repository

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
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), shape = MaterialTheme.shapes.medium)
                .padding(16.dp),
        ) {
            repository?.avatarUrl?.let {

                    AsyncImage(
                        model = it ,
                        contentScale = ContentScale.Crop,
                        contentDescription = "Project Avatar",
                        modifier = Modifier.size(48.dp).clip(CircleShape),
//                    contentScale = ContentScale.Crop,
                    )

                Spacer(modifier = Modifier.width(12.dp))
            }

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
        }

        repository?.let {
            Text(
                text = "📅 Created: ${it.date.dayOfMonth}.${it.date.monthValue}.${it.date.year}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp),
            )
        }

        // Показываем количество звезд и форков
        repository?.let {
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(text = "⭐ Stars: ${it.stars}", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "🔄 Forks: ${it.forks}", fontSize = 14.sp, fontWeight = FontWeight.Medium)
            }
        }

        // Если есть языки, показываем их в FlexBox
        repository?.languages?.let { languages ->
            if (languages.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), shape = MaterialTheme.shapes.medium)
                        .padding(16.dp),
                ) {
                    Text(text = "Languages:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomFlexBoxScreen(languages.keys.toList())
                }
            }
        }
    }
}
