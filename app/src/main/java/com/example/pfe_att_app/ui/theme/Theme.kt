package com.example.pfe_att_app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    /*
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200

     */
     primary = darkRed,
    primaryVariant = lightRed,
    secondary = errorColor

)

private val LightColorPalette = lightColors(
   /* primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200
*/
            primary = darkRed,
    primaryVariant = lightRed,
    secondary = errorColor

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun Pfe_att_appTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}