package com.antsfamily.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.antsfamily.data.models.*
import com.antsfamily.data.remote.RemoteSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyInt
import org.mockito.junit.MockitoJUnitRunner
import java.util.Date

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DataRepositoryTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var remoteDataSource: RemoteSource

    @InjectMocks
    lateinit var repository: DataRepositoryImpl

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Test
    fun `get successfully posts`() = runTest {
        `when`(remoteDataSource.getPosts(anyInt())).thenReturn(MOCK_POSTS)
        val posts = repository.getPosts(1)
        assert(posts.size == 2)
    }
    @Test
    fun `get successfully post content`() = runTest {
        `when`(remoteDataSource.getPostContent(anyInt())).thenReturn(getMockPost(1))
        val content = repository.getPostContent(1)
        assert(content.id == 1)
    }

    companion object {
        private fun getMockPost(id: Int) = PostApiModel(
            id = id,
            date = MOCK_DATE,
            title = PostRenderedDataApiModel("mock_title"),
            content = PostRenderedDataApiModel("mock_content"),
            excerpt = PostRenderedDataApiModel("mock_excerpt"),
            headJson = YoastHeadJsonApiModel("mock image"),
        )

        private val MOCK_DATE = Date()
        private val MOCK_POSTS = listOf(
            getMockPost(1),
            getMockPost(2),
        )
    }
}
