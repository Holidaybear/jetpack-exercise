package tw.holidaybear.jetpack.exercise.userdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import tw.holidaybear.jetpack.exercise.databinding.FragmentUserDetailBinding

class UserDetailFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentUserDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewModel = ViewModelProviders.of(this).get(UserDetailViewModel::class.java)
        viewDataBinding = FragmentUserDetailBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupUserDetail()
    }

    private fun setupUserDetail() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            val safeArgs: UserDetailFragmentArgs by navArgs()
            val userLogin = safeArgs.argUserLogin
            viewModel.loadUser(userLogin)
        }
    }
}