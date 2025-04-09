package co.feip.fefu2025.presentation.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import co.feip.fefu2025.domain.model.Repository
import co.feip.fefu2025.domain.usecase.GetRepositoriesUseCase

@RequiresApi(Build.VERSION_CODES.O)
class RepositoriesViewModel(
    private  val getRepositoriesUseCase:GetRepositoriesUseCase = GetRepositoriesUseCase()
):ViewModel(){
    private val _repositories = mutableStateOf<List<Repository>>(emptyList())
    val repositories: State<List<Repository>> = _repositories

    init {
        loadRepositories()
    }
     @RequiresApi(Build.VERSION_CODES.O)
     private  fun loadRepositories(){
         _repositories.value = getRepositoriesUseCase.execute()
     }
}