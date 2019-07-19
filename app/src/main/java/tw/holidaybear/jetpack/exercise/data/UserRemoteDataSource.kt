package tw.holidaybear.jetpack.exercise.data

object UserRemoteDataSource : UserDataSource {

    private val api = GitHubAPI.create()

    override suspend fun getUsers(): List<User> {
        val response = api.getUsers(API_TOKEN)
        return response.body() ?: emptyList()
    }

    override suspend fun getUser(login: String): User {
        val response = api.getUser(API_TOKEN, login)
        return response.body() ?: defaultUser
    }
}