package com.example.firstcompose.screen

import Quote
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun QuatesList(quate: Quote, onClick: (quate:Quote) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White // Set background color to white
        ),
        elevation = CardDefaults.cardElevation(3.dp),
        modifier = Modifier
            .clickable(
            ) {
                onClick(quate)
            }
            .padding(7.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                imageVector = Icons.Filled.AccountBox,
                colorFilter = ColorFilter.tint(color = Color.White),
                contentDescription = "Quate",
                alignment = Alignment.TopStart,
                modifier = Modifier
                    .size(40.dp)
                    .background(color = Color.Black),
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Column {

                Text(
                    text = quate.quote,
                    maxLines = 2,
                    minLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
                )
                Box(
                    modifier = Modifier
                        .background(
                            color = Color.Gray
                        )
                        .fillMaxWidth(.4f)
                        .height(1.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                ) {
                    Text(
                        text = quate.author,

                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(0.dp, 4.dp, 0.dp, 0.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = quate.date,
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 7.sp),
                        modifier = Modifier
                            .padding(0.dp, 4.dp, 0.dp, 0.dp)
                            .align(Alignment.Bottom)
                    )
                }

            }

        }


    }

}