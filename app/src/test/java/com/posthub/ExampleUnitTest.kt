package com.posthub

import com.posthub.di.repositoryes.SupabaseRepositoryImpl
import com.posthub.supabase.models.Post
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

class ExampleUnitTest {
    /**
     * Test to verify that the total number of posts returned by `getPosts` is 17.
     */
    @Test
    fun getPosts_1() = runBlocking {
        val request = SupabaseRepositoryImpl().getPosts()
        val expected = 17
        val result = request.size
        assertEquals(expected, result)
    }

    /**
     * Test to verify that the ID of the first post returned by `getPosts` is "b29e0125-09ef-472d-8f8a-ea0a80badb70".
     */
    @Test
    fun getPosts_2() = runBlocking {
        val request = SupabaseRepositoryImpl().getPosts()
        val expected = "b29e0125-09ef-472d-8f8a-ea0a80badb70"
        val result = request[0].id
        assertEquals(expected, result)
    }

    /**
     * Test to verify that the author ID of the first post returned by `getPosts` is "d56a991b-8f3f-4806-baa8-c5150f3abb7c".
     */
    @Test
    fun getPosts_3() = runBlocking {
        val request = SupabaseRepositoryImpl().getPosts()
        val expected = "d56a991b-8f3f-4806-baa8-c5150f3abb7c"
        val result = request[0].author_id
        assertEquals(expected, result)
    }

    /**
     * Test to verify that the view count of the second post returned by `getPosts` is 5.
     */
    @Test
    fun getPosts_4() = runBlocking {
        val request = SupabaseRepositoryImpl().getPosts()
        val expected = 5
        val result = request[1].count_view
        assertEquals(expected, result)
    }

    /**
     * Test to verify the mocked behavior of `getPosts` method, returning a list with a single post.
     */
    @Test
    fun getPosts_mock_1() = runBlocking {
        val mockService = mockk<SupabaseRepositoryImpl>()
        coEvery { mockService.getPosts() } returns listOf(
            Post("1", null, null, "123", "temp", "temp", 12, null, null)
        )

        val result = mockService.getPosts()
        println(result)

        assertEquals(
            listOf(Post("1", null, null, "123", "temp", "temp", 12, null, null)),
            result
        )

        coVerify { mockService.getPosts() }
    }
}