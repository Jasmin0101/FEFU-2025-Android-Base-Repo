package co.feip.fefu2025.presentation.ui.component.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GradientText(text: String, modifier: Modifier = Modifier) {
    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xffbf02b3), Color(0xffd18006)),
        start = Offset(0f, 0f),
        end = Offset(300f, 0f)
    )

    Text(
        text = text, style = TextStyle(
            brush = gradient,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        ), modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp)
    )
}
