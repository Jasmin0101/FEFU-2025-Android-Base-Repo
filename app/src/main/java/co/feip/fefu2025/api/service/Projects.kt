import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface GitLabApiService {
    @GET("projects")
    suspend fun getProjects(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20,
        @Query("search") search: String? = null,
        @Query("starred") starred : Boolean? = null,
    ): List<RepositoryResponse>

    @GET("projects/{id}")
    suspend fun getProjectById(
        @Path("id") id: Int
    ): RepositoryResponse

    @GET("projects/{id}/languages")
    suspend fun  getLanguages(
        @Path("id") id : Int
    ) :  Map<String, Float>

    @POST("projects/{id}/star")
    suspend fun starProject(
        @Path("id") id: Int
    )

    @POST("projects/{id}/unstar")
    suspend fun unstarProject(
        @Path("id") id: Int
    )
}
