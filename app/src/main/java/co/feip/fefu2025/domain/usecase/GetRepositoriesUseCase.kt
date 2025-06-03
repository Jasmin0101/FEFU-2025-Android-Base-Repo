package co.feip.fefu2025.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import co.feip.fefu2025.data.repository.RepositoryRepository
import co.feip.fefu2025.data.repository.StarRepository
import co.feip.fefu2025.domain.model.RepositoryModel
import kotlinx.coroutines.CoroutineScope

class GetRepositoriesUseCase(
    private val repository: RepositoryRepository,
    private val starRepository: StarRepository
) {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun execute(page: Int = 1, perPage: Int = 20): List<RepositoryModel> =
        repository.getAllRepositories(page)

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun executeStarred(
        page: Int = 1,
        perPage: Int = 20,
        scope: CoroutineScope
    ): List<RepositoryModel> = starRepository.getAllStarredRepositoriesPage(page, perPage, scope)

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun executeSearch(
        page: Int = 1,
        perPage: Int = 20,
        search: String = "search"
    ): List<RepositoryModel> = repository.getAllRepositories(page, perPage, search)

}
