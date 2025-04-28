package co.feip.fefu2025.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import co.feip.fefu2025.data.repository.RepositoryRepository
import co.feip.fefu2025.domain.model.Repository

class GetRepositoryUseCase(
    private val repository: RepositoryRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun execute(id: Int): Repository? {
        return repository.getRepositoryById(id)
    }
}
