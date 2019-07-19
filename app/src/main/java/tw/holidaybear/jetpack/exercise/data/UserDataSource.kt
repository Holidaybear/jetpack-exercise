package tw.holidaybear.jetpack.exercise.data

interface UserDataSource {

    suspend fun getUsers(): List<User>

    suspend fun getUser(login: String): User
}