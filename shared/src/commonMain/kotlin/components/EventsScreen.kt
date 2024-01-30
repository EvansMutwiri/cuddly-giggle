package components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import model.EventsViewModel

@Composable
fun EventsScreen(viewModel: EventsViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    EventsList(uiState.events)
}