package co.feip.fefu2025.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import co.feip.fefu2025.data.repository.RepositoryRepository
import co.feip.fefu2025.domain.model.RepositoryModel

class GetRepositoryUseCase(
    private val repository: RepositoryRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun execute(id: Int): RepositoryModel? {
        return repository.getRepositoryById(id)
    }
}
