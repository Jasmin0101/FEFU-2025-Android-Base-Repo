package co.feip.fefu2025.data.repository

import android.icu.text.StringSearch
import android.os.Build
import androidx.annotation.RequiresApi
import co.feip.fefu2025.api.GitLabApiClient
import co.feip.fefu2025.domain.model.RepositoryModel
import toRepository


class RepositoryRepository {

    private val api = GitLabApiClient.apiService

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getAllRepositories(page: Int = 1, perPage: Int = 20, search: String? = null): List<RepositoryModel> {
        try {
            val gitlabProjects = api.getProjects(
                page = page,
                perPage = perPage,
                search =  search
            )
            return gitlabProjects.map { it.toRepository() }
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getAllStarredRepositories(page: Int = 1, perPage: Int = 20): List<RepositoryModel> {
        try {
            val gitlabProjects = api.getProjects(
                page = page,
                perPage = perPage,
                starred = true,
            )
            return gitlabProjects.map { it.toRepository() }
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getRepositoryById(id: Int): RepositoryModel? {
        return try {
            val gitLabProject = api.getProjectById(id)
            val languages = try {
                api.getLanguages(id)
            } catch (e: Exception) {
                e.printStackTrace()
                emptyMap()
            }

            gitLabProject.toRepository(languages)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}
