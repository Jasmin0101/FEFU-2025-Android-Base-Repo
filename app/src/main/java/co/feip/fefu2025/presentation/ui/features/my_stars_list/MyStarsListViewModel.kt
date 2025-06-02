package co.feip.fefu2025.presentation.ui.features.my_stars_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import co.feip.fefu2025.domain.model.RepositoryModel
import co.feip.fefu2025.domain.usecase.GetRepositoriesUseCase
import co.feip.fefu2025.presentation.ui.features.all_projects_list.MyStarsPaginSource
import kotlinx.coroutines.flow.Flow

class MyStarsListViewModel(
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
) : ViewModel() {
    private val perPage = 20


    val items: Flow<PagingData<RepositoryModel>> = Pager(
        config = PagingConfig(pageSize = perPage, enablePlaceholders = false),
        pagingSourceFactory = { MyStarsPaginSource(getRepositoriesUseCase) }
    )
        .flow
        .cachedIn(viewModelScope)


}