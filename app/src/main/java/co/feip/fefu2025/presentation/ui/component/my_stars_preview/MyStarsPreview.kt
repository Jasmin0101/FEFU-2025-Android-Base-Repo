package co.feip.fefu2025.presentation.ui.component.my_stars_preview

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.presentation.ui.component.my_stars_preview.states.MyStarsPreviewStates
import co.feip.fefu2025.presentation.ui.component.my_stars_preview.states.ui.Empty
import co.feip.fefu2025.presentation.ui.component.my_stars_preview.states.ui.Error
import co.feip.fefu2025.presentation.ui.component.my_stars_preview.states.ui.Loaded
import co.feip.fefu2025.presentation.ui.component.my_stars_preview.states.ui.Loading
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyStarsPreview(
    viewModel: MyStarsPreviewViewModel = koinViewModel(),
    navigateMyStars: () -> Unit,
    navigateRepository: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        viewModel.refresh()
    }
    TextButton(
        onClick = navigateMyStars,
        modifier = modifier.padding(vertical = 8.dp),
    ) {
        Text(
            text = "My Stars", fontSize = 20.sp, fontWeight = FontWeight.Bold
        )
    }

    when (val state = viewModel.state) {
        is MyStarsPreviewStates.Loading -> {
            Loading()
        }

        is MyStarsPreviewStates.Error -> {
            Error()
        }

        is MyStarsPreviewStates.Empty -> {
            Empty()
        }

        is MyStarsPreviewStates.Loaded -> {
            Loaded(
                repositories = state.list, navigateRepository = navigateRepository
            )
        }


    }
}
