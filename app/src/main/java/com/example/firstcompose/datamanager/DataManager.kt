package com.example.firstcompose.datamanager

import Quote
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.example.firstcompose.Pages
import com.google.gson.Gson

object DataManager {

    var data = emptyArray<Quote>()

    var currentQuote : Quote?= null;
//
    var isDataLoaded = mutableStateOf(false)

    var currentPage =  mutableStateOf(Pages.LISTING)

    fun loadAssetsFromFile(context: Context){
        val inputStream= context.assets.open("dummy.json")
        val size : Int = inputStream.available()
        val buffer= ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json= String(buffer, Charsets.UTF_8)
        val gson= Gson()
        data = gson.fromJson(json,Array<Quote>::
        class.java)
        isDataLoaded.value= true
    }

}