package co.feip.fefu2025.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import co.feip.fefu2025.data.repository_mock.repositories
import co.feip.fefu2025.domain.model.Repository


class RepositoryRepository {

    fun getAllRepositories(): List<Repository> {
        return repositories
    }
    fun getRepositoryById(id: Int): Repository? {
        return repositories.find { it.id == id }
    }
}
