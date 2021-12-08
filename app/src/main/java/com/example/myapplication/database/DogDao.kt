package com.example.myapplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.concurrent.Flow

@Dao
interface DogDao {

    @Query("SELECT * FROM dogtable")
    fun getAllDogImages(): Flow<List<DogEntity>>

    @Insert
    suspend fun addDogImage(dogEntity: DogEntity)
}