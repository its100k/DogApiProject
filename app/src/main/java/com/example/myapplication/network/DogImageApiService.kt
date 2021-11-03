package com.example.myapplication.network
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

//Fill out the Base URL
private const val BASE_URL = "https://dog.ceo/api/breeds/image/"

//This is added to print out network requests and responses in logcat
private val networkLoggingInterceptor =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


//create moshi breaks down the reponse we see into Json so we cn retrieve data and use it
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//Make retrofit builder
private val retrofit = Retrofit.Builder()
    .client(OkHttpClient.Builder().addInterceptor(networkLoggingInterceptor).build())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

//gets added on to the Base Url to get random image
interface DogImageApiService {
    @GET("random")
    suspend fun getRandomDogImage(): DogImage
}

//create object to access the interface function

object DogImageApi
{
    val retrofitService: DogImageApiService by lazy { retrofit.create(DogImageApiService::class.java)}
}
