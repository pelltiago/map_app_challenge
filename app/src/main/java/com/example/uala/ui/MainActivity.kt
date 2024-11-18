package com.example.uala.ui

import CityViewModel
import android.content.res.Configuration
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.uala.ui.composables.MainScreen

class MainActivity : ComponentActivity() {
    private val cityViewModel by viewModels<CityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(
                isPortrait = resources.configuration.orientation == ORIENTATION_PORTRAIT,
                selectedCity = cityViewModel.selectedCity.value,
                filteredCities = cityViewModel.filteredCities,
                isLoading = cityViewModel.isLoading,
                searchQuery = cityViewModel.searchQuery,
                onCitySelected = { cityViewModel.selectedCity.value = it },
                onSearchQueryChanged = { cityViewModel.updateSearchQuery(it) },
                onClearQuery = { cityViewModel.updateSearchQuery("") },
                onSearch = { cityViewModel.updateSearchQuery(cityViewModel.searchQuery) },
                favoriteCities = cityViewModel.favoriteCities,
                onMoreInfo = {},
                onToggleShowFavorites = { cityViewModel.toggleShowFavorites() },
                showFavoritesOnly = cityViewModel.showFavoritesOnly,
                onToggleFavorite = { city, isFavorite ->
                    cityViewModel.toggleFavorite(
                        city, isFavorite
                    )
                },
            )
        }
    }
}














