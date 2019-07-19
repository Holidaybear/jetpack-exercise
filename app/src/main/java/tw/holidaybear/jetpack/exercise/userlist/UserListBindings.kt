package tw.holidaybear.jetpack.exercise.userlist

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.request.RequestOptions
import tw.holidaybear.jetpack.exercise.data.User

@BindingAdapter("items")
fun RecyclerView.setItems(items: List<User>) {
    (this.adapter as UserListAdapter).submitList(items)
}

@BindingAdapter("imageUrl")
fun ImageView.setImage(imageUrl: String) {
    Glide.with(this.context)
        .asBitmap()
        .load(imageUrl)
        .transition(BitmapTransitionOptions().crossFade(300))
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}