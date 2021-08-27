package tw.holidaybear.jetpack.exercise.userlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import tw.holidaybear.jetpack.exercise.R
import tw.holidaybear.jetpack.exercise.data.User
import tw.holidaybear.jetpack.exercise.databinding.ItemUserBinding

class UserViewHolder(
    itemView: View,
    onItemClicked: (User) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemUserBinding.bind(itemView)

    private var user: User? = null

    init {
        itemView.setOnClickListener {
            user?.let { onItemClicked(it) }
        }
    }

    fun bind(user: User?) {
        if (user != null) {
            showData(user)
        }
    }

    private fun showData(user: User) {
        this.user = user
        binding.userAvatar.load(user.imageUrl) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
        binding.userLogin.text = user.login
        binding.userAdminBadge.visibility = if (user.isAdmin) View.VISIBLE else View.GONE
    }

    companion object {
        fun create(parent: ViewGroup, onItemClicked: (User) -> Unit): UserViewHolder {
            val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_user, parent, false)
            return UserViewHolder(view, onItemClicked)
        }
    }
}
