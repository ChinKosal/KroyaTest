package com.hrd.kroyafinalproject.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hrd.kroyafinalproject.R
import com.hrd.kroyafinalproject.ui.theme.InterBold
import com.hrd.kroyafinalproject.ui.theme.InterLight
import com.hrd.kroyafinalproject.ui.theme.InterMedium
import com.hrd.kroyafinalproject.ui.theme.InterSemiBold

@Composable
fun ProfileUser(){
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 12.dp, horizontal = 8.dp)){
        // top section of profile
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)) {
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
            ){
                Row (modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ronaldo),
                            contentDescription = "profile",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop  // Ensures the image covers the entire box, cropping where necessary
                        )
                    }

                    Box(modifier = Modifier){
                        Column {
                            Text(text = "Chin Kosal", fontSize = 16.sp , fontFamily = InterBold)
                            Text(text = "Since October, 2024", fontSize = 13.sp, fontFamily = InterLight)
                        }
                    }
                }
            }
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(horizontal = 8.dp)
            ){
                Text(text = "Edit",
                    fontSize = 16.sp,
                    fontFamily = InterSemiBold,
                    color = Color(0xFFE5B803),
                    modifier = Modifier.align(Alignment.TopEnd))
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        // card section of profile
        // row one
        Box (modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)){
           Row (modifier = Modifier.fillMaxWidth(),
               ){
               Card (modifier = Modifier.weight(1f).border(1.dp , Color(0x0D000000), shape = RoundedCornerShape(13.dp)),
                   ){
                   Box(modifier = Modifier
                       .background(Color(0xCCFFFAE6))
                       .fillMaxSize()){
                       Column (modifier = Modifier.padding(8.dp)){
                           Text(text = "Favorite")
                           Text(text = "List of their favorite dishes", fontSize = 10.sp,
                               fontFamily = InterLight)
                       }
                       Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "favorite",
                           tint = Color(0xFEFECC03),
                           modifier = Modifier.align(Alignment.BottomEnd).padding(horizontal = 5.dp, vertical = 3.dp))
                   }
               }

               Spacer(modifier = Modifier.width(8.dp))

               Card (modifier = Modifier.weight(1f).border(1.dp , Color(0x0D000000), shape = RoundedCornerShape(13.dp)),
               ){
                   Box(modifier = Modifier
                       .background(Color(0xCCFFFAE6))
                       .fillMaxSize()){
                       Column (modifier = Modifier.padding(8.dp)){
                           Text(text = "Address")
                           Text(text = "List of their favorite dishes", fontSize = 10.sp,
                               fontFamily = InterLight)
                       }
                       Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "favorite",
                           tint = Color(0xFEFECC03),
                           modifier = Modifier.align(Alignment.BottomEnd).padding(horizontal = 5.dp, vertical = 3.dp))
                   }
               }
           }
        }

        Spacer(modifier = Modifier.height(8.dp))
        // row two
        Box (modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)){
            Row (modifier = Modifier.fillMaxWidth(),
            ){
                Card (modifier = Modifier.weight(1f).border(1.dp , Color(0x0D000000), shape = RoundedCornerShape(13.dp)),
                ){
                    Box(modifier = Modifier
                        .background(Color(0xCCFFFAE6))
                        .fillMaxSize()){
                        Column (modifier = Modifier.padding(8.dp)){
                            Text(text = "Orders")
                            Text(text = "List of their favorite dishes", fontSize = 10.sp,
                                fontFamily = InterLight)
                        }
                        Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "favorite",
                            tint = Color(0xFEFECC03),
                            modifier = Modifier.align(Alignment.BottomEnd).padding(horizontal = 5.dp, vertical = 3.dp))
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                Card (modifier = Modifier.weight(1f).border(1.dp , Color(0x0D000000), shape = RoundedCornerShape(13.dp)),
                ){
                    Box(modifier = Modifier
                        .background(Color(0xCCFFFAE6))
                        .fillMaxSize()){
                        Column (modifier = Modifier.padding(8.dp)){
                            Text(text = "Sale Reports")
                            Text(text = "List of their favorite dishes", fontSize = 10.sp,
                                fontFamily = InterLight)
                        }
                        Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "favorite",
                            tint = Color(0xFEFECC03),
                            modifier = Modifier.align(Alignment.BottomEnd).padding(horizontal = 5.dp, vertical = 3.dp))
                    }
                }
            }
        }

        // end card section of profile


        // payment method
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "Payment Method", fontSize = 14.sp, fontFamily = InterMedium)
        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier
            .background(Color(0x9900000), shape = RoundedCornerShape(13.dp))
            .height(56.dp)
            .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
            ) {
            Box(modifier = Modifier.weight(1f)){
                Row (modifier = Modifier
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Box(modifier = Modifier
                        .size(30.dp)
                        .background(Color.Yellow, shape = CircleShape)

                    ){
                       Icon(imageVector = Icons.Default.Call, contentDescription = "contact",
                           tint = Color.White,
                           modifier = Modifier
                               .fillMaxSize()
                               .padding(5.dp)
                           )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Connected", fontSize = 16.sp, fontFamily = InterMedium)
                }
            }
            Box(modifier = Modifier.weight(1f)){
               Row (modifier = Modifier.align(Alignment.TopEnd),
                   verticalAlignment = Alignment.CenterVertically
                   ){
                   Text(text = "WeBill365", fontFamily = InterMedium, fontSize = 14.sp, color = Color(0x990000000))
                   Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "ArrowRight",
                       tint = Color(0x99000000),
                       modifier = Modifier.size(26.dp)
                       )
               }
            }
        }
        // end payment method

        // app setting
        // payment method
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "App Setting", fontSize = 14.sp, fontFamily = InterMedium)
        Spacer(modifier = Modifier.height(12.dp))

        // change location
        Row(modifier = Modifier
            .background(Color(0x9900000), shape = RoundedCornerShape(13.dp))
            .height(56.dp)
            .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)){
                Row (modifier = Modifier
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Box(modifier = Modifier
                        .size(30.dp)
                    ){
                        Icon(imageVector = Icons.Default.LocationOn, contentDescription = "contact",
                            tint = Color.Gray,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Change location", fontSize = 16.sp, fontFamily = InterMedium)
                }
            }
            Box(modifier = Modifier.weight(1f)){
                Row (modifier = Modifier.align(Alignment.TopEnd),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "ArrowRight",
                        tint = Color(0x99000000),
                        modifier = Modifier.size(26.dp)
                    )
                }
            }
        }
        // end change location
        Spacer(modifier = Modifier.height(8.dp))
        // notification
        Row(modifier = Modifier
            .background(Color(0x9900000), shape = RoundedCornerShape(13.dp))
            .height(56.dp)
            .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)){
                Row (modifier = Modifier
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Box(modifier = Modifier
                        .size(30.dp)
                    ){
                        Icon(imageVector = Icons.Default.Notifications, contentDescription = "contact",
                            tint = Color.Gray,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Notifications", fontSize = 16.sp, fontFamily = InterMedium)
                }
            }
            Box(modifier = Modifier.weight(1f)){
                Row (modifier = Modifier.align(Alignment.TopEnd),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "ArrowRight",
                        tint = Color(0x99000000),
                        modifier = Modifier.size(26.dp)
                    )
                }
            }
        }
        // end notification

        Spacer(modifier = Modifier.height(8.dp))
        // language
        Row(modifier = Modifier
            .background(Color(0x9900000), shape = RoundedCornerShape(13.dp))
            .height(56.dp)
            .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)){
                Row (modifier = Modifier
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Box(modifier = Modifier
                        .size(30.dp)
                    ){
                        Icon(imageVector = Icons.Default.Settings, contentDescription = "contact",
                            tint = Color.Gray,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Language", fontSize = 16.sp, fontFamily = InterMedium)
                }
            }
            Box(modifier = Modifier.weight(1f)){
                Row (modifier = Modifier.align(Alignment.TopEnd),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "ArrowRight",
                        tint = Color(0x99000000),
                        modifier = Modifier.size(26.dp)
                    )
                }
            }
        }
        // end language
        
        Spacer(modifier = Modifier.height(32.dp))
        // button logout 
        Button(onClick = { /*TODO*/ } , colors = ButtonDefaults.buttonColors(Color(0xFFFFCC03)),
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
        ) {
            Text(text = "Log out", fontSize = 16.sp, fontFamily = InterSemiBold,)
        }
    }
}