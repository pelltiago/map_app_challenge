package com.example.uala.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uala.data.model.City

@Composable
fun MainScreen(
    isPortrait: Boolean,
    selectedCity: City?,
    filteredCities: List<City>,
    favoriteCities: Set<City>,
    isLoading: Boolean,
    searchQuery: String,
    onCitySelected: (City?) -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onClearQuery: () -> Unit,
    onSearch: () -> Unit,
    onToggleFavorite: (City, Boolean) -> Unit,
    onMoreInfo: (City) -> Unit,
    onToggleShowFavorites: () -> Unit,
    showFavoritesOnly: Boolean,
) {
    Scaffold(
        floatingActionButton = {
            if (selectedCity == null || !isPortrait) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 30.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    FloatingActionButton(onClick = onToggleShowFavorites) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {
                            Icon(
                                imageVector = if (showFavoritesOnly) Icons.Filled.List else Icons.Filled.Favorite,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (showFavoritesOnly) "Lista" else "Favoritos"
                            )
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        Row(
            modifier = Modifier.padding(paddingValues)
        ) {
            if (isPortrait) {
                if (selectedCity == null) {
                    CityListScreen(
                        cities = filteredCities,
                        favoriteCities = favoriteCities,
                        isLoading = isLoading,
                        searchQuery = searchQuery,
                        onCitySelected = onCitySelected,
                        onQueryChanged = onSearchQueryChanged,
                        onClearQuery = onClearQuery,
                        onSearch = onSearch,
                        onToggleFavorite = onToggleFavorite,
                        onMoreInfo = onMoreInfo
                    )
                } else {
                    CityMapScreen(city = selectedCity, onBack = { onCitySelected(null) })
                }
            } else {
                ListAndMapLandscape(
                    cities = filteredCities,
                    favoriteCities = favoriteCities,
                    isLoading = isLoading,
                    selectedCity = selectedCity,
                    searchQuery = searchQuery,
                    onCitySelected = onCitySelected,
                    onSearchQueryChanged = onSearchQueryChanged,
                    onClearQuery = onClearQuery,
                    onSearch = onSearch,
                    onToggleFavorite = onToggleFavorite,
                    onMoreInfo = onMoreInfo
                )
            }
        }
    }
}
