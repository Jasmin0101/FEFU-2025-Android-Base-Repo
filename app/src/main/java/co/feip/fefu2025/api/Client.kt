package co.feip.fefu2025.api

import GitLabApiService
import co.feip.fefu2025.BuildConfig
import retrofit2.Retrofit
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

object GitLabApiClient {
    private const val BASE_URL = "https://gitlab.com/api/v4/"

    private fun provideOkHttpClient(): OkHttpClient {

        val logging = HttpLoggingInterceptor { message ->
            android.util.Log.d("GitLabHttp", message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val authInterceptor = Interceptor { chain ->
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("PRIVATE-TOKEN", BuildConfig.GITLAB_API_TOKEN)
                .build()
            chain.proceed(newRequest)
        }

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(authInterceptor)
            .build()
    }

    val apiService: GitLabApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitLabApiService::class.java)
    }
}