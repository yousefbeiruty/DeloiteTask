package com.example.deloitetask.compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.deloitetask.compose.bottomnav.BottomNavigation
import com.example.deloitetask.compose.nav_graph.NavigationGraph
import com.example.deloitetask.compose.sharViewModel.SharedViewModel
import com.example.deloitetask.compose.ui.theme.DeloiteTaskTheme
import com.example.deloitetask.compose.ui.theme.myColors
import com.example.deloitetask.compose.ui.theme.myTypography
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposeMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeloiteTaskTheme(darkTheme = isSystemInDarkTheme()) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   MainScreenView()
                }
            }
        }
    }
}




@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreenView(sharedViewModel: SharedViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    androidx.compose.material.Scaffold(topBar = {
        TopAppBarSample(
            navController,
            sharedViewModel.flag
        )
    }, bottomBar = {
        if (sharedViewModel.flag) BottomNavigation(navController = navController)
    }) {
        NavigationGraph(navController = navController)
    }
}

@Composable
fun TopAppBarSample(navController: NavHostController, flag: Boolean) {
    Column {
        androidx.compose.material.TopAppBar(elevation = 4.dp, title = {
            Text(
                "I'm a TopAppBar", style =
                //TextStyle(
//                    fontSize = 17.sp,
//                    fontStyle = FontStyle.Normal,
//                    fontWeight = FontWeight.Bold,
//                    color = if (isSystemInDarkTheme()) MaterialTheme.myColors.white
//                    else Color.Black
//
                MaterialTheme.myTypography.bigText
                //  )
            )
        }, backgroundColor = MaterialTheme.myColors.Purpple, navigationIcon = {
            androidx.compose.material.IconButton(onClick = { navController.popBackStack() }) {
                if (!flag) androidx.compose.material.Icon(
                    Icons.Filled.ArrowBack, null,
                    tint = Color.White
                )
            }
        }, actions = {
            androidx.compose.material.IconButton(onClick = {/* Do Something*/ }) {
                androidx.compose.material.Icon(Icons.Filled.Share, null)
            }
            androidx.compose.material.IconButton(onClick = {/* Do Something*/ }) {
                androidx.compose.material.Icon(Icons.Filled.Settings, null)
            }
        })
    }
}




@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DeloiteTaskTheme {
        Greeting("Android")
    }
}