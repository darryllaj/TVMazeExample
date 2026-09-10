package com.example.tvmazeexample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.tvmazeexample.Response.TvShowResponse
import androidx.core.text.HtmlCompat
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
@Composable
private fun DetailInfo(
    title: String,
    value: String
) {
    Column(
        modifier = Modifier.width(100.dp)
    ) {
        Text(
            text = title,
            color = Color.Black,
            style = MaterialTheme.typography.labelMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = value,
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun DetailScreen(
    show: TvShowResponse,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(
                colors = listOf(
                    Color.Transparent,
                    Color.Transparent,
                    Color(0xFF355C7D)
                )
            ))
            .verticalScroll(rememberScrollState())
    ) {

        // =========================
        // HERO SECTION
        // =========================

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp)
        ) {

            AsyncImage(
                model = show.image?.original,
                contentDescription = show.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Gradient agar poster menyatu dengan background
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Transparent,
                                Color(0xFF355C7D)
                            )
                        )
                    )
            )

            // Back Button
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .padding(16.dp)
                    .size(45.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color.Black.copy(alpha = 0.55f))
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            IconButton(
                onClick = onShareClick,
                modifier = Modifier
                    .padding(16.dp)
                    .size(45.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color.Black.copy(alpha = 0.55f))
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    tint = Color.White
                )
            }
        }

        // =========================
        // CONTENT
        // =========================

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {

            // =========================
            // TITLE
            // =========================

            Text(
                text = show.name ?: "Unknown",
                color = Color.Black,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            // =========================
            // RATING
            // =========================

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(25.dp)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "${show.rating?.average ?: "-"} / 10",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // =========================
            // INFO
            // =========================

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                DetailInfo(
                    title = "Status",
                    value = show.status ?: "-"
                )

                DetailInfo(
                    title = "Runtime",
                    value = "${show.runtime ?: "-"} min"
                )

                DetailInfo(
                    title = "Language",
                    value = show.language ?: "-"
                )
            }

            Spacer(modifier = Modifier.height(22.dp))

            // =========================
            // GENRE
            // =========================

            Text(
                text = "Genres",
                color = Color.Black,
                style = MaterialTheme.typography.titleMedium,

            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                show.genres?.forEach { genre ->

                    AssistChip(
                        onClick = {},
                        label = {
                            Text(
                                text = genre,
                                color = Color.White
                            )
                        },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = Color(0xFF163A63),
                            labelColor = Color.White
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // =========================
            // DESCRIPTION
            // =========================

            Text(
                text = "Description",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = cleanHtml(show.summary),
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                lineHeight = MaterialTheme.typography.bodyLarge.lineHeight
            )

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}


// =========================
// CLEAN HTML
// =========================

private fun cleanHtml(html: String?): String {

    if (html.isNullOrBlank()) {
        return "Summary tidak tersedia."
    }

    return HtmlCompat
        .fromHtml(
            html,
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
        .toString()
        .trim()
}