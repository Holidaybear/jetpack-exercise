package tw.holidaybear.jetpack.exercise

import android.app.Application
import tw.holidaybear.jetpack.exercise.data.UserRepository
import tw.holidaybear.jetpack.exercise.util.Injection

class UserApp : Application() {

    val userRepository: UserRepository
        get() = Injection.provideUserRepository(this)
}