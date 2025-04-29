package co.feip.fefu2025.presentation.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.model.Repository
import co.feip.fefu2025.domain.usecase.GetRepositoryUseCase
import co.feip.fefu2025.navigation.Destination
import co.feip.fefu2025.navigation.Navigator
import co.feip.fefu2025.presentation.ui.states.git_lab_page.GitLabPageState
import co.feip.fefu2025.presentation.ui.states.home_page.HomePageState
import co.feip.fefu2025.presentation.ui.states.home_page.MyStarsStatesPage
import kotlinx.coroutines.launch
import kotlin.random.Random


class RepositoryViewModel(
    private val getRepositoryUseCase: GetRepositoryUseCase,
    private val navigator: Navigator
) : ViewModel() {

    private val _repository = mutableStateOf<Repository?>(null)
    val repository: State<Repository?> = _repository
    var gitLabPageState by mutableStateOf<GitLabPageState>(GitLabPageState.Loading)
        private set
    @RequiresApi(Build.VERSION_CODES.O)
    fun loadRepository(id: Int) {

        viewModelScope.launch {
            try {

                if (Random.nextBoolean()) {
                    _repository.value = getRepositoryUseCase.execute(id)
                    gitLabPageState = GitLabPageState.Loaded
                } else {
                    throw Exception("Random error occurred")
                }
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

