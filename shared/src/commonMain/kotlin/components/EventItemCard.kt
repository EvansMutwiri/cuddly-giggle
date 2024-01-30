package components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import model.EventItem

@Composable
fun EventItemCard(eventItem: EventItem) {

    var showPoster by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clickable(onClick = {
                showPoster = true
            })
            .heightIn(min = 200.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = 0.dp
    ) {
        Column {
            KamelImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp),
                onLoading = { progress ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .align(Alignment.Center)
                    ) {
                        CircularProgressIndicator(progress = progress)
                    }
                },
                resource = asyncPainterResource(eventItem.src),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )

            val desc = eventItem.description.replace("\n", "").replace("\t", "")
            Text(text = desc, modifier = Modifier.padding(5.dp).align(Alignment.CenterHorizontally))
        }
    }

    PosterDialog(
        poster = eventItem.src,
        onDismissRequest = { showPoster = false },
        showPoster = showPoster
    )
}