package co.feip.fefu2025.domain.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.OffsetDateTime

data class RepositoryResponse(
    val id: Int,
    val name: String,
    val description: String?,
    val path_with_namespace: String,
    val web_url: String,
    val avatar_url: String?,
    val star_count: Int?,
    val forks_count: Int?,
    val created_at: String,

    )

@RequiresApi(Build.VERSION_CODES.O)
fun RepositoryResponse.toRepository(languages: Map<String, Float> = emptyMap()): RepositoryModel {

    return RepositoryModel(
        id = id,
        repositoryName = name,
        description = description,
        stars = star_count ?: 0,
        forks = forks_count ?: 0,
        avatarUrl = avatar_url,
        isMyRepo = false,
        languages = languages ?: emptyMap(),
        date = OffsetDateTime.parse(created_at).toLocalDate(),
    );

}
