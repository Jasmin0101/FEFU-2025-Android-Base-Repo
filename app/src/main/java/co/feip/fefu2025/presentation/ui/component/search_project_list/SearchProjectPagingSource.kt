package co.feip.fefu2025.presentation.ui.component.search_project_list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import co.feip.fefu2025.domain.model.RepositoryModel
import co.feip.fefu2025.domain.usecase.GetSearchRepositoriesUseCase
import kotlin.math.max


class SearchProjectPagingSource(
    private val getSearchRepositoriesUseCase: GetSearchRepositoriesUseCase,
    private val query: String
) :
    PagingSource<Int, RepositoryModel>() {
    private var STARTING_KEY = 1

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryModel> {

        val page = params.key ?: STARTING_KEY
        val perPage = params.loadSize

        val repositories =
            getSearchRepositoriesUseCase.execute(page = page, perPage = perPage, search = query)

        return LoadResult.Page(
            data = repositories,
            prevKey = when (page) {
                STARTING_KEY -> null
                else -> ensureValidKey(key = page - 1)
            },
            nextKey = page + 1
        )

    }

    override fun getRefreshKey(state: PagingState<Int, RepositoryModel>): Int? {
        return null
    }

    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)
}