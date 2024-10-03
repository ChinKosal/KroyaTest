package com.hrd.kroyafinalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hrd.kroyafinalproject.screen.auth.AddressScreen
import com.hrd.kroyafinalproject.screen.components.CardOrder
import com.hrd.kroyafinalproject.screen.profile.ProfileUser
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
                    ProfileUser()
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