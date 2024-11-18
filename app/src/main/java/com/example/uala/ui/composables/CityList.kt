package com.example.uala.ui.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uala.data.model.City

@Composable
fun CityList(
    modifier: Modifier = Modifier,
    cities: List<City>,
    favoriteCities: Set<City>,
    onCitySelected: (City) -> Unit,
    onToggleFavorite: (City, Boolean) -> Unit,
    onMoreInfo: (City) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(top = 8.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        items(cities.size) { index ->
            val city = cities[index]
            CityCard(
                city = city,
                isFavorite = city in favoriteCities,
                onToggleFavorite = { isFavorite -> onToggleFavorite(city, isFavorite) },
                onNavigateToMap = { onCitySelected(city) },
                onMoreInfo = { onMoreInfo(city) }
            )
        }
    }
}
