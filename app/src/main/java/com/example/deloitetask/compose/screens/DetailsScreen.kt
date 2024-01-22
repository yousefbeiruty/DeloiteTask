package com.example.deloitetask.compose.screens

import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.deloitetask.compose.sharViewModel.SharedViewModel
import com.example.deloitetask.utils.POSTER_BASE_URL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(sharedViewModel: SharedViewModel= hiltViewModel()) {
    val movie=sharedViewModel.getMovieDetails()
    LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                item {
                    MovieImage(POSTER_BASE_URL + movie?.posterPath)
                }
                item {
                    MovieTitle(movie.title.toString())
                }
                item {
                    MovieDetails(movie.overview.toString())
                }
                item {
                    RatingSection()
                }
            }
}

@Composable
fun MovieImage(url: String) {
    val painter: Painter = rememberAsyncImagePainter(url)
    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(shape = MaterialTheme.shapes.medium),
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun MovieTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(top = 16.dp)
    )
}

@Composable
fun MovieDetails(details: String) {
    Text(
        text = details,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(top = 8.dp)
    )
}

@Composable
fun RatingSection() {
   // var rating by remember { mutableStateOf(0) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Rate this movie:",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
        (1..5).forEach { index ->
            Icon(
                imageVector = //if (index <= rating) Icons.Default.Star else
                    Icons.Default.Star,
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                     //   rating = index
                        // Handle rating submission
                    }
                    .padding(4.dp)
                    .size(36.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsPagePreview() {
//    val sampleMovie = Movie(
//        title = "Inception",
//        imageResId = R.drawable.inception_poster,
//        details = "A thief who enters the dreams of others to steal their secrets."
//    )
//    DetailsScreen(movie = sampleMovie)
}
