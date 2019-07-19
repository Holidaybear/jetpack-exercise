package tw.holidaybear.jetpack.exercise.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import tw.holidaybear.jetpack.exercise.databinding.FragmentUserListBinding

class UserListFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentUserListBinding

    private lateinit var listAdapter: UserListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewModel = ViewModelProviders.of(this).get(UserListViewModel::class.java)
        viewDataBinding = FragmentUserListBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupListAdapter()
    }

    private fun setupListAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            listAdapter = UserListAdapter(viewModel)
            viewDataBinding.userList.adapter = listAdapter
        }
    }
}