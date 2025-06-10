package co.feip.fefu2025.presentation.ui.component.search_project_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import co.feip.fefu2025.domain.model.RepositoryModel
import co.feip.fefu2025.domain.usecase.GetSearchRepositoriesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest

class SearchProjectListViewModel(
    private val getSearchRepositoriesUseCase: GetSearchRepositoriesUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val perPage = 20

    companion object {
        private const val QUERY_KEY = "query_key"
    }

    private val _query = MutableStateFlow(savedStateHandle.get<String>(QUERY_KEY) ?: "")
    val query = _query.asStateFlow()

    fun searchRepositories(newQuery: String) {
        _query.value = newQuery
        savedStateHandle[QUERY_KEY] = newQuery
    }

    val items: Flow<PagingData<RepositoryModel>> =
        _query.debounce(300).distinctUntilChanged().flatMapLatest { query ->
            Pager(config = PagingConfig(pageSize = perPage, enablePlaceholders = false),
                pagingSourceFactory = {
                    SearchProjectPagingSource(
                        getSearchRepositoriesUseCase,
                        query
                    )
                }).flow
        }.cachedIn(viewModelScope)
}
