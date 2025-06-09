package co.feip.fefu2025.presentation.ui.component.my_stars_list


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import co.feip.fefu2025.domain.model.RepositoryModel
import co.feip.fefu2025.domain.usecase.GetStarredRepositoriesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlin.math.max


class MyStarsPaginSource(
    private val getRepositoriesUseCase: GetStarredRepositoriesUseCase,
    private var scope: CoroutineScope
) :
    PagingSource<Int, RepositoryModel>() {
    private var STARTING_KEY = 1

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryModel> {
        val page = params.key ?: STARTING_KEY
        val perPage = params.loadSize
        val repositories =
            getRepositoriesUseCase.execute(page = page, perPage = perPage, scope = scope)

        val nextKey = if (repositories.isEmpty()) {
            null
        } else {
            page + 1
        }

        return LoadResult.Page(
            data = repositories,
            prevKey = if (page == STARTING_KEY) null else ensureValidKey(page - 1),
            nextKey = nextKey
        )
    }

    override fun getRefreshKey(state: PagingState<Int, RepositoryModel>): Int? {
        return null
    }

    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)
}