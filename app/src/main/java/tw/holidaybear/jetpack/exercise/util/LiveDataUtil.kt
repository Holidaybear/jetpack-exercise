package tw.holidaybear.jetpack.exercise.util

import androidx.compose.effectOf
import androidx.compose.memo
import androidx.compose.onCommit
import androidx.compose.state
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * General purpose observe effect. this will likely be provided by LiveData. effect API for compose will also simplify soon.
 */
fun <T> observe(data: LiveData<T>) = effectOf<T?> {
    val result = +state { data.value }
    val observer = +memo { Observer<T> { result.value = it } }

    +onCommit(data) {
        data.observeForever(observer)
        onDispose { data.removeObserver(observer) }
    }

    result.value
}
