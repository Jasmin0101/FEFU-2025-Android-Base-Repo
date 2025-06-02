package co.feip.fefu2025.presentation.ui.features.my_stars_preview

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import co.feip.fefu2025.domain.usecase.GetRepositoriesUseCase
import co.feip.fefu2025.presentation.ui.states.git_lab_page.MyStarsPreviewStates

@RequiresApi(Build.VERSION_CODES.O)
class MyStarsPreviewViewModel(
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
) : ViewModel() {


    private val perPage = 10
    var state by mutableStateOf<MyStarsPreviewStates>(MyStarsPreviewStates.Loading)
        private set

    suspend fun refresh() {
        state = MyStarsPreviewStates.Loading
        try {
            val result = getRepositoriesUseCase.executeStarred(1, perPage)
            state = if (result.isEmpty()) {
                MyStarsPreviewStates.Empty
            } else {
                MyStarsPreviewStates.Loaded(result)
            }
        } catch (e: Exception) {
            state = MyStarsPreviewStates.Error
        }
    }

}


