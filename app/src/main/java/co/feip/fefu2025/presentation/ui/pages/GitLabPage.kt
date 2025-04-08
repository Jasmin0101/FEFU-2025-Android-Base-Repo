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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import co.feip.fefu2025.presentation.viewmodel.RepositoryViewModel
import views.FexBoxLayoutCustom
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GitLabPage(
    viewModel: RepositoryViewModel = RepositoryViewModel(),
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
) {
    val repository by viewModel.repository
    if (repository == null) {
        viewModel.loadRepository(1)
    }


    Scaffold { paddingValues ->
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
                repository?.avatarRes?.let {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = "Avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape).border(
                                width = 4.dp,
                                brush = Brush.linearGradient(
                                    colors = listOf(Color(0xffbf02b3), Color(0xffd18006))
                                ),
                                shape = RoundedCornerShape(70.dp)
                            ),
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
                    text = it.description,
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 8.dp),
                )
            }

            // Показываем дату создания репозитория
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun PreviewPage(modifier: Modifier = Modifier) {
    GitLabPage()
}

fun getRandomColor(): Color = Color(Random.nextInt(0, 256), Random.nextInt(0, 256), Random.nextInt(0, 256))
