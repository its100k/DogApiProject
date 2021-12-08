package com.example.myapplication
import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import com.example.myapplication.database.DogEntity
import com.example.myapplication.viewmodel.DogApplication
import com.example.myapplication.viewmodel.MainActivity2
import com.squareup.picasso.Picasso


    class MainActivity : AppCompatActivity() {
        private val viewModel: MainViewModel by viewModels {
            MainViewModel.DogViewModelFactory((application as DogApplication).database.dogDao())
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)




        val changeDogButton: Button = findViewById(R.id.button2)

        viewModel.currentlyDisplayedImage.observe(this,
            {
                val mainImage: ImageView = findViewById(R.id.dogImageHolder)
                Picasso.with(this).load(it.imgSrcUrl).into(mainImage)
            })

        changeDogButton.setOnClickListener {
            viewModel.getNewDog()


            val currentImgUrl = viewModel.dogEntity.value?.url
            val newDogImage = currentImgUrl?.let { it1 ->
                DogEntity(url = it1) }
            if (newDogImage != null) {
                viewModel.addDog(newDogImage)

            }
            val prevDogButton :Button = findViewById(R.id.button1)
            prevDogButton.setOnClickListener {
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
            }
        }
    }

        }




