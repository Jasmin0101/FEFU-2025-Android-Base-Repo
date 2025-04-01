import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.git_lab_ui.PreviewGitLabCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage() {
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp) // Ограниченная высота AppBar
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                ),
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            placeholder = { Text("Search...") },
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(16.dp))
                                .border(
                                    width = 2.dp,
                                    brush = Brush.linearGradient(
                                        colors = listOf(Color(0xffbf02b3), Color(0xffd18006))
                                    ),
                                    shape = RoundedCornerShape(16.dp)
                                ),
                            singleLine = true
                        )
                        Button(
                            onClick = { },
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.height(56.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Search,
                                contentDescription = "Search",
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                }
            )
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)

        ) {
            Text(
                text = "My Stars",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )


            LazyRow(
                modifier = Modifier.fillMaxWidth().height(128.dp),
                contentPadding = PaddingValues(start = 16.dp , end=16.dp)

            ) {
                items(2) { index ->
                        PreviewGitLabCard()
                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "All Projects",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp)
            ) {
                items(10) { index ->
                        PreviewGitLabCard()
                }
            }
        }
    }
}




@Composable
@Preview
fun PreviewHomePage() {
    HomePage()
}
