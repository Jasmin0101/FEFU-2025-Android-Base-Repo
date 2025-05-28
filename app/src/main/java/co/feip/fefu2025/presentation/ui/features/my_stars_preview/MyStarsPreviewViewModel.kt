package co.feip.fefu2025.presentation.ui.features.my_stars_preview

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.model.RepositoryModel
import co.feip.fefu2025.domain.usecase.GetRepositoriesUseCase
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class MyStarsPreviewViewModel(
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
    ) :
    ViewModel() {
    var starredRepositories: List<RepositoryModel> = emptyList()


    private fun loadStartRepositories() {
        viewModelScope.launch {
            try {
                starredRepositories = getRepositoriesUseCase.executeStarred(1, 10)

            } catch (e: Exception) {

            }
        }
    }


}

