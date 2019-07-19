package tw.holidaybear.jetpack.exercise.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tw.holidaybear.jetpack.exercise.data.User
import tw.holidaybear.jetpack.exercise.userlist.UserListAdapter.UserViewHolder
import tw.holidaybear.jetpack.exercise.databinding.ItemUserBinding

class UserListAdapter(private val viewModel: UserListViewModel) : ListAdapter<User, UserViewHolder>(
    UserDiffCallback()
) {

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModel, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.from(parent)
    }

    class UserViewHolder private constructor(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: UserListViewModel, item: User) {
            binding.user = item
            binding.viewmodel = viewModel
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): UserViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemUserBinding.inflate(layoutInflater, parent, false)

                return UserViewHolder(binding)
            }
        }
    }
}

class UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}