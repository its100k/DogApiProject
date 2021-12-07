package com.example.myapplication
import androidx.lifecycle.*
import com.example.myapplication.database.DogDao
import com.example.myapplication.database.DogEntity
import com.example.myapplication.network.DogImage
import com.example.myapplication.network.DogImageApi


import kotlinx.coroutines.launch


class MainViewModel(private val DogDao: DogDao) : ViewModel() {

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

    fun addDog(dogEntity: DogEntity) {
        viewModelScope.launch {
            DogDao.addDogImage(dogEntity)
        }
    }

    fun getAllDogs(): LiveData<List<DogEntity>> {
        return DogDao.getAllDogImages().asLiveData()
    }

    class DogViewModelFactory(
        private val dogDao: DogDao
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java))
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(dogDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
