package tw.holidaybear.jetpack.exercise.data

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: Long,
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val imageUrl: String,
    @SerializedName("site_admin") val isAdmin: Boolean,
    @SerializedName("name") val name: String?,
    @SerializedName("blog") val blog: String?,
    @SerializedName("location") val location: String?,
    @SerializedName("bio") val bio: String?
)
