package tw.holidaybear.jetpack.exercise.data.remote

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import tw.holidaybear.jetpack.exercise.data.User

interface GitHubAPI {

    @GET("/users")
    suspend fun getUsers(@Header("Authorization") authorization: String): Response<List<User>>

    @GET("/users/{login}")
    suspend fun getUser(@Header("Authorization") authorization: String, @Path("login") login: String): Response<User>

    companion object {
        fun create(): GitHubAPI {
            return Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GitHubAPI::class.java)
        }
    }
}