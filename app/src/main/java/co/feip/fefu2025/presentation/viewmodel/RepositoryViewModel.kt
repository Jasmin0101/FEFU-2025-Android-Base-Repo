package co.feip.fefu2025.presentation.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.model.Repository
import co.feip.fefu2025.domain.usecase.GetRepositoryUseCase
import co.feip.fefu2025.navigation.Destination
import co.feip.fefu2025.navigation.Navigator
import kotlinx.coroutines.launch


class RepositoryViewModel(
    private val getRepositoryUseCase: GetRepositoryUseCase,
    private val navigator: Navigator
) : ViewModel() {

    private val _repository = mutableStateOf<Repository?>(null)
    val repository: State<Repository?> = _repository

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadRepository(id: Int) {
       viewModelScope.launch {  _repository.value = getRepositoryUseCase.execute(id)}
    }

    fun navigateHome() {
        viewModelScope.launch {
            navigator.navigate(
                destination = Destination.HomePage,
            )
        }
    }
}
