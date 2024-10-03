package com.hrd.kroyafinalproject.screen.auth

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hrd.kroyafinalproject.ui.theme.InterLight
import com.hrd.kroyafinalproject.ui.theme.InterMedium
import com.hrd.kroyafinalproject.ui.theme.InterSemiBold

enum class ContainerState {
    Fab,
    Fullscreen
}

@SuppressLint("RememberReturnType")
@Composable
fun AddressScreen(){
    var containerState by remember { mutableStateOf(ContainerState.Fab) }
    var transition = updateTransition(targetState = containerState)

    // Define colors for each state
    val padding by transition.animateDp(label = "" , transitionSpec = {
        tween(500)
    }
    ) { state ->
        when (state) {
           ContainerState.Fab -> {
               0.dp
           }
            ContainerState.Fullscreen -> {
                0.dp
            }
        }
    }


    Box (modifier = Modifier
        .fillMaxSize()
        .padding(padding.value.dp)
        ){
        transition.AnimatedContent {
            when (it){
                ContainerState.Fab -> {
                    Column (modifier = Modifier.fillMaxSize().background(Color.White)
                        .padding(horizontal = 12.dp, vertical = 12.dp)) {
                        Row (modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent)
                        ){
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription =  "ArrowBack" )
                        }
                        Row (modifier = Modifier
                            .fillMaxWidth()
                            .drawBehind {
                                val borderSize = 1.dp.toPx()
                                drawLine(
                                    color = Color.Gray,
                                    start = Offset(0f, size.height),
                                    end = Offset(size.width, size.height),
                                    strokeWidth = borderSize
                                )
                            }
                            .background(Color.Transparent),
                            horizontalArrangement = Arrangement.SpaceBetween){
                            Box(modifier = Modifier
                                .background(Color.Transparent)
                                .padding(0.dp, 20.dp, 0.dp, 20.dp)){
                                Box(modifier = Modifier
                                    .background(Color.Transparent)
                                ){
                                    Box(modifier = Modifier.background(Color.Transparent)){
                                        Text(text = "My Address", fontSize = 16.sp, fontFamily = InterSemiBold)
                                    }
                                    Row (modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.End) {
                                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add",
                                            tint = Color.Green , modifier = Modifier.clickable {
                                                containerState = ContainerState.Fullscreen
                                            })
                                        Text(text = "Add",  color = Color.Black, fontSize = 16.sp, fontFamily = InterMedium,
                                            fontWeight = FontWeight.Medium,  modifier = Modifier.clickable {
                                                containerState = ContainerState.Fullscreen
                                            })
                                    }
                                }
                            }
                        }
                        Row (modifier = Modifier
                            .fillMaxWidth()
                            .padding()
                            .background(Color.Transparent)
                            .drawBehind {
                                val borderSize = 1.dp.toPx()
                                drawLine(
                                    color = Color.Gray,
                                    start = Offset(0f, size.height),
                                    end = Offset(size.width, size.height),
                                    strokeWidth = borderSize
                                )
                            },
                        ){
                            Box(modifier = Modifier
                                .background(Color.Transparent)
                                .padding(0.dp, 12.dp, 0.dp, 10.dp)
                                .fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ){
                                Box(modifier = Modifier.background(Color.Transparent)){
                                    Column(modifier = Modifier) {
                                        Text(text = "HRD Center, St22",  fontSize = 16.sp, fontFamily = InterSemiBold)
                                        LimitedText(text = "Russian federation blvd (#10),steadfast")
                                    }
                                }
                                Box(modifier = Modifier.background(Color.Transparent)){
                                    Row (modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.End,
                                        verticalAlignment = Alignment.CenterVertically) {
                                        Text(text = "Office", color = Color.Green, fontSize = 16.sp,
                                            fontFamily = InterMedium)
                                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "MoreVert",
                                            tint = Color.Gray)
                                    }
                                }
                            }
                        }
                    }
                }
                ContainerState.Fullscreen -> {
                    MapApp()
                }
            }
        }
    }
}

@Composable
fun LimitedText(text: String, maxChars: Int = 29) {
    val limitedText = if (text.length > maxChars) {
        text.take(maxChars) + "..."
    } else {
        text
    }
    Text(
        text = limitedText,
        fontSize = 12.sp,
        color = Color.DarkGray,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontFamily = InterLight
    )
}


@Preview
@Composable
fun AddressScreenPreview(){
    AddressScreen()
}