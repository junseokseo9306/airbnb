package com.example.airbnb.ui.home

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.Coil
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.airbnb.R

@Composable
fun AccommodationList(accommodationList: List<Accommodation>) {
    Column(
        Modifier.padding(
            start = 16.dp,
            end = 16.dp,
            bottom = 16.dp,
            top = 32.dp
        )
    ) {
        Text(
            text = "어디에서나, 여행은 \n살아보는거야!",
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(24.dp))

        LazyRow(
            horizontalArrangement =
            Arrangement.spacedBy(16.dp)
        ) {
            items(accommodationList) { accommodation ->
                AccommodationLayout(accommodation)
            }
        }
    }
}

@Composable
fun AccommodationLayout(accommodation: Accommodation) {

    Column {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(accommodation.imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_baseline_photo_size_select_actual_24),
            contentScale = ContentScale.Crop,
            contentDescription = "이미지",
            modifier = Modifier
                .size(
                    242.dp,
                    242.dp
                )
                .clip(RoundedCornerShape(10.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = accommodation.title,
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Preview
@Composable
fun test() {
    AccommodationLayout(accommodation = Accommodation("AA", "BB"))
}

@Preview
@Composable
fun test1() {
    val data = listOf(
        Accommodation("AA", "BB"),
        Accommodation("AA", "CC"),
        Accommodation("AA", "DD"),
        Accommodation("AA", "EE"),
        Accommodation("AA", "FF"),
        Accommodation("AA", "GG")
    )

    AccommodationList(data)
}