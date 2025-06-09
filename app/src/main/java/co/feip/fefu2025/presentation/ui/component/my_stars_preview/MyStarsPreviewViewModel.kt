package co.feip.fefu2025.presentation.ui.component.my_stars_preview

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.usecase.GetStarredRepositoriesUseCase
import co.feip.fefu2025.presentation.ui.component.my_stars_preview.states.MyStarsPreviewStates

@RequiresApi(Build.VERSION_CODES.O)
class MyStarsPreviewViewModel(
    private val getStarredRepositoriesUseCase: GetStarredRepositoriesUseCase,
) : ViewModel() {


    private val perPage = 10
    var state by mutableStateOf<MyStarsPreviewStates>(MyStarsPreviewStates.Loading)
        private set

    suspend fun refresh() {
        state = MyStarsPreviewStates.Loading
        try {
            val result = getStarredRepositoriesUseCase.execute(1, perPage, this.viewModelScope)
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


