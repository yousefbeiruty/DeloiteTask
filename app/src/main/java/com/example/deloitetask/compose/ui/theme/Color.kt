package com.example.deloitetask.compose.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)
val Purpple=Color(530760)
val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


data class MyColors(
    val white: Color = Color.White,
    val black: Color = Color.Black,
    val Purple80:Color=Color(0xFFD0BCFF),
    val Purple40 :Color= Color(0xFF6650a4),
    val Purpple :Color=Color(0xFFC4A1CA)
)
val LocalColors= compositionLocalOf { MyColors() }
val MaterialTheme.myColors: MyColors
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current
