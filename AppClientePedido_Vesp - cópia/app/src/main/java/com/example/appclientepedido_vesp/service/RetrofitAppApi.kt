package com.example.appclientepedido_vesp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitAppApi {

    private const val BASE_URL = "https://api.mockfly.dev/mocks/e65fe627-5d02-4381-a293-bd693114d8db/"

    val apiService: IService by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IService::class.java)

    }

}