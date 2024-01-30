package components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.EventItem

@Composable
fun EventsList(eventItems: List<EventItem>) {
    AnimatedVisibility(visible = eventItems.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier.padding(5.dp)
        ) {
            items(count = eventItems.size) { index ->
                EventItemCard(eventItems[index])
            }
        }
    }
}