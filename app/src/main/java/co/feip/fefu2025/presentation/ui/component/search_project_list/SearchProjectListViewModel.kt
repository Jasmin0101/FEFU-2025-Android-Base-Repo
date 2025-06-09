package co.feip.fefu2025.presentation.ui.component.search_project_list

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
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest

class SearchProjectListViewModel(
    private val getSearchRepositoriesUseCase: GetSearchRepositoriesUseCase,
) : ViewModel() {
    private val perPage = 20
    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()


    fun searchRepositories(newQuery: String) {
        _query.value = newQuery
    }

    val items: Flow<PagingData<RepositoryModel>> = _query
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            if (query.isBlank()) {
                emptyFlow()
            } else {
                Pager(
                    config = PagingConfig(pageSize = perPage, enablePlaceholders = false),
                    pagingSourceFactory = {
                        SearchProjectPagingSource(getSearchRepositoriesUseCase, query)
                    }
                ).flow
            }
        }

        .cachedIn(viewModelScope)


}
