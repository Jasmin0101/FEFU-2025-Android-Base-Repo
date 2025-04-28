package co.feip.fefu2025.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import co.feip.fefu2025.data.repository_mock.repositories
import co.feip.fefu2025.domain.model.Repository
import kotlinx.coroutines.delay


class RepositoryRepository {

    suspend  fun getAllRepositories(): List<Repository> {
        delay(5000)
        return repositories
    }
    suspend  fun getRepositoryById(id: Int): Repository? {
        delay(5000)
        return repositories.find { it.id == id }
    }
}
