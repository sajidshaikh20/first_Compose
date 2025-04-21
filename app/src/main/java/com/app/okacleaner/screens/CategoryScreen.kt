package com.app.okacleaner.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.okacleaner.viewmodel.CategoryViewmodel


@Composable
fun CategoryScreen(){

    val categoryViewmodel: CategoryViewmodel = viewModel()

    val category: State<List<String>> =  categoryViewmodel.categories.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.SpaceBetween
        ) {

       items (category.value) {
           CategoryItem(it)
       }
    }

}


@Composable
fun CategoryItem(category: String){
    Box(modifier = Modifier.padding(
        4.dp,).size(160.dp).
        clip(RoundedCornerShape(8.dp)).border(
            1.dp, Color(0xFFEEEEEE)),
        contentAlignment = Alignment.BottomCenter
    ){
        Text(text = category,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(0.dp,20.dp),
            style = MaterialTheme.typography.body1
            )
    }

}