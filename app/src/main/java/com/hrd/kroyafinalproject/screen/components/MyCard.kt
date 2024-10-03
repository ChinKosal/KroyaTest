package com.hrd.kroyafinalproject.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hrd.kroyafinalproject.ui.theme.InterLight
import com.hrd.kroyafinalproject.ui.theme.InterSemiBold

@Composable
fun CardOrder(
    title: String,
    description: String,
    imageResId: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(178.dp)
            .height(130.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        // Inside the card, use a Box or any other container to set the background
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFEFB1)) // background card
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                // Title text
                Text(
                    text = title,
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontFamily = InterSemiBold
                )

                // Description text
                Text(
                    text = description,
                    fontSize = 12.sp,
                    fontFamily = InterLight
                )

                // Image at the bottom-end
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Image(
                        painter = painterResource(id = imageResId),
                        contentDescription = null,
                        modifier = Modifier.size(70.dp)
                    )
                }
            }
        }
    }
}

