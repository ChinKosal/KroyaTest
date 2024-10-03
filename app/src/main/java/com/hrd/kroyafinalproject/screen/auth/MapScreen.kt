package com.hrd.kroyafinalproject.screen.auth

import android.location.Geocoder
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapApp() {
    val locationPermissionState = rememberPermissionState(
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )
    LaunchedEffect(Unit) {
        locationPermissionState.launchPermissionRequest()
    }

    if (locationPermissionState.status.isGranted) {
        // Show the map once permission is granted
        MapWithMarkerAndAddress()
    } else {
        // Show a message to the user
        Text("Location permission is required to display the map.")
    }
}

@Composable
fun MapWithMarkerAndAddress() {
    val context = LocalContext.current
    val cameraPositionState = rememberCameraPositionState()
    var markerPosition by remember { mutableStateOf<LatLng?>(null) }
    var address by remember { mutableStateOf<String>("") }
    val coroutineScope = rememberCoroutineScope()
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    // Cambodia's LatLng bounds (Southwest and Northeast corners)
    val cambodiaBounds = LatLngBounds(
        LatLng(10.486543, 102.348099), // Southwest corner of Cambodia
        LatLng(14.686965, 107.610809)  // Northeast corner of Cambodia
    )
    val defaultLocation = LatLng(12.5657, 104.9910) // Center of Cambodia (for fallback)

    // Fetch current location and update camera position
    LaunchedEffect(Unit) {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                val userLatLng = LatLng(it.latitude, it.longitude)
                if (cambodiaBounds.contains(userLatLng)) {
                    coroutineScope.launch {
                        cameraPositionState.animate(CameraUpdateFactory.newLatLngZoom(userLatLng, 10f))
                    }
                } else {
                    // Move camera to Cambodia's default center if user's location is outside Cambodia
                    coroutineScope.launch {
                        cameraPositionState.animate(CameraUpdateFactory.newLatLngZoom(defaultLocation, 6f))
                    }
                }
            } ?: run {
                // Fallback to Cambodia's center if location is unavailable
                coroutineScope.launch {
                    cameraPositionState.animate(CameraUpdateFactory.newLatLngZoom(defaultLocation, 6f))
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxWidth(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                isMyLocationEnabled = true,
                latLngBoundsForCameraTarget = cambodiaBounds // Restrict camera within Cambodia
            ),
            uiSettings = MapUiSettings(
                myLocationButtonEnabled = true,
                zoomControlsEnabled = true
            ),
            onMapClick = { latLng ->
                // Only handle clicks within Cambodia
                if (cambodiaBounds.contains(latLng)) {
                    markerPosition = latLng

                    // Perform reverse geocoding to get address
                    coroutineScope.launch(Dispatchers.IO) {
                        val geoCoder = Geocoder(context, Locale.getDefault())
                        try {
                            val addresses = geoCoder.getFromLocation(
                                latLng.latitude, latLng.longitude, 1
                            )
                            if (addresses != null && addresses.isNotEmpty()) {
                                address = addresses[0].getAddressLine(0) ?: "Address not found"
                            } else {
                                address = "Address not found"
                            }
                        } catch (e: Exception) {
                            address = "Error getting address"
                        }
                    }
                }
            }
        ) {
            // Show marker if a position is selected
            markerPosition?.let { position ->
                Marker(
                    state = MarkerState(position = position),
                    title = "Selected Location",
                    onClick = {
                        markerPosition = null
                        true
                    }
                )
            }
        }

        // Back Button to handle navigation
        IconButton(
            onClick = { /* Handle back navigation */ },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
                .background(Color.White.copy(alpha = 0.8f), shape = CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Back",
                tint = Color.Black
            )
        }

        // Bottom info bar with selected location
        BottomAppBar(
            modifier = Modifier.align(Alignment.BottomCenter).height(400.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "Select your location",
                    fontSize = 22.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.size(8.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(imageVector = Icons.Default.Place, contentDescription = "Place", tint = Color.Green)
                    if (markerPosition != null) {
                        LimitedText(text = "$markerPosition")
                    } else {
                        LimitedText(text = "No location selected")
                    }
                }
                Text(text = "Address Detail")
            }
        }
    }
}



@Preview
@Composable
fun MapAppPreview(){
    MapApp()
}