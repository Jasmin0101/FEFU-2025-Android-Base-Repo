package co.feip.fefu2025.presentation.ui.states.git_lab_page.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ErrorGitLabState(
    modifier: Modifier = Modifier,
) {


    Column(
        modifier = modifier
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.medium)
            .padding(16.dp),
    ) {
        Spacer(Modifier.height(50.dp))
        TextButton(
            onClick = {

            },
            modifier = Modifier.padding(vertical = 8.dp),
        ) {
            Text(
                text = "Oops, something's wrong.:/\nRepeat?",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
