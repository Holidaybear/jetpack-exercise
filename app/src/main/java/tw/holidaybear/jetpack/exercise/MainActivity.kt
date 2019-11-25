package tw.holidaybear.jetpack.exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.ui.core.setContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tw.holidaybear.jetpack.exercise.data.API_TOKEN
import tw.holidaybear.jetpack.exercise.data.GitHubAPI
import tw.holidaybear.jetpack.exercise.ui.UserStatus
import tw.holidaybear.jetpack.exercise.ui.UsersApp

class MainActivity : AppCompatActivity() {

    private var userStatus = UserStatus()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UsersApp(userStatus)
        }
    }

    override fun onResume() {
        super.onResume()

        GlobalScope.launch {
            val api = GitHubAPI.create()
            val response = api.getUsers(API_TOKEN)
            withContext(Dispatchers.Main) {
                userStatus.users = response.await().body() ?: emptyList()
            }
        }
    }
}