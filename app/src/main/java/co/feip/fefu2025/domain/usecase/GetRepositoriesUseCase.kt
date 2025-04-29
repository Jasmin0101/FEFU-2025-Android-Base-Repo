package co.feip.fefu2025.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import co.feip.fefu2025.data.repository.RepositoryRepository
import co.feip.fefu2025.domain.model.Repository

class GetRepositoriesUseCase(
    private val repository: RepositoryRepository
) {

    suspend fun execute(): List<Repository> = repository.getAllRepositories()
}
