package com.example.saloginproject

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiClient{

    @POST("login")
    fun ApiClientUserLogin(@Body userdata : UserLogin) : Call<ResponseBody>
}



class RetrofitClient {

    companion object{
        val myRetrofit = Retrofit.Builder()
            .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiClient::class.java)
    }


}

/*
probelalkhan@gmail.com
123456*/
