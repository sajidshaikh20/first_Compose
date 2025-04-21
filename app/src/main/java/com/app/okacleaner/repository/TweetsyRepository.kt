package com.app.okacleaner.repository

import com.app.okacleaner.api.TweetsyApi
import com.app.okacleaner.model.TweetsResponseItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetsyRepository @Inject constructor(private  val tweetsyApi: TweetsyApi) {

    private val _category= MutableStateFlow<List<String>>(emptyList());
    val category: StateFlow<List<String>>
    get() = _category


    private val _tweetsList= MutableStateFlow<List<TweetsResponseItem>>(emptyList());
    val tweetsList: StateFlow<List<TweetsResponseItem>>
    get() = _tweetsList



    suspend fun getCategories(){
        val response = tweetsyApi.getCategories()
        if (response.isSuccessful&& response.body()!=null){
            _category.emit(response.body()!!)
        }
    }

    suspend fun  getTweetList(category: String){
        val response = tweetsyApi.getTweets(category)

        if(response.isSuccessful && response.body()!=null){
            _tweetsList.emit(response.body()!!)
        }

    }
}