import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.uala.data.model.City
import com.example.uala.domain.GetCitiesUseCase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CityViewModel(application: Application) : AndroidViewModel(application) {
    var searchQuery by mutableStateOf("")
        private set

    private var _filteredCities by mutableStateOf(emptyList<City>())
    val filteredCities: List<City> get() = _filteredCities

    private var _isLoading by mutableStateOf(true)
    val isLoading: Boolean get() = _isLoading

    var selectedCity = mutableStateOf<City?>(null)
        private set

    private var citiesList: List<City> = emptyList()
    private val getCitiesUseCase = GetCitiesUseCase()

    private val sharedPreferences =
        application.getSharedPreferences("favorite_cities_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    private val _favoriteCities = mutableStateOf(setOf<City>())
    val favoriteCities: Set<City> get() = _favoriteCities.value

    var showFavoritesOnly by mutableStateOf(false)
        private set

    init {
        getCities()
        loadFavorites()
    }

    fun toggleFavorite(city: City, isFavorite: Boolean) {
        viewModelScope.launch(IO) {
            val updatedFavorites = if (isFavorite) {
                _favoriteCities.value + city
            } else {
                _favoriteCities.value - city
            }
            _favoriteCities.value = updatedFavorites
            saveFavorites(updatedFavorites)
            filterCities()
        }
    }

    fun toggleShowFavorites() {
        showFavoritesOnly = !showFavoritesOnly
        filterCities()
    }

    private fun saveFavorites(favorites: Set<City>) {
        val serializedFavorites = gson.toJson(favorites)
        sharedPreferences.edit().putString("favorites", serializedFavorites).apply()
    }

    private fun loadFavorites() {
        viewModelScope.launch(IO) {
            val serializedFavorites = sharedPreferences.getString("favorites", null)
            if (!serializedFavorites.isNullOrEmpty()) {
                val type = object : TypeToken<Set<City>>() {}.type
                val favoriteCities: Set<City> = gson.fromJson(serializedFavorites, type)

                withContext(Dispatchers.Main) {
                    _favoriteCities.value = favoriteCities
                }
            }
        }
    }


    private fun getCities() {
        if (citiesList.isNotEmpty()) return
        viewModelScope.launch(IO) {
            withContext(Dispatchers.Main) {
                _isLoading = true
            }
            val cities = getCitiesUseCase().sortedBy { it.name.lowercase() }
            citiesList = cities
            filterCities()
            withContext(Dispatchers.Main) {
                _isLoading = false
            }
        }
    }


    fun updateSearchQuery(query: String) {
        searchQuery = query
        filterCities()
    }

    private fun filterCities() {
        viewModelScope.launch(IO) {
            val filtered = if (showFavoritesOnly) {
                favoriteCities.filter { it.name.startsWith(searchQuery, ignoreCase = true) }
            } else {
                citiesList.filter { it.name.startsWith(searchQuery, ignoreCase = true) }
            }.sortedBy { it.name.lowercase() }

            withContext(Dispatchers.Main) {
                _filteredCities = filtered
            }
        }
    }
}
