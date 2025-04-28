package co.feip.fefu2025.presentation.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import co.feip.fefu2025.domain.model.Repository
import co.feip.fefu2025.domain.usecase.GetRepositoriesUseCase
import co.feip.fefu2025.navigation.Destination
import co.feip.fefu2025.navigation.Navigator
import co.feip.fefu2025.presentation.ui.states.home_page.HomePageState
import kotlinx.coroutines.launch
import kotlin.random.Random

class  RepositoriesViewModel(
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
    private val navigator: Navigator
) : ViewModel() {

    private val _repositories = mutableStateOf<List<Repository>>(emptyList())
    val repositories: State<List<Repository>> = _repositories
    var homePageState by mutableStateOf<HomePageState>(HomePageState.Loading)
        private set

    init {
        loadRepositories()
    }

    private fun loadRepositories() {
        viewModelScope.launch {
            try {
                // Случайно генерируем ошибку или успешный результат
                if (Random.nextBoolean()) {
                    // Эмуляция успешного получения данных
                    _repositories.value = getRepositoriesUseCase.execute()
                    homePageState = HomePageState.Loaded
                } else {
                    // Эмуляция ошибки
                    throw Exception("Random error occurred")
                }
            } catch (e: Exception) {
                homePageState = HomePageState.Error
            }
        }
    }

    // Функция для повторной загрузки данных
    fun retry() {
        // Сначала меняем состояние на Loading
        homePageState = HomePageState.Loading
        // Затем вызываем загрузку данных
        loadRepositories()
    }
    fun navigateRepository(id: Int) {
        viewModelScope.launch {
            navigator.navigate(
                destination = Destination.Repository(id),
            )
        }
    }
    fun navigateMyStars() {
        viewModelScope.launch {
            navigator.navigate(
                destination = Destination.MyStars,
            )
        }
    }

    fun navigateHome(){
        viewModelScope.launch {
            navigator.navigate(
                destination = Destination.HomePage
            )
        }
    }
}
