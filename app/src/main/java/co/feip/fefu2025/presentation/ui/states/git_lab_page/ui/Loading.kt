package co.feip.fefu2025.presentation.ui.states.git_lab_page.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.presentation.ui.component.ui.shimmerEffect

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LoadingGitLabState(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .padding(paddingValues)
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.medium)
            .padding(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                    shape = MaterialTheme.shapes.medium
                )
                .padding(16.dp),
        ) {

            Box(
                modifier = modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .shimmerEffect()
            )

            Spacer(modifier = modifier.width(12.dp))


            Box(
                modifier = modifier
                    .height(40.dp)
                    .width(300.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .shimmerEffect()
            )


        }




        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .background(
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                    shape = MaterialTheme.shapes.medium
                )
                .padding(16.dp),
        ) {
            Text(text = "Languages:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(20.dp))

        }
    }

}


//@RequiresApi(Build.VERSION_CODES.O)
//@Preview
//@Composable
//private  fun PreviewGitLabLoading(){
//    LoadingGitLabState()
//}