package co.feip.fefu2025.presentation.ui.component.all_projects_list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import co.feip.fefu2025.domain.model.RepositoryModel
import co.feip.fefu2025.domain.usecase.GetRepositoriesUseCase
import kotlinx.coroutines.flow.Flow

@RequiresApi(Build.VERSION_CODES.O)
class AllProjectsListViewModel(
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
) : ViewModel() {
    private val perPage = 20

    val items: Flow<PagingData<RepositoryModel>> = Pager(
        config = PagingConfig(pageSize = perPage, enablePlaceholders = false),
        pagingSourceFactory = { AllProjectsPagingSource(getRepositoriesUseCase) }
    )
        .flow
        .cachedIn(viewModelScope)

}