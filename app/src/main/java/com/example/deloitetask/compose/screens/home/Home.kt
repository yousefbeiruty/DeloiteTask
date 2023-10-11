package com.example.deloitetask.compose.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.deloitetask.R
import com.example.deloitetask.utils.ErrorMessage
import com.example.deloitetask.utils.LoadingNextPageItem
import com.example.deloitetask.utils.POSTER_BASE_URL
import com.example.deloitetask.utils.PageLoader
import com.example.domain.model.movie.Movie
import kotlin.math.roundToInt

@Composable
fun HomeScreen(navController: NavController,
               viewModel: HomeViewMode = hiltViewModel()
) {
    val moviePagingItems: LazyPagingItems<Movie> = viewModel.moviesState.collectAsLazyPagingItems()
    listofMovies(movies = moviePagingItems,"")
//        LazyColumn(
//
//        ) {
//            item { Spacer(modifier = Modifier.padding(4.dp)) }
//            items(moviePagingItems.itemCount) { index ->
//                ItemMovie(
//                    itemEntity = moviePagingItems[index]!!,
//                    onClick = {
//                       // navController.navigate(AppScreen.DetailsScreen.route)
//                    }
//                )
//            }
//            moviePagingItems.apply {
//                when {
//                    loadState.refresh is LoadState.Loading -> {
//                        item { PageLoader(modifier = Modifier.fillParentMaxSize()) }
//                    }
//
//                    loadState.refresh is LoadState.Error -> {
//                        val error = moviePagingItems.loadState.refresh as LoadState.Error
//                        item {
//                            ErrorMessage(
//                                modifier = Modifier.fillParentMaxSize(),
//                                message = error.error.localizedMessage!!,
//                                onClickRetry = { retry() })
//                        }
//                    }
//
//                    loadState.append is LoadState.Loading -> {
//                        item { LoadingNextPageItem(modifier = Modifier) }
//                    }
//
//                    loadState.append is LoadState.Error -> {
//                        val error = moviePagingItems.loadState.append as LoadState.Error
//                        item {
//                            ErrorMessage(
//                                modifier = Modifier,
//                                message = error.error.localizedMessage!!,
//                                onClickRetry = { retry() })
//                        }
//                    }
//                }
//            }
//            item { Spacer(modifier = Modifier.padding(4.dp)) }
//        }
}

@Composable
private fun listofMovies(
    movies: LazyPagingItems<Movie>,
    searchText: String
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 1.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        item { Spacer(modifier = Modifier.padding(4.dp)) }
        items(movies.itemSnapshotList.filter {
            it?.originalTitle?.startsWith(searchText, ignoreCase = true) == true
        }.count()) { index ->
            Box(contentAlignment = Alignment.Center) {
                CardDemo(movies[index])
            }
        }

        movies.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { PageLoader(modifier = Modifier.fillParentMaxSize()) }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = movies.loadState.refresh as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier.fillParentMaxSize(),
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item { LoadingNextPageItem(modifier = Modifier) }
                }

                loadState.append is LoadState.Error -> {
                    val error = movies.loadState.append as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier,
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }
            }
            item { Spacer(modifier = Modifier.padding(4.dp)) }
        }
    }
}
@Composable
fun CardDemo(obj: Movie?) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(

        ) {
            NetworkImage(
                contentDescription = "",
                url = POSTER_BASE_URL+obj?.posterPath,
                width = 150,
                height = 150
            )
            TextWithEllipsis(obj?.originalTitle.toString(),15)
        }
    }
}

@Composable
fun TextWithEllipsis(text: String, maxLength: Int) {
    var truncatedText by remember { mutableStateOf(text) }

    if (text.length > maxLength) {
        truncatedText = text.substring(0, maxLength - 2) + ".."
    }
    Text(
        buildAnnotatedString {
        append("$truncatedText  ")
        withStyle(
            style = SpanStyle(fontWeight = FontWeight.W900, color = Color(0xFF4552B8))
        ) {
            //     append("Jetpack Compose Playground")
        }
    }, maxLines = 1,
        fontSize = 15.sp,
        modifier = Modifier.padding(5.dp)
    )
}

@Composable
fun NetworkImage(url: String, contentDescription: String?, width: Int, height: Int) {
    val painter: Painter = rememberAsyncImagePainter(url)
    var visible by remember { mutableStateOf(true) }
    val density = LocalDensity.current

    val scrollState = rememberScrollState()
// Access the current scroll position using scrollState.value
    val currentScrollPosition = scrollState.value
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically {
            // Slide in from 40 dp from the top.
            with(density) { -40.dp.roundToPx() }
        } + expandVertically(
            // Expand from the top.
            expandFrom = Alignment.Top
        ) + fadeIn(
            // Fade in with the initial alpha of 0.3f.
            initialAlpha = 0.3f
        ),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = url).apply(block = fun ImageRequest.Builder.() {
                    placeholder(R.drawable.ic_movie)
                }).build()
            ),
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(width.dp)
                .height(height.dp).offset {
                    IntOffset(
                        x = (width * scrollState.value / (scrollState.maxValue - scrollState.value) * 0.5f).roundToInt(),
                        y = 0
                    )
                },
        )
    }
}



@Composable
fun ItemMovie(
    itemEntity: Movie,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        val painter = rememberAsyncImagePainter(POSTER_BASE_URL+ itemEntity.backdropPath)
        val transition by animateFloatAsState(
            targetValue = if (painter.state is AsyncImagePainter.State.Success) 1f else 0f,
            label = ""
        )
        Column {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                        .alpha(transition)
                )
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .align(Alignment.TopEnd),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = null,
                        tint = Color.Yellow
                    )

                    Text(
                        text = itemEntity.voteAverage.toString() + "/10",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Yellow,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
            }

            Text(
                text = itemEntity.title,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.padding(vertical = 4.dp))

            Text(
                text = itemEntity.overview,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 8.dp),
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}