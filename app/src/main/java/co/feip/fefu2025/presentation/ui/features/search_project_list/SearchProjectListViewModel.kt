package co.feip.fefu2025.presentation.ui.features.search_project_list

import SearchProjectPagingSource
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import co.feip.fefu2025.domain.model.RepositoryModel
import co.feip.fefu2025.domain.usecase.GetRepositoriesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.retry

class SearchProjectListViewModel(
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : ViewModel() {
    private val perPage = 20
    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()


    fun searchRepositories(newQuery: String) {
         _query.value = newQuery
    }

//    val items: Flow<PagingData<RepositoryModel>> =
//        Pager(
//            config = PagingConfig(pageSize = perPage, enablePlaceholders = false),
//            pagingSourceFactory = { SearchProjectPagingSource(getRepositoriesUseCase, _query.value) }
//        )
//            .flow
//            .cachedIn(viewModelScope)

    val items: Flow<PagingData<RepositoryModel>> = _query
        .flatMapLatest { query ->
            Pager(
                config = PagingConfig(pageSize = perPage, enablePlaceholders = false),
                pagingSourceFactory = { SearchProjectPagingSource(getRepositoriesUseCase, query) }
            ).flow
        }
        .cachedIn(viewModelScope)


}
