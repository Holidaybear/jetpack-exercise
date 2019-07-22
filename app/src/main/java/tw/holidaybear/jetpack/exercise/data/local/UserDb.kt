package tw.holidaybear.jetpack.exercise.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import tw.holidaybear.jetpack.exercise.data.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDb : RoomDatabase() {

    abstract fun userDao(): UserDao
}