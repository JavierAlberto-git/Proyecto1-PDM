package com.example.practica3room.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Esquema de colores personalizado para tema claro
private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = BackgroundCream,
    primaryContainer = PrimaryBlue,
    onPrimaryContainer = BackgroundCream,
    secondary = PrimaryBlue,
    onSecondary = BackgroundCream,
    tertiary = PrimaryBlue,
    background = BackgroundCream,
    onBackground = PrimaryBlue,
    surface = Color.White,
    onSurface = PrimaryBlue,
    surfaceVariant = BackgroundCream,
    onSurfaceVariant = PrimaryBlue
)

// Esquema de colores personalizado para tema oscuro (usa tus colores también)
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryBlue,
    onPrimary = BackgroundCream,
    primaryContainer = PrimaryBlue,
    onPrimaryContainer = BackgroundCream,
    secondary = PrimaryBlue,
    onSecondary = BackgroundCream,
    tertiary = PrimaryBlue,
    background = Color(0xFF1C1C1C),
    onBackground = BackgroundCream,
    surface = Color(0xFF2C2C2C),
    onSurface = BackgroundCream,
    surfaceVariant = Color(0xFF3C3C3C),
    onSurfaceVariant = BackgroundCream
)

@Composable
fun Practica3RoomTheme(
    darkTheme: Boolean = false, // Forzar siempre tema claro
    dynamicColor: Boolean = false, // Desactivar colores dinámicos de Android 12+
    content: @Composable () -> Unit
) {
    // Siempre usar el esquema de colores claro con tu paleta personalizada
    val colorScheme = LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Barra de estado con tu color primario
            window.statusBarColor = PrimaryBlue.toArgb()
            // Iconos de la barra de estado en color claro
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}