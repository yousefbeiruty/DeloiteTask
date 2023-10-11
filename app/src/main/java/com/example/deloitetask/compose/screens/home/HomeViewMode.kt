package com.example.deloitetask.compose.screens.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.model.movie.Movie
import com.example.domain.usecase.movie.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewMode   @Inject constructor(
    private val getMoviesUseCase: MovieUseCase
) : ViewModel() {
    private val _moviesState: MutableStateFlow<PagingData<Movie>> = MutableStateFlow(value = PagingData.empty())
    val moviesState: MutableStateFlow<PagingData<Movie>> get() = _moviesState

init {
    onEvent(HomeEvent.GetHome)
}

fun onEvent(event: HomeEvent) {
    viewModelScope.launch {
        when (event) {
            is HomeEvent.GetHome -> {
                getMovies()
            }
        }
    }
}

private suspend fun getMovies() {
    getMoviesUseCase.invoke(Unit)
        .distinctUntilChanged()
        .cachedIn(viewModelScope)
        .collect {
            _moviesState.value = it
        }
}
}

sealed class HomeEvent {
    object GetHome : HomeEvent()
}

data class HomeState(
    val movies: List<Movie> = listOf()
)