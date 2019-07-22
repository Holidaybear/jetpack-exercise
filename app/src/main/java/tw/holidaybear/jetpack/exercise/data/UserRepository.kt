package tw.holidaybear.jetpack.exercise.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userRemoteDataSource: UserDataSource,
                     private val userLocalDataSource: UserDataSource) {

    suspend fun getUsers(): List<User> {
        return withContext(Dispatchers.IO) {
            if (userLocalDataSource.getUsers().isEmpty()) {
                val users = userRemoteDataSource.getUsers()
                userLocalDataSource.saveUsers(users)
            }
            return@withContext userLocalDataSource.getUsers()
        }
    }

    suspend fun getUser(login: String): User {
        return withContext(Dispatchers.IO) {
            if (userLocalDataSource.getUser(login).name == null) {
                val user = userRemoteDataSource.getUser(login)
                userLocalDataSource.updateUser(user)
            }
            return@withContext userLocalDataSource.getUser(login)
        }
    }
}