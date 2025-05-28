package co.feip.fefu2025.presentation.ui.features.search_project_list

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

class SearchProjectListViewModel(private val getRepositoriesUseCase: GetRepositoriesUseCase) :
    ViewModel() {

    private val _repositories = mutableStateOf<List<RepositoryModel>>(emptyList())
    val repositories: State<List<RepositoryModel>> = _repositories



    var isLoading by mutableStateOf(false)
    private var currentPage = 1
    private var currentStarPage = 1
    private val perPage = 20

    @RequiresApi(Build.VERSION_CODES.O)
    fun searchRepositories(query: String) {
        _repositories.value = emptyList()
        currentPage = 1
        loadNextPage(query)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadNextPage(query: String) {
        if (isLoading) return
        isLoading = true

        viewModelScope.launch {
            try {
                val nextPage = getRepositoriesUseCase.executeSearch(page = currentPage, perPage = perPage, search =  query  )
                _repositories.value += nextPage

                currentPage++
                Log.d("page", currentPage.toString())

            } catch (e: Exception) {

            } finally {
                isLoading = false
            }
        }
    }
}