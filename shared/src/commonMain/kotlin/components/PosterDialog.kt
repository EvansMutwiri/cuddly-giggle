package components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun PosterDialog(poster: String, onDismissRequest: () -> Unit, showPoster: Boolean) {
    AnimatedVisibility(visible = showPoster, enter = scaleIn(), exit = scaleOut()) {
        Dialog(onDismissRequest = { onDismissRequest() }) {
            Box(
                modifier = Modifier.heightIn(min = 200.dp, max = 400.dp).fillMaxWidth(),
            ) {
                KamelImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),
                    resource = asyncPainterResource(poster),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                )
            }
        }
    }
}