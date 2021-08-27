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
import tw.holidaybear.jetpack.exercise.data.GitHubService

@RunWith(JUnit4::class)
class GitHubServiceTest {

    @MockK
    lateinit var service: GitHubService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testUsersResponse() {
        runBlocking {
            coEvery { service.getUsers() } returns Response.success(testUsers)

            val apiResponse = service.getUsers()

            assertThat(apiResponse.isSuccessful)
            assertThat(apiResponse.body() != null)
            assertThat(apiResponse.body()?.size == 2)
        }
    }

    @Test
    fun testUserResponse() {
        runBlocking {
            coEvery { service.getUser(any()) } returns Response.success(testUser)

            val apiResponse = service.getUser("login")

            assertThat(apiResponse.isSuccessful)
            assertThat(apiResponse.body() != null)
            assertThat(apiResponse.body()?.login == "JakeWharton")
        }
    }
}
