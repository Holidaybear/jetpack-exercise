package tw.holidaybear.jetpack.exercise.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userRemoteDataSource: UserDataSource) {

    suspend fun getUsers(): List<User> {
        return withContext(Dispatchers.IO) {
            return@withContext userRemoteDataSource.getUsers()
        }
    }

    suspend fun getUser(login: String): User {
        return withContext(Dispatchers.IO) {
            return@withContext userRemoteDataSource.getUser(login)
        }
    }
}