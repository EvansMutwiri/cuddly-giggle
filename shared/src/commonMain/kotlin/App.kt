
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import components.EventsScreen
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import model.EventsViewModel

@Composable
fun MyAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = MaterialTheme.colors.copy(
            surface = Color.DarkGray.copy(alpha = 0.5f),
            onSurface = Color.LightGray,
        )
    ) {
        content()
    }
}

@Composable
fun App() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        MyAppTheme {
            val viewModel : EventsViewModel = getViewModel(Unit, factory = viewModelFactory { EventsViewModel() })
            EventsScreen(viewModel)
        }
    }
}
