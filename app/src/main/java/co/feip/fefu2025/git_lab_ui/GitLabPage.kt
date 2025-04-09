package co.feip.fefu2025.git_lab_ui

import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import co.feip.fefu2025.views.custom.ProgrammingLanguageTag
import views.FexBoxLayoutCustom
import co.feip.fefu2025.R
import kotlin.random.Random

data class Date(val year: Int, val month: Int, val day: Int, val hour: Int, val minute: Int, val second: Int)

@Composable
fun GitLabPage(
    repositoryName: String,
    description: String,
    dateCreate: Date,
    stars: Int,
    forks: Int,
    avatarRes: Int? = null,
    programmingLanguages: Array<String>
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.medium)
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), shape = MaterialTheme.shapes.medium)
                    .padding(16.dp)
            ) {
                avatarRes?.let {
                    Image(
                        painter = painterResource(id = avatarRes),
                        contentDescription = "Avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                }
                Text(
                    text = repositoryName,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Text(
                text = description,
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = "📅 Created: ${dateCreate.day}-${dateCreate.month}-${dateCreate.year} ${dateCreate.hour}:${dateCreate.minute}:${dateCreate.second}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp)
            )

            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(text = "⭐ Stars: $stars", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "🔄 Forks: $forks", fontSize = 14.sp, fontWeight = FontWeight.Medium)
            }

            if (programmingLanguages.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), shape = MaterialTheme.shapes.medium)
                        .padding(16.dp)
                ) {
                    Text(text = "Languages:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomFlexBoxScreen(programmingLanguages)
                }
            }
        }
    }
}

@Composable
fun CustomFlexBoxScreen(
    array: Array<String>
) {
    val context = LocalContext.current

    AndroidView(
        factory = { ctx ->
            FexBoxLayoutCustom(ctx).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
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
          .background(MaterialTheme.colorScheme.background , shape = MaterialTheme.shapes.medium)
            .padding(16.dp)
    )
}

@Preview
@Composable
fun PreviewPage() {
    GitLabPage(
        repositoryName = "Sample Repo",
        description = "This is a sample GitLab repository.",
        dateCreate = Date(2025, 3, 25, 14, 30, 45),
        stars = 42,
        forks = 10,
        avatarRes = R.drawable.download,
        programmingLanguages = arrayOf("Java", "Kotlin", "Python", "Ruby", "Dart")
    )
}

fun getRandomColor(): Color {
    return Color(Random.nextInt(0, 256), Random.nextInt(0, 256), Random.nextInt(0, 256))
}