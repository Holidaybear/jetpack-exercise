package tw.holidaybear.jetpack.exercise.util

import android.content.Context
import androidx.room.Room
import tw.holidaybear.jetpack.exercise.data.UserDataSource
import tw.holidaybear.jetpack.exercise.data.UserRepository
import tw.holidaybear.jetpack.exercise.data.local.UserDb
import tw.holidaybear.jetpack.exercise.data.local.UserLocalDataSource
import tw.holidaybear.jetpack.exercise.data.remote.GitHubAPI
import tw.holidaybear.jetpack.exercise.data.remote.UserRemoteDataSource

object Injection {

    private var database: UserDb? = null
    @Volatile
    private var repository: UserRepository? = null

    fun provideUserRepository(context: Context): UserRepository {
        synchronized(this) {
            return repository ?: repository ?: createUserRepository(context)
        }
    }

    private fun createUserRepository(context: Context): UserRepository {
        return UserRepository(createUserRemoteDataSource(), createUserLocalDataSource(context))
    }

    private fun createUserRemoteDataSource(): UserDataSource {
        return UserRemoteDataSource(GitHubAPI.create())
    }

    private fun createUserLocalDataSource(context: Context): UserDataSource {
        val database = database ?: createDataBase(context)
        return UserLocalDataSource(database.userDao())
    }

    private fun createDataBase(context: Context): UserDb {
        val result = Room.databaseBuilder(
            context.applicationContext,
            UserDb::class.java, "Users.db"
        ).build()
        database = result
        return result
    }
}