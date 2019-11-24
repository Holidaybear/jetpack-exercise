package tw.holidaybear.jetpack.exercise.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface GitHubAPI {

    @GET("/users")
    fun getUsers(@Header("Authorization") authorization: String): Deferred<Response<List<User>>>

    @GET("/users/{login}")
    fun getUser(@Header("Authorization") authorization: String, @Path("login") login: String): Deferred<Response<User>>

    companion object {
        fun create(): GitHubAPI {
            return Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(GitHubAPI::class.java)
        }
    }
}