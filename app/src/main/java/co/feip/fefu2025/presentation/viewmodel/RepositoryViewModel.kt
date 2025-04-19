package co.feip.fefu2025.presentation.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import co.feip.fefu2025.domain.model.Repository
import co.feip.fefu2025.domain.usecase.GetRepositoryUseCase

@RequiresApi(Build.VERSION_CODES.O)
class RepositoryViewModel(
    private val getRepositoryUseCase: GetRepositoryUseCase
) : ViewModel() {

    private val _repository = mutableStateOf<Repository?>(null)
    val repository: State<Repository?> = _repository

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadRepository(id: Int) {
        _repository.value = getRepositoryUseCase.execute(id)
    }
}
