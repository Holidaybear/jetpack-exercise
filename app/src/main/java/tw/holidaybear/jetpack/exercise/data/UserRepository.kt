package tw.holidaybear.jetpack.exercise.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import tw.holidaybear.jetpack.exercise.defaultUser
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val gitHubService: GitHubService
) {

    fun getUsers(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UserPagingSource(gitHubService) }
        ).flow
    }

    suspend fun getUser(login: String): User {
        return withContext(Dispatchers.IO) {
            return@withContext gitHubService.getUser(login).body() ?: defaultUser
        }
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}
