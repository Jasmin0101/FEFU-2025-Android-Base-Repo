package co.feip.fefu2025.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import co.feip.fefu2025.domain.model.RepositoryModel
import co.feip.fefu2025.domain.repository.RepositoryRepository
import co.feip.fefu2025.domain.repository.StarRepository
import kotlinx.coroutines.CoroutineScope

class GetRepositoryUseCase(
    private val repository: RepositoryRepository,
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun execute(id: Int): RepositoryModel? {
        return repository.getRepositoryById(id)
    }
}

class GetToggleStarredUseCase(
    private val starRepository: StarRepository,
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun execute(
        id: Int, repositoryModel: RepositoryModel, isStarred: Boolean, scope: CoroutineScope
    ): Boolean {
        return starRepository.toggleStar(
            id,
            repositoryModel,
            isStarred,
            scope,
        )
    }
}

class GetIsStarredUseCase(
    private val starRepository: StarRepository,
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun execute(id: Int, scope: CoroutineScope): Boolean {
        return starRepository.isStarred(id, scope)
    }

}
