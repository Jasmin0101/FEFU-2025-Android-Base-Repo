package co.feip.fefu2025.presentation.ui.features.all_projects_list

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import co.feip.fefu2025.domain.model.RepositoryModel
import co.feip.fefu2025.domain.usecase.GetRepositoriesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class AllProjectsListViewModel(
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
) : ViewModel() {

    private val _repositories = mutableStateOf<List<RepositoryModel>>(emptyList())
    val repositories: State<List<RepositoryModel>> = _repositories
    var allRepositories: List<RepositoryModel> = emptyList()

    var isLoading by mutableStateOf(false)
    private var currentPage = 1
    private var currentStarPage = 1
    private val perPage = 20

    val items: Flow<PagingData<RepositoryModel>> = Pager(
        config = PagingConfig(pageSize = perPage, enablePlaceholders = false),
        pagingSourceFactory = { AllProjectsPagingSource()}
    )
        .flow
        .cachedIn(viewModelScope)



    init {
        loadRepositories()
    }

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

    private fun loadRepositories() {
        currentPage = 1
        _repositories.value = emptyList()
        allRepositories = emptyList()
        loadNextPage()
    }
}