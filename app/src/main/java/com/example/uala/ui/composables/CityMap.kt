package com.example.uala.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker

@Composable
fun CityMap(
    modifier: Modifier = Modifier,
    cameraPositionState: CameraPositionState,
    selectedPosition: LatLng?
) {
    GoogleMap(modifier = modifier, cameraPositionState = cameraPositionState) {
        selectedPosition?.let { Marker(position = it) }
    }
}