package co.feip.fefu2025.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import co.feip.fefu2025.data.repository.RepositoryRepository
import co.feip.fefu2025.data.repository.StarRepository
import co.feip.fefu2025.domain.model.RepositoryModel
import kotlinx.coroutines.CoroutineScope

class GetRepositoryUseCase(
    private val repository: RepositoryRepository,
    private val starRepository: StarRepository,
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun execute(id: Int): RepositoryModel? {
        return repository.getRepositoryById(id)
    }
    suspend fun executeStarred(id: Int) {
        repository.starRepository(id)
    }

    suspend fun executeUnstarred(id: Int) {
        repository.unstarRepository(id)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun executeIsStared(id: Int, scope: CoroutineScope): Boolean {
        return starRepository.isStarred(id, scope )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun executeToggleStared(id: Int, repositoryModel: RepositoryModel, isStarred: Boolean, scope: CoroutineScope): Boolean {
        return starRepository.toggleStar(
            id,
            repositoryModel,
            isStarred,
            scope,
        )
    }
}
