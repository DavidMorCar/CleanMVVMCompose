package com.davidmoreno.cleanmvvmcompose.component

import android.content.Context
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.davidmoreno.cleanmvvmcompose.domain.model.response.Album
import com.davidmoreno.cleanmvvmcompose.domain.util.DEFAULT_IMAGE
import com.davidmoreno.cleanmvvmcompose.domain.util.loadPicture
import kotlinx.coroutines.ExperimentalCoroutinesApi

/** AlbumItem composable view */
@ExperimentalCoroutinesApi
@Composable
fun AlbumItem(
    album: Album,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    context: Context,
    cutCornerSize: Dp = 30.dp,
) {
    Box(
        modifier = modifier
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val clipPath = Path().apply {
                lineTo(size.width, 0f)
                lineTo(size.width, cutCornerSize.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }

            clipPath(clipPath) {
                drawRoundRect(
                    color = Color(0x446BB86FC),
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )

            }
        }
        Row(
            modifier = Modifier
                .padding(16.dp),
            Arrangement.Center,
            Alignment.CenterVertically
        ) {
            album.imageList[1].url.let { url ->
                val image = loadPicture(
                    url = url,
                    defaultImage = DEFAULT_IMAGE,
                    context = context
                ).value
                image?.let { img ->
                    Image(
                        bitmap = img.asImageBitmap(),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(5.dp)
                            .widthIn(max = 150.dp, min = 70.dp)
                            .heightIn(max = 150.dp, min = 70.dp),
                        contentScale = ContentScale.FillWidth,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(
                        modifier = Modifier
                            .weight(2f)
                    ) {
                        Text(
                            text = album.title.label,
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSurface,
                            maxLines = 5,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = album.price.label,
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.onSurface,
                            maxLines = 10,
                            modifier = Modifier,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}