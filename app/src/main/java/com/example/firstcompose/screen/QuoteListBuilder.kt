package com.example.firstcompose.screen

import Quote
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable


@Composable
 fun QuoteListBuilder(data: Array<Quote>, onClick:(quate:Quote)->Unit) {
    LazyColumn(content = {
        items(data){
            QuatesList(quate = it, onClick)
        }
    })
}