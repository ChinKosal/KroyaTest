package com.hrd.kroyafinalproject.screen.auth

import android.content.Context
import android.location.Geocoder
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.contentValuesOf
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.hrd.kroyafinalproject.R
import com.hrd.kroyafinalproject.Routes
import com.hrd.kroyafinalproject.ui.theme.InterMedium
import com.hrd.kroyafinalproject.ui.theme.InterReqular
import com.hrd.kroyafinalproject.ui.theme.InterSemiBold
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapApp(navController: NavController) {
    val locationPermissionState = rememberPermissionState(
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )
    LaunchedEffect(Unit) {
        locationPermissionState.launchPermissionRequest()
    }

    if (locationPermissionState.status.isGranted) {
        // Show the map once permission is granted
        MapWithMarkerAndAddress(navController =  navController)
    } else {
        // Show a message to the user
        Text("Location permission is required to display the map.")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapWithMarkerAndAddress(navController: NavController) {
    val context = LocalContext.current
    val cameraPositionState = rememberCameraPositionState()
    var markerPosition by remember { mutableStateOf<LatLng?>(null) }
    var address by remember {   mutableStateOf<String?>(null) }
    var detailAddress by remember { mutableStateOf<String?>("Can input your detail") }
    val coroutineScope = rememberCoroutineScope()
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    var properties by remember { mutableStateOf(MapProperties(isMyLocationEnabled = true)) }


    val defaultLocation = LatLng(0.0, 0.0) // Global fallback location (center of the world)

    // Fetch current location and update camera position
    LaunchedEffect(Unit) {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                val userLatLng = LatLng(it.latitude, it.longitude)
                coroutineScope.launch {
                    cameraPositionState.animate(CameraUpdateFactory.newLatLngZoom(userLatLng, 18f))
                }
            } ?: run {
                // Fallback to a global view (center of the world) if user location is unavailable
                coroutineScope.launch {
                    cameraPositionState.animate(CameraUpdateFactory.newLatLngZoom(defaultLocation, 2f)) // Zoom out for a global view
                }
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Green)) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(
                myLocationButtonEnabled = true,
                zoomControlsEnabled = true
            ),
            onMapClick = { latLng ->
                markerPosition = latLng

                // Perform reverse geocoding to get the address for the clicked location
                coroutineScope.launch(Dispatchers.IO) {
                    val geoCoder = Geocoder(context, Locale.getDefault())
                    address = try {
                        val addresses = geoCoder.getFromLocation(
                            latLng.latitude, latLng.longitude, 1
                        )
                        if (addresses != null && addresses.isNotEmpty()) {
                            addresses[0].getAddressLine(0) ?: "Address not found"
                        } else {
                            "Address not found"
                        }
                    } catch (e: Exception) {
                        "Error getting address"
                    }
                }
            },
//            properties = properties // Pass properties to the map
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

        // Custom Box element, aligned at the end of the Row
        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(horizontal = 12.dp)
                .fillMaxWidth(), 
            horizontalArrangement = Arrangement.End, 
            verticalAlignment = Alignment.CenterVertically 
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .border(2.dp, Color(0xDEDEDDE6), shape = CircleShape)
                    .background(Color.White, shape = CircleShape)
                    .clickable {
                       // todo
                    }
            ) {
               Image(painter = painterResource(id = R.drawable.scop), contentDescription = "scope",
                   modifier = Modifier
                       .fillMaxSize()
                       .padding(14.dp),
                   contentScale = ContentScale.Crop
               )
            }
        }


        // Back Button to handle navigation
        Box(modifier = Modifier.align(Alignment.TopEnd).padding(12.dp)){
            Box(modifier = Modifier.size(40.dp)
                .background(Color.Transparent, shape = CircleShape)
                .clickable {
                    navController.navigate(Routes.Address)
                }
            ){
                Image(painter = painterResource(id = R.drawable.exiticon), contentDescription = "exit icon",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
        // Bottom section with address details and Save button
         Box(modifier = Modifier
             .fillMaxWidth()
             .height(340.dp)
             .background(Color.White, shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
             .align(Alignment.BottomEnd)){
             Column(
                 modifier = Modifier
                     .fillMaxWidth()
                     .fillMaxHeight()
                     .padding(12.dp)
                     .align(Alignment.BottomEnd)
                     .background(Color.White),
             ) {
                 Text(
                     text = "Select your location",
                     fontSize = 20.sp,
                     color = Color.Black,
                     fontFamily = InterSemiBold
                 )
                 Spacer(modifier = Modifier.size(8.dp))
                 Row(modifier = Modifier.fillMaxWidth()) {
                     Icon(
                         imageVector = Icons.Default.Place,
                         contentDescription = "Place",
                         tint = Color.Green
                     )
                     Spacer(modifier = Modifier.width(8.dp)) // Add some space between the icon and the text
                     if (markerPosition != null) {
                         address?.let {
                             Text(
                                 text = it, // Display the selected address
                                 fontSize = 14.sp,
                                 fontFamily = InterReqular,
                                 color = Color(0x77777F89) // Customize the text color as needed
                             )
                         }
                     } else {
                         Text(
                             text = "No location selected",
                             fontSize = 14.sp,
                             color = Color.Gray
                         )
                     }
                 }

                 Spacer(modifier = Modifier.size(8.dp))
                 Text(text = "Address Detail", fontSize = 12.sp, fontFamily = InterMedium)
                 Box(modifier = Modifier
                     .fillMaxWidth()
                 ) {
                     TextField(
                         value = detailAddress.toString(),
                         onValueChange = { newText ->
                             detailAddress = newText
                         },
                         modifier = Modifier
                             .fillMaxWidth()
                             .drawBehind {
                                 val borderSize = 1.dp.toPx()
                                 drawLine(
                                     color = Color(0xDEDEDDE6),
                                     start = Offset(0f, size.height),
                                     end = Offset(size.width, size.height),
                                     strokeWidth = borderSize
                                 )
                             },
                         colors = TextFieldDefaults.textFieldColors(
                             containerColor = Color.Transparent,
                             unfocusedIndicatorColor = Color.Transparent
                         ),
                         textStyle = TextStyle(
                             color = Color(0x77777F89),
                             fontSize = 14.sp,
                             fontFamily = InterReqular,
                             lineHeight = 20.sp,
                         ),
                     )
                 }
                 Spacer(modifier = Modifier.height(20.dp))
                 Text(text = "Tag this location for later", fontSize = 12.sp)

                 // chip filter
                 val items = listOf(
                     ChipItem(label = "Home", icon = Icons.Default.Check),
                     ChipItem(label = "Office", icon = Icons.Default.Check),
                     ChipItem(label = "School", icon = Icons.Default.Check),
                     ChipItem(label = "Other", icon = Icons.Default.Check)
                 )
                 var selectedChipIndex by remember { mutableIntStateOf(0) }
                 ChipGroup(
                     items = items,
                     selectedChipIndex = selectedChipIndex,
                     onChipSelected = { index ->
                         selectedChipIndex = index
                     }
                 )

                 Spacer(modifier = Modifier.height(20.dp))
                 // Save button
                 Button(
                     onClick = { /* Handle Save Address */ },
                     colors = ButtonDefaults.buttonColors(Color(0xFFFFCC03)),
                     modifier = Modifier
                         .fillMaxWidth()
                         .height(44.dp),
                     shape = RoundedCornerShape(13.dp)
                 ) {
                     Text(text = "Save Address", fontSize = 16.sp, fontFamily = InterSemiBold)
                 }
             }
         }
    }
}



// chip
data class ChipItem(val label: String, val icon: ImageVector)
@Composable
fun ChipComponent(
    label: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(percent = 25))
            .background(if (isSelected) Color(0xFEFECC03) else Color.Transparent)
            .clickable(onClick = onClick),
    ) {
        Row (modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            if (isSelected){
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = Color.White
                )
            }
            Text(
                text = label,
                fontSize = 10.sp,
                fontFamily = InterSemiBold,
                color = if (isSelected) Color.White  else Color(0x99000000),
                modifier = Modifier
            )
        }
    }
}

@Composable
fun ChipGroup(
    items: List<ChipItem>,
    selectedChipIndex: Int,
    onChipSelected:(Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        items.forEachIndexed { index, item ->ChipComponent(
            label = item.label,
            icon = item.icon,
            isSelected = index == selectedChipIndex,
            onClick = { onChipSelected(index) },
            modifier = Modifier
                .padding(0.dp, 0.dp, 12.dp, 0.dp)
                .border(1.dp, Color(0xDEDEDDE6), shape = RoundedCornerShape(8.dp))
            )
        }
    }
}
