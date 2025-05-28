package co.feip.fefu2025.presentation.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.model.RepositoryModel
import co.feip.fefu2025.domain.usecase.GetRepositoryUseCase
import co.feip.fefu2025.navigation.Destination
import co.feip.fefu2025.navigation.Navigator
import co.feip.fefu2025.presentation.ui.states.git_lab_page.GitLabPageState
import kotlinx.coroutines.launch


class RepositoryViewModel(
    private val getRepositoryUseCase: GetRepositoryUseCase,
    private val navigator: Navigator
) : ViewModel() {

    private val _repository = mutableStateOf<RepositoryModel?>(null)
    val repository: State<RepositoryModel?> = _repository
    var gitLabPageState by mutableStateOf<GitLabPageState>(GitLabPageState.Loading)
        private set
    @RequiresApi(Build.VERSION_CODES.O)
    fun loadRepository(id: Int) {

        viewModelScope.launch {
            try {


                    _repository.value = getRepositoryUseCase.execute(id)
                    gitLabPageState = GitLabPageState.Loaded

            } catch (e: Exception) {

                gitLabPageState = GitLabPageState.Error

            }
        }
    }
        @RequiresApi(Build.VERSION_CODES.O)
        fun retryRepository(id: Int) {
            gitLabPageState = GitLabPageState.Loading
            loadRepository(id)
        }


        fun navigateHome() {
            viewModelScope.launch {
                navigator.navigate(
                    destination = Destination.HomePage,
                )
            }
        }
    }

