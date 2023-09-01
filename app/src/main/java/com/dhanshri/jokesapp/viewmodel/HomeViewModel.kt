package com.dhanshri.jokesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dhanshri.jokesapp.model.Jokes
import com.dhanshri.jokesapp.repository.MemesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val memesRepository: MemesRepository): ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            memesRepository.getMemes()
        }
    }


    val memes : LiveData<Jokes>
     get() = memesRepository.memes

}