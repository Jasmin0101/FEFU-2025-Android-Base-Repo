package co.feip.fefu2025.presentation.ui.component.my_stars_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import co.feip.fefu2025.domain.model.RepositoryModel
import co.feip.fefu2025.domain.usecase.GetStarredRepositoriesUseCase
import kotlinx.coroutines.flow.Flow

class MyStarsListViewModel(
    private val getRepositoriesUseCase: GetStarredRepositoriesUseCase,

    ) : ViewModel() {
    private val perPage = 20


    val items: Flow<PagingData<RepositoryModel>> = Pager(
        config = PagingConfig(pageSize = perPage, enablePlaceholders = false),
        pagingSourceFactory = {
            MyStarsPaginSource(
                getRepositoriesUseCase,
                scope = this.viewModelScope,
            )
        }
    )
        .flow
        .cachedIn(viewModelScope)


}