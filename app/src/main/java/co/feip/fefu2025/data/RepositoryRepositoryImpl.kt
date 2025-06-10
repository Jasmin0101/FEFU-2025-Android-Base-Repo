package co.feip.fefu2025.data

import android.os.Build
import androidx.annotation.RequiresApi
import co.feip.fefu2025.data.api.GitLabApiClient
import co.feip.fefu2025.domain.model.RepositoryModel
import co.feip.fefu2025.domain.model.toRepository
import co.feip.fefu2025.domain.repository.RepositoryRepository

class RepositoryRepositoryImpl : RepositoryRepository {

    private val api = GitLabApiClient.apiService

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getAllRepositories(
        page: Int, perPage: Int, search: String?
    ): List<RepositoryModel> {
        return try {
            api.getProjects(page, perPage, search).map { it.toRepository() }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getAllStarredRepositories(
        page: Int, perPage: Int
    ): List<RepositoryModel> {
        return try {
            api.getProjects(page, perPage, starred = true).map { it.toRepository() }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getRepositoryById(id: Int): RepositoryModel? {
        return try {
            val project = api.getProjectById(id)
            val languages = try {
                api.getLanguages(id)
            } catch (e: Exception) {
                e.printStackTrace()
                emptyMap()
            }
            project.toRepository(languages)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun starRepository(id: Int) {
        try {
            api.starProject(id)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun unstarRepository(id: Int) {
        try {
            api.unstarProject(id)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}
