package co.feip.fefu2025.domain.model

import java.time.LocalDate

data class RepositoryModel(
    val id: Int,
    val repositoryName: String,
    val description: String?,
    val stars: Int,
    val forks: Int,
    val avatarRes: Int? = null,
    val avatarUrl: String? = null,
    val isMyRepo: Boolean? = false,
    val languages: Map<String, Float>,
    val date: LocalDate
)
