package tw.holidaybear.jetpack.exercise.data.remote

import tw.holidaybear.jetpack.exercise.data.API_TOKEN
import tw.holidaybear.jetpack.exercise.data.User
import tw.holidaybear.jetpack.exercise.data.UserDataSource
import tw.holidaybear.jetpack.exercise.data.defaultUser

class UserRemoteDataSource(private val api: GitHubAPI) : UserDataSource {

    override suspend fun getUsers(): List<User> {
        val response = api.getUsers(API_TOKEN)
        return response.body() ?: emptyList()
    }

    override suspend fun getUser(login: String): User {
        val response = api.getUser(API_TOKEN, login)
        return response.body() ?: defaultUser
    }

    override suspend fun saveUsers(users: List<User>) = Unit

    override suspend fun updateUser(user: User) = Unit
}