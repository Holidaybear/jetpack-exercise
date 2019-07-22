package tw.holidaybear.jetpack.exercise.data.local

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tw.holidaybear.jetpack.exercise.data.User
import tw.holidaybear.jetpack.exercise.data.UserDataSource

class UserLocalDataSource(private val userDao: UserDao) : UserDataSource {

    override suspend fun getUsers(): List<User> {
        return withContext(Dispatchers.IO) {
            return@withContext userDao.getUsers()
        }
    }

    override suspend fun getUser(login: String): User {
        return withContext(Dispatchers.IO) {
            return@withContext userDao.getUser(login)
        }
    }

    override suspend fun saveUsers(users: List<User>) {
        userDao.saveUsers(users)
    }

    override suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
}