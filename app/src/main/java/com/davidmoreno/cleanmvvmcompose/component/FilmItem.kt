package com.davidmoreno.cleanmvvmcompose.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.davidmoreno.cleanmvvmcompose.FilmQuery
import com.davidmoreno.cleanmvvmcompose.domain.util.MOVIE_IMAGE
import kotlinx.coroutines.ExperimentalCoroutinesApi

/** AlbumList composable view */
@ExperimentalCoroutinesApi
@Composable
fun FilmItem(
    film: FilmQuery.Film,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
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
            Image(
                painter = painterResource(id = MOVIE_IMAGE),
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
                    text = film.title!!,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 2,
                    modifier = Modifier,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "Director: " + film.director,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))


            }
        }
    }
}