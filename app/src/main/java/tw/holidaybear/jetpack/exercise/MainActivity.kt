package tw.holidaybear.jetpack.exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.*
import androidx.lifecycle.ViewModelProvider
import androidx.ui.core.setContent
import tw.holidaybear.jetpack.exercise.ui.UsersApp
import tw.holidaybear.jetpack.exercise.util.observe

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContent {
            +observe(viewModel.users)
            UsersApp(viewModel)
        }
    }
}
