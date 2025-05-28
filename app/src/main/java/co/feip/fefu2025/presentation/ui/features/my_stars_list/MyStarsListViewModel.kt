package co.feip.fefu2025.presentation.ui.features.my_stars_list

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.model.RepositoryModel
import co.feip.fefu2025.domain.usecase.GetRepositoriesUseCase
import kotlinx.coroutines.launch

class MyStarsListViewModel(
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
): ViewModel() {

    private val _repositories = mutableStateOf<List<RepositoryModel>>(emptyList())
    val repositories: State<List<RepositoryModel>> = _repositories
    var allRepositories: List<RepositoryModel> = emptyList()
    var starredRepositories: List<RepositoryModel> = emptyList()

    var isLoading by mutableStateOf(false)
    private var currentPage = 1
    private var currentStarPage = 1
    private val perPage = 20

    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadStartRepositories() {
        viewModelScope.launch {
            try {
                starredRepositories = getRepositoriesUseCase.executeStarred(1, 10)

            } catch (e: Exception) {

            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadNextPage() {
        if (isLoading) return
        isLoading = true

        viewModelScope.launch {
            try {
                val nextPage = getRepositoriesUseCase.execute(page = currentPage, perPage = perPage)
                _repositories.value += nextPage
                allRepositories = allRepositories + nextPage
                currentPage++
                Log.d("page", currentPage.toString())

            } catch (e: Exception) {

            } finally {
                isLoading = false
            }
        }
    }
}