package co.feip.fefu2025.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import co.feip.fefu2025.data.repository.RepositoryRepository
import co.feip.fefu2025.domain.model.Repository

class GetRepositoriesUseCase(
    private val repository: RepositoryRepository
) {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun execute(page: Int = 1, perPage: Int = 20): List<Repository> = repository.getAllRepositories(page)

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun executeStarred(page: Int = 1, perPage: Int = 20): List<Repository> = repository.getAllStarredRepositories(page , perPage)

}
