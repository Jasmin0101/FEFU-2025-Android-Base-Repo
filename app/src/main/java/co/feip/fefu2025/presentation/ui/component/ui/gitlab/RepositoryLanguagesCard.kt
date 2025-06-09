package co.feip.fefu2025.presentation.ui.component.ui.gitlab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RepositoryLanguages(
    languages: List<LanguageData>, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
    ) {
        Text(
            text = "Languages",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Spacer(modifier = modifier.height(8.dp))

        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(12.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color.Gray.copy(alpha = 0.3f)),
        ) {
            languages.forEach { lang ->
                Box(
                    modifier = modifier
                        .weight(lang.percentage)
                        .fillMaxHeight()
                        .background(lang.color),
                )
            }
        }

        Spacer(modifier = modifier.height(12.dp))

        FlowRow(
            modifier = modifier.fillMaxWidth(),
        ) {
            languages.forEach { lang ->
                Spacer(
                    modifier
                        .width(10.dp)
                        .height(20.dp)
                )
                LanguageItem(lang)
            }
        }
    }
}

@Composable
fun LanguageItem(
    language: LanguageData, modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(

            painter = painterResource(id = R.drawable.rounded_circle),
            contentDescription = "Heart",
            tint = language.color,
            modifier = modifier.size(14.dp)
        )
        Spacer(modifier = modifier.width(4.dp))
        Text(
            text = "${language.name} ${language.percentage}%",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}

data class LanguageData(
    val name: String,
    val percentage: Float,
    val color: Color,
)

@Preview
@Composable
private fun PreviewRepositoryLanguages(modifier: Modifier = Modifier) {
    val languages = listOf(
        LanguageData("C++", 85.7f, Color(0xFFFF4081)),
        LanguageData("C#", 3.5f, Color(0xFF4CAF50)),
        LanguageData("Java", 3.1f, Color(0xFFFF9800)),
        LanguageData("C", 2.3f, Color(0xFF616161)),
        LanguageData("GLSL", 1.7f, Color(0xFF78909C)),
        LanguageData("Objective-C++", 1.4f, Color(0xFF673AB7)),
        LanguageData("Other", 2.3f, Color(0xFFA83A99)),
    )
    RepositoryLanguages(languages)
}
