package com.example.uala.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uala.data.model.City

@Composable
fun CityListScreen(
    isLoading: Boolean,
    cities: List<City>,
    favoriteCities: Set<City>,
    searchQuery: String,
    onCitySelected: (City) -> Unit,
    onClearQuery: () -> Unit,
    onSearch: () -> Unit,
    onQueryChanged: (String) -> Unit,
    onToggleFavorite: (City, Boolean) -> Unit,
    onMoreInfo: (City) -> Unit
) {
    Scaffold(topBar = {
        SimpleSearchBar(
            searchQuery = searchQuery,
            onClearQuery = onClearQuery,
            onSearch = onSearch,
            onQueryChanged = onQueryChanged
        )
    }) { innerPadding ->
        if (isLoading) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
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
            CityList(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                cities = cities,
                favoriteCities = favoriteCities,
                onCitySelected = onCitySelected,
                onToggleFavorite = onToggleFavorite,
                onMoreInfo = onMoreInfo
            )
        }
    }
}
