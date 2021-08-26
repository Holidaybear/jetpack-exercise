package tw.holidaybear.jetpack.exercise.userlist

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load

import coil.transform.CircleCropTransformation
import tw.holidaybear.jetpack.exercise.data.User

@BindingAdapter("items")
fun RecyclerView.setItems(items: List<User>) {
    (this.adapter as UserListAdapter).submitList(items)
}

@BindingAdapter("imageUrl")
fun ImageView.setImage(imageUrl: String?) {
    load(imageUrl) {
        crossfade(true)
        transformations(CircleCropTransformation())
    }
}