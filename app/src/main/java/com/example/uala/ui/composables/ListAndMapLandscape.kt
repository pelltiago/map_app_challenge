package com.example.uala.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uala.data.model.City
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
@Composable
fun ListAndMapLandscape(
    cities: List<City>,
    favoriteCities: Set<City>,
    selectedCity: City?,
    isLoading: Boolean,
    searchQuery: String,
    onCitySelected: (City) -> Unit,
    onToggleFavorite: (City, Boolean) -> Unit,
    onMoreInfo: (City) -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onClearQuery: () -> Unit,
    onSearch: () -> Unit
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(
                selectedCity?.coord?.lat ?: 41.0, selectedCity?.coord?.lon ?: -71.0
            ), 10f
        )
    }

    LaunchedEffect(selectedCity) {
        selectedCity?.let {
            cameraPositionState.position = CameraPosition.fromLatLngZoom(
                LatLng(it.coord.lat, it.coord.lon), 10f
            )
        }
    }

    Row(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .weight(0.4f)
                .fillMaxHeight()
        ) {
            SimpleSearchBar(
                searchQuery = searchQuery,
                onClearQuery = onClearQuery,
                onSearch = onSearch,
                onQueryChanged = onSearchQueryChanged
            )

            if (isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                    Text(
                        text = "Cargando ciudades...",
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            } else if (cities.isEmpty()) {
                Text(
                    text = "No se encontraron ciudades.",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    items(cities.size) { index ->
                        val city = cities[index]
                        CityCard(
                            city = city,
                            isFavorite = favoriteCities.contains(city),
                            onToggleFavorite = { isFavorite -> onToggleFavorite(city, isFavorite) },
                            onNavigateToMap = { onCitySelected(city) },
                            onMoreInfo = { onMoreInfo(city) }
                        )
                    }
                }
            }
        }

        CityMap(
            modifier = Modifier
                .weight(0.6f)
                .fillMaxHeight(),
            cameraPositionState = cameraPositionState,
            selectedPosition = selectedCity?.let { LatLng(it.coord.lat, it.coord.lon) }
        )
    }
}

