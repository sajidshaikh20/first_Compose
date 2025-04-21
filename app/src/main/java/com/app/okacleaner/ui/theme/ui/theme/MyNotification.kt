package com.app.okacleaner.ui.theme.ui.theme

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment


@Composable
fun MyNotificationUI(value: Int, increamentFunction: () -> Unit) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End
    ) {
        Text("You have sent $value Notificaion")
        Button(onClick = {
          //  count.value++;
            increamentFunction()
            Log.d("ButtonClick", "button click");
        }) {
            Text("Send Notification")
        }
    }
}