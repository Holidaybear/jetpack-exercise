package tw.holidaybear.jetpack.exercise.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class User(
    @PrimaryKey @SerializedName("id") val id: Long,
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val imageUrl: String,
    @SerializedName("site_admin") val isAdmin: Boolean,
    @SerializedName("name") val name: String?,
    @SerializedName("blog") val blog: String?,
    @SerializedName("location") val location: String?,
    @SerializedName("bio") val bio: String?
)