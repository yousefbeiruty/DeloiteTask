package com.example.deloitetask.compose.sharViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.domain.model.home.MostPopular
import com.example.domain.model.movie.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor():ViewModel() {
     private var _movieDetails: Movie= Movie()
    var flag by mutableStateOf<Boolean>(true)
        private set
    fun visibleBottom(visible: Boolean){
        flag=visible
    }

    fun setMovieDetails(movie: Movie){
        _movieDetails=movie
    }

    fun getMovieDetails():Movie{
       return _movieDetails
    }

}