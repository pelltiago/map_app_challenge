package com.example.uala.ui.composables

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.uala.data.model.City
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityMapScreen(city: City, onBack: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = { Text("Mapa de ${city.name}") }, navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver")
            }
        })
    }) { innerPadding ->
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(LatLng(city.coord.lat, city.coord.lon), 10f)
        }
        CityMap(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            cameraPositionState = cameraPositionState,
            selectedPosition = LatLng(city.coord.lat, city.coord.lon)
        )
    }
}