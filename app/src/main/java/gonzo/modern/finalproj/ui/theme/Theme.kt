package gonzo.modern.finalproj.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat


private val DarkColorScheme = darkColorScheme(
    primary = PresentGreen,
    primaryContainer = PresentGreenContainer,
    onPrimary = OnPresentGreen,
    onPrimaryContainer = OnPresentGreenContainer,
    error = Color(0xFFFF5252),
    errorContainer = Color(0xFF4F1919),
    surface = Color(0xFF1A1A1A),
    surfaceVariant = Color(0xFF2D2D2D),
    background = Color(0xFF121212),
    onSurface = Color.White,
    onSurfaceVariant = Color(0xFFBBBBBB),
    onError = Color.White,
    onErrorContainer = Color(0xFFFF8A8A)
)

@Composable
fun FinalProjTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme
    val view = LocalView.current
    
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            
            // Enable edge-to-edge experience
            WindowCompat.setDecorFitsSystemWindows(window, false)
            
            // Update the system bars to be transparent
            val insetsController = WindowCompat.getInsetsController(window, view)
            insetsController.apply {
                isAppearanceLightStatusBars = false
                isAppearanceLightNavigationBars = false
                systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}