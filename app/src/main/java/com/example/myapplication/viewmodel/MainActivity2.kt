package com.example.myapplication.viewmodel

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.MainViewModel
import com.example.myapplication.R
import com.squareup.picasso.Picasso

class MainActivity2 : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels {
        MainViewModel.DogViewModelFactory((application as DogApplication).database.dogDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        viewModel.getAllDogs().observe(this) {
            val count = it.size


            val imageView = findViewById<ImageView>(R.id.dogImageHolder2)
            Picasso.with(this).load(it[count - 1].url).into(imageView)


            val button = findViewById<Button>(R.id.button3)

        }

    }
}