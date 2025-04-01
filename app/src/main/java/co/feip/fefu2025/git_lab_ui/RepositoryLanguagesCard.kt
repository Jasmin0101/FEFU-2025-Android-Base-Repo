package com.example.gitlabapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RepositoryLanguages(languages: List<LanguageData>) {
    Column(

        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp).background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = "Languages",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Горизонтальная полоска с цветами языков
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color.Gray.copy(alpha = 0.3f))
        ) {
            languages.forEach { lang ->
                Box(
                    modifier = Modifier
                        .weight(lang.percentage)
                        .fillMaxHeight()
                        .background(lang.color)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Список языков
        FlowRow(
            modifier = Modifier.fillMaxWidth(),

        ) {
            languages.forEach { lang ->
                Spacer(Modifier.width(10.dp).height(20.dp))
                LanguageItem(lang)
            }
        }
    }
}

@Composable
fun LanguageItem(language: LanguageData) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(
            modifier = Modifier.size(10.dp)
        ) {
            drawCircle(color = language.color)
        }
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "${language.name} ${language.percentage}%",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color =    MaterialTheme.colorScheme.onBackground
        )
    }
}

data class LanguageData(val name: String, val percentage: Float, val color: Color)

@Preview
@Composable
fun PreviewRepositoryLanguages() {

    val languages = listOf(
        LanguageData("C++", 85.7f, Color(0xFFFF4081)),
        LanguageData("C#", 3.5f, Color(0xFF4CAF50)),
        LanguageData("Java", 3.1f, Color(0xFFFF9800)),
        LanguageData("C", 2.3f, Color(0xFF616161)),
        LanguageData("GLSL", 1.7f, Color(0xFF78909C)),
        LanguageData("Objective-C++", 1.4f, Color(0xFF673AB7)),
        LanguageData("Other", 2.3f, Color(0xFFA83A99))
    )
    RepositoryLanguages(languages)
}
