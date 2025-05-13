package co.feip.fefu2025.domain.model

import co.feip.fefu2025.R
import java.time.LocalDate
import java.util.Date

data class Repository(
    val id: Int,
    val repositoryName: String,
    val description: String?,
    val stars: Int,
    val forks: Int,
    val avatarRes: Int? = null,
    val avatarUrl: String? = null,
    val isMyRepo: Boolean? = false,
    val languages:Map<String,Float> ,
    val date: LocalDate
)
