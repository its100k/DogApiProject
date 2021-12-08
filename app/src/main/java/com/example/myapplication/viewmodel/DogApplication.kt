package com.example.myapplication.viewmodel

import android.app.Application
import com.example.myapplication.database.AppDatabase

class DogApplication: Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}
