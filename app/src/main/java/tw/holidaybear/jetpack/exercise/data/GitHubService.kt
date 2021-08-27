package tw.holidaybear.jetpack.exercise.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tw.holidaybear.jetpack.exercise.data.User

interface GitHubService {

    @GET("/users")
    suspend fun getUsers(@Query("page") page: Int, @Query("per_page") itemsPerPage: Int): List<User>

    @GET("/users/{login}")
    suspend fun getUser(@Path("login") login: String): Response<User>
}
