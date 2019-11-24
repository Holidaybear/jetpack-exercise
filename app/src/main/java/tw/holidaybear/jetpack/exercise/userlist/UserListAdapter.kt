package tw.holidaybear.jetpack.exercise.userlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import tw.holidaybear.jetpack.exercise.R
import tw.holidaybear.jetpack.exercise.data.User
import tw.holidaybear.jetpack.exercise.userlist.UserListAdapter.UserViewHolder

class UserListAdapter(private val items: List<User>) : ListAdapter<User, UserViewHolder>(
    UserDiffCallback()
) {

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
    }

    class UserViewHolder(private val containerView: View) : RecyclerView.ViewHolder(containerView) {

        fun bind(item: User) {
            containerView.findViewById<ImageView>(R.id.user_avatar).load(item.imageUrl) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
            containerView.findViewById<TextView>(R.id.user_login).text = item.login
            containerView.findViewById<TextView>(R.id.user_admin_badge).visibility = if (item.isAdmin) View.VISIBLE else View.GONE
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