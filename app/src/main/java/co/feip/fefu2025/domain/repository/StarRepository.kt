package co.feip.fefu2025.domain.repository

import android.os.Build
import androidx.annotation.RequiresApi
import co.feip.fefu2025.data.api.GitLabApiClient
import co.feip.fefu2025.domain.model.RepositoryModel
import co.feip.fefu2025.domain.model.toRepository
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class StarRepository {
    private val api = GitLabApiClient.apiService


    private var cachedRepositories: Deferred<List<RepositoryModel>>? = null

    @RequiresApi(Build.VERSION_CODES.O)
    private fun _fetch(scope: CoroutineScope): Deferred<List<RepositoryModel>> =
        scope.async(Dispatchers.IO) {

            var page = 1
            var perPage = 20
            val data = mutableListOf<RepositoryModel>();

            while (true) {
                try {
                    val gitlabProjects = api.getProjects(
                        page = page, perPage = perPage, starred = true
                    )

                    if (gitlabProjects.isEmpty()) break

                    data.addAll(gitlabProjects.map { it.toRepository() })
                    page++
                } catch (e: Exception) {
                    e.printStackTrace()
                    break
                }
            }
            return@async data
        }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getAllStarredRepositoriesPage(
        page: Int = 1,
        perPage: Int = 20,
        scope: CoroutineScope,
    ): List<RepositoryModel> {
        if (cachedRepositories == null) {
            cachedRepositories = _fetch(scope)
        }

        val allRepositories = cachedRepositories!!.await()

        val fromIndex = (page - 1) * perPage
        val toIndex = minOf(fromIndex + perPage, allRepositories.size)

        return if (fromIndex >= allRepositories.size) {
            emptyList()
        } else {
            allRepositories.subList(fromIndex, toIndex)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun isStarred(id: Int, scope: CoroutineScope): Boolean {
        if (cachedRepositories == null) {
            cachedRepositories = _fetch(scope)
        }
        val allRepositories = cachedRepositories!!.await()
        return allRepositories.any { it.id == id }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun toggleStar(
        id: Int, repositoryModel: RepositoryModel, isStarred: Boolean, scope: CoroutineScope
    ): Boolean {
        if (cachedRepositories == null) {
            cachedRepositories = _fetch(scope)
        }
        val allRepositories = cachedRepositories!!.await()

        try {

            if (isStarred) {
                api.starProject(id)

                val newRepositories: List<RepositoryModel> = allRepositories + repositoryModel
                cachedRepositories = CompletableDeferred<List<RepositoryModel>>(newRepositories)
            }
            if (!isStarred) {
                api.unstarProject(id)

                val newRepositories = allRepositories.filter { it.id != id }
                cachedRepositories = CompletableDeferred(newRepositories)
            }


        } catch (e: Exception) {
            return false;
        }

        return true;
    }

}
