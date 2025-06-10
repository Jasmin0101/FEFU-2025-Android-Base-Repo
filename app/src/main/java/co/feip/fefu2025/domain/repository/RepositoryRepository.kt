package co.feip.fefu2025.domain.repository

import co.feip.fefu2025.domain.model.RepositoryModel

interface RepositoryRepository {
    suspend fun getAllRepositories(
        page: Int = 1,
        perPage: Int = 20,
        search: String? = null
    ): List<RepositoryModel>

    suspend fun getAllStarredRepositories(
        page: Int = 1,
        perPage: Int = 20
    ): List<RepositoryModel>

    suspend fun getRepositoryById(id: Int): RepositoryModel?

    suspend fun starRepository(id: Int)

    suspend fun unstarRepository(id: Int)
}
