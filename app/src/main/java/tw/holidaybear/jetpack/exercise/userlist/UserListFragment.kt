package tw.holidaybear.jetpack.exercise.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import tw.holidaybear.jetpack.exercise.R
import tw.holidaybear.jetpack.exercise.util.EventObserver
import tw.holidaybear.jetpack.exercise.util.getViewModelFactory

class UserListFragment : Fragment() {

    private val viewModel by viewModels<UserListViewModel> { getViewModelFactory() }

    private lateinit var listAdapter: UserListAdapter

    private lateinit var userList: RecyclerView

    private lateinit var progressbar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userList = view.findViewById(R.id.user_list)
        progressbar = view.findViewById(R.id.progress_bar)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupNavigation()

        viewModel.items.observe(this, Observer {
            listAdapter = UserListAdapter(it)
            userList.adapter = listAdapter
        })

        viewModel.isDataLoading.observe(this, Observer {
            progressbar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun setupNavigation() {
        viewModel.openUserEvent.observe(this, EventObserver{ argUserLogin ->
            val action = UserListFragmentDirections.nextAction(argUserLogin)
            findNavController().navigate(action)
        })
    }
}