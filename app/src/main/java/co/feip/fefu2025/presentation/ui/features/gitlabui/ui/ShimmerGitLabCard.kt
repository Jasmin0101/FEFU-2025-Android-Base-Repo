package co.feip.fefu2025.presentation.ui.features.gitlabui.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.presentation.ui.features.ui.shimmerEffect

@Composable
fun ShimmerGitLabCard(
    modifier: Modifier = Modifier
){
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xffeee6f6)
        )

    ) {
        Row(modifier = Modifier.padding(16.dp)) {

            Box(modifier = Modifier.size(48.dp).clip(CircleShape).shimmerEffect())

            Spacer(modifier = Modifier.width(16.dp))
            Column {

                Box(modifier = Modifier.height(20.dp).width(300.dp).clip(RoundedCornerShape(8.dp)).shimmerEffect())
                Spacer(Modifier.size(20.dp))
                Box(modifier = Modifier.height(20.dp).width(250.dp).clip(RoundedCornerShape(8.dp)).shimmerEffect())

            }
        }
    }

}

@Composable
@Preview
private  fun PreviewCard(){
ShimmerGitLabCard(modifier = Modifier)
}