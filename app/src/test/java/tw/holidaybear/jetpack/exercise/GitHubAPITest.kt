package tw.holidaybear.jetpack.exercise

import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response
import tw.holidaybear.jetpack.exercise.data.remote.GitHubAPI

@RunWith(JUnit4::class)
class GitHubAPITest {

    @MockK
    lateinit var api: GitHubAPI

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testUsersResponse() {
        runBlocking {
            coEvery { api.getUsers(any()) } returns Response.success(testUsers)

            val apiResponse = api.getUsers("token")

            assertThat(apiResponse.isSuccessful)
            assertThat(apiResponse.body() != null)
            assertThat(apiResponse.body()?.size == 2)
        }
    }

    @Test
    fun testUserResponse() {
        runBlocking {
            coEvery { api.getUser(any(), any()) } returns Response.success(testUser)

            val apiResponse = api.getUser("token", "login")

            assertThat(apiResponse.isSuccessful)
            assertThat(apiResponse.body() != null)
            assertThat(apiResponse.body()?.login == "JakeWharton")
        }
    }
}