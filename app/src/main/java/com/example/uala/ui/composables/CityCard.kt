package com.example.uala.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.uala.data.model.City

@Composable
fun CityCard(
    city: City,
    isFavorite: Boolean,
    onToggleFavorite: (Boolean) -> Unit,
    onNavigateToMap: () -> Unit,
    onMoreInfo: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onNavigateToMap() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(text = "${city.name}, ${city.country}", fontWeight = FontWeight.Bold)
                    Text(text = "Lat: ${city.coord.lat}, Lon: ${city.coord.lon}")
                }
                IconButton(onClick = { onToggleFavorite(!isFavorite) }) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = null
                    )
                }
            }
            Button(onClick = onMoreInfo, modifier = Modifier.align(Alignment.End)) {
                Text(text = "More Info")
            }
        }
    }
}
