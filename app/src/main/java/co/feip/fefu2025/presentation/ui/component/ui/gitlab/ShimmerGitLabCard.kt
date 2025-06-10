package co.feip.fefu2025.presentation.ui.component.ui.gitlab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.presentation.ui.component.ui.shimmerEffect

@Composable
fun ShimmerGitLabCard(
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xffeee6f6)
        )

    ) {
        Row(modifier = modifier.padding(16.dp)) {

            Box(
                modifier = modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .shimmerEffect()
            )

            Spacer(modifier = modifier.width(16.dp))
            Column {

                Box(
                    modifier = modifier
                        .height(20.dp)
                        .width(300.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .shimmerEffect()
                )
                Spacer(modifier.size(20.dp))
                Box(
                    modifier = modifier
                        .height(20.dp)
                        .width(250.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .shimmerEffect()
                )

            }
        }
    }

}

@Composable
@Preview
private fun PreviewCard() {
    ShimmerGitLabCard()
}