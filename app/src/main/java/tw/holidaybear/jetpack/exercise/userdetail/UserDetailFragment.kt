package tw.holidaybear.jetpack.exercise.userdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import tw.holidaybear.jetpack.exercise.R
import tw.holidaybear.jetpack.exercise.util.getViewModelFactory

class UserDetailFragment : Fragment() {

    private val viewModel by viewModels<UserDetailViewModel> { getViewModelFactory() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUserDetail()
    }

    private fun setupUserDetail() {
//        if (viewModel != null) {
//            val safeArgs: UserDetailFragmentArgs by navArgs()
//            val userLogin = safeArgs.argUserLogin
//            viewModel.loadUser(userLogin)
//        }
    }
}