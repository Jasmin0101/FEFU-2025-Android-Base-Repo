package co.feip.fefu2025.api

import GitLabApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GitLabApiClient {
    private const val BASE_URL = "https://gitlab.com/api/v4/"

    val apiService: GitLabApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitLabApiService::class.java)
    }
}
