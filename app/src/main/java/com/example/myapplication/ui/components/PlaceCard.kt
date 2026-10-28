package com.example.myapplication.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.model.Place

@Composable
fun PlaceCard(place: Place, onOpenDetail: (Int) -> Unit) {
    Card(onClick = { onOpenDetail(place.id) }) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Image(
                painter = painterResource(id = place.imageId),
                contentDescription = place.name,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
            Text(place.name, style = MaterialTheme.typography.titleSmall)
            Text(place.location, style = MaterialTheme.typography.bodySmall)
        }
    }
}
