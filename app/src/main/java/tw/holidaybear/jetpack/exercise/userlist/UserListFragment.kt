package tw.holidaybear.jetpack.exercise.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import tw.holidaybear.jetpack.exercise.databinding.FragmentUserListBinding
import tw.holidaybear.jetpack.exercise.util.EventObserver

class UserListFragment : Fragment() {

    private lateinit var viewModel: UserListViewModel

    private lateinit var viewDataBinding: FragmentUserListBinding

    private lateinit var listAdapter: UserListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(UserListViewModel::class.java)
        viewDataBinding = FragmentUserListBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupListAdapter()
        setupNavigation()
    }

    private fun setupListAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            listAdapter = UserListAdapter(viewModel)
            viewDataBinding.userList.adapter = listAdapter
        }
    }

    private fun setupNavigation() {
        viewModel.openUserEvent.observe(this, EventObserver{ argUserLogin ->
            val action = UserListFragmentDirections.nextAction(argUserLogin)
            findNavController().navigate(action)
        })
    }
}