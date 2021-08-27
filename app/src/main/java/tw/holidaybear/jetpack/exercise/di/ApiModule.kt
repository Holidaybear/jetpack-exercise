package tw.holidaybear.jetpack.exercise.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tw.holidaybear.jetpack.exercise.API_ENDPOINT
import tw.holidaybear.jetpack.exercise.API_TOKEN
import tw.holidaybear.jetpack.exercise.data.GitHubService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideGitHubService(): GitHubService {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor {
                val original = it.request()
                val request = original.newBuilder()
                    .header("Authorization", "Bearer $API_TOKEN")
                it.proceed(request.build())
            }
            .build()

        return Retrofit.Builder()
            .baseUrl(API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(GitHubService::class.java)
    }
}
