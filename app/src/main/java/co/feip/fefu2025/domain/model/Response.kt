package co.feip.fefu2025.domain.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import java.time.OffsetDateTime

data class RepositoryResponse(
    @SerializedName("id") val id: Int,

    @SerializedName("name") val name: String,

    @SerializedName("description") val description: String?,

    @SerializedName("path_with_namespace") val path_with_namespace: String,

    @SerializedName("web_url") val web_url: String,

    @SerializedName("avatar_url") val avatar_url: String?,

    @SerializedName("star_count") val star_count: Int?,

    @SerializedName("forks_count") val forks_count: Int?,

    @SerializedName("created_at") val created_at: String
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
