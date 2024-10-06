package com.hrd.kroyafinalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hrd.kroyafinalproject.screen.auth.AddressScreen
import com.hrd.kroyafinalproject.screen.auth.MapApp
import com.hrd.kroyafinalproject.screen.auth.MapWithMarkerAndAddress
import com.hrd.kroyafinalproject.ui.theme.InterMedium
import com.hrd.kroyafinalproject.ui.theme.InterSemiBold
import com.hrd.kroyafinalproject.ui.theme.KroyaFinalProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KroyaFinalProjectTheme {
                SetBarColor(color = MaterialTheme.colorScheme.background)
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {

                    // call card to use by put it in row
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(8.dp),
//                        horizontalArrangement = Arrangement.spacedBy(8.dp)
//                    ) {
//                        // First Card with custom title, description, and image
//                        CardOrder(
//                            title = "Pizza Order",
//                            description = "Order what you love",
//                            imageResId = R.drawable.food2,
//                            modifier = Modifier.weight(1f)
//                        )
//
//                        // Second Card with different title, description, and image
//                        CardOrder(
//                            title = "Food Recipe",
//                            description = "Learn how to cook",
//                            imageResId = R.drawable.food3,
//                            modifier = Modifier.weight(1f)
//                        )
//                    }
//                    AddressScreen()
//                    MapWithMarkerAndAddress()
//                    ProfileUser()
                    SetupRoute()
                }
            }

        }
    }
    @Composable
    private fun SetBarColor(color: Color) {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = color
            )
        }
    }
}

@Composable
fun SetupRoute(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Address) {
        composable(Routes.Address) {
            AddressScreen(navController = navController)
        }
        composable(Routes.Map){
            MapApp(navController = navController)
        }
    }
}


object Routes {
    var Address = "Address";
    var Map = "Map";
}



