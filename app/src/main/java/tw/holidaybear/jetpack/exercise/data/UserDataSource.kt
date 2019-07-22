package tw.holidaybear.jetpack.exercise.data

interface UserDataSource {

    suspend fun getUsers(): List<User>

    suspend fun getUser(login: String): User

    suspend fun saveUsers(users: List<User>)

    suspend fun updateUser(user: User)
}