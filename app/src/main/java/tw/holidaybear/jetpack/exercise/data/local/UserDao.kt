package tw.holidaybear.jetpack.exercise.data.local

import androidx.room.*
import tw.holidaybear.jetpack.exercise.data.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<User>

    @Query("SELECT * FROM users WHERE login = :login")
    suspend fun getUser(login: String): User

    @Insert
    fun saveUsers(users: List<User>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: User)
}