package com.example.domain.usecase.movie

import androidx.paging.PagingData
import com.example.domain.model.movie.Movie
import com.example.domain.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito.mock

class MovieUseCaseTest{

    private lateinit var movieUseCase:MovieUseCase

    @Before
    fun setUp(){
        movieUseCase= MovieUseCase()
    }

    @Test
    fun `invoke should return Flow of PagingData`() = runBlocking {
        // Given
        val mockPagingData: PagingData<Movie> = mock() // Mock PagingData<Movie>
        whenever(mockRepository.getMovies()).thenReturn(flowOf(mockPagingData)) // Stub repository method

        // When
        val result: Flow<PagingData<Movie>> = movieUseCase.invoke()

        // Then
        assertEquals(mockPagingData, result.single()) // Verify that the result is the same as the mocked PagingData<Movie>
    }
}