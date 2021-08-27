package tw.holidaybear.jetpack.exercise.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

class UserPagingSource(
    private val gitHubService: GitHubService
) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val position = params.key ?: 1
        return try {
            val users = gitHubService.getUsers(position, params.loadSize)
            LoadResult.Page(
                data = users,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (users.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition
    }
}