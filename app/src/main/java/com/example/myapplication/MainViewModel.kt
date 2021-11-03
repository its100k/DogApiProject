package com.example.myapplication
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.network.DogImage
import com.example.myapplication.network.DogImageApi


import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    private val _currentlyDisplayedImage = MutableLiveData<DogImage>()
    val currentlyDisplayedImage: LiveData<DogImage> = _currentlyDisplayedImage


    init {
        getNewDog()
    }

    fun getNewDog() {
        viewModelScope.launch {
            //Get the first item in the list from response
            _currentlyDisplayedImage.value = DogImageApi.retrofitService.getRandomDogImage()

        }
    }
}

