package com.app.okacleaner.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.okacleaner.repository.TweetsyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategoryViewmodel @Inject constructor(private val  repository: TweetsyRepository)  : ViewModel() {

    val categories : StateFlow<List<String>>
    get()= repository.category

    init {
        viewModelScope.launch {
            repository.getCategories()
        }
    }



}