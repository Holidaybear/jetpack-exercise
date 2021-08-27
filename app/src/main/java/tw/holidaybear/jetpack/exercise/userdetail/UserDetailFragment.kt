package tw.holidaybear.jetpack.exercise.userdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tw.holidaybear.jetpack.exercise.data.User
import tw.holidaybear.jetpack.exercise.databinding.FragmentUserDetailBinding

@AndroidEntryPoint
class UserDetailFragment : Fragment() {

    private val viewModel: UserDetailViewModel by viewModels()

    private lateinit var binding: FragmentUserDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUserDetail()
        setupObservers()
    }

    private fun setupUserDetail() {
        val safeArgs: UserDetailFragmentArgs by navArgs()
        val userLogin = safeArgs.argUserLogin
        viewModel.getUserDetail(userLogin)
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.uiState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    handleUiState(it)
                }
        }
    }

    private fun handleUiState(uiState: DetailUiState) {
        when (uiState) {
            is DetailUiState.StateLoading -> Unit
            is DetailUiState.StateError -> Unit
            is DetailUiState.StateLoaded -> {
                updateUI(uiState.user)
            }
        }
    }

    private fun updateUI(user: User) {
        binding.userAvatar.load(user.imageUrl) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
        binding.userName.text = user.name
        binding.userBio.text = user.bio
        binding.userLogin.text= user.login
        binding.userAdminBadge.visibility = if (user.isAdmin) View.VISIBLE else View.GONE
        binding.userLocation.text = user.location
        binding.userBlog.text = user.blog
    }
}
