package co.feip.fefu2025.git_lab_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import co.feip.fefu2025.R

@Composable
fun GitLabCard(
    repositoryName: String,
    description: String,
    stars: Int,
    forks: Int,
    avatarRes: Int? = null
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.widthIn(min = 320.dp).height(96.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            if ( avatarRes == null)
            Icon(imageVector = Icons.Outlined.Home, contentDescription = "Home Icon" , modifier =  Modifier.size(48.dp))
            else Image(
                painter = painterResource(id = avatarRes),
                contentDescription = "Project Avatar",
                modifier = Modifier.size(48.dp).clip(CircleShape),
                contentScale = ContentScale.Crop,



            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = repositoryName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Row(modifier = Modifier.padding(top = 8.dp)) {

                    Text(text = "⭐ stars $stars", fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "🔄 forks $forks", fontSize = 14.sp)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewGitLabCard() {
    GitLabCard(
        repositoryName = "Sample Repo",
        description = "This is a sample GitLab repository.",
        stars = 42,
        forks = 10
                ,
        avatarRes = R.drawable.download,
    )
}
