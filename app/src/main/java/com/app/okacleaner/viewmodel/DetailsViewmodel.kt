package com.app.okacleaner.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.okacleaner.model.TweetsResponseItem
import com.app.okacleaner.repository.TweetsyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewmodel @Inject constructor(private val  repository: TweetsyRepository)  : ViewModel() {

    val Tweets : StateFlow<List<TweetsResponseItem>>
    get()= repository.tweetsList

    init {
        viewModelScope.launch {
            repository.getTweetList("android")
        }
    }



}