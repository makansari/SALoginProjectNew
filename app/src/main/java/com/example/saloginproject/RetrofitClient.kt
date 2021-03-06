package com.example.saloginproject

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiClient {


    @FormUrlEncoded
    @POST("login")
    fun ApiClientuserLogin(
        @Field("email") emailOne: String,
        @Field("password") passwordOne: String
    ): Call<ResponseBody>

/*    @GET("login")
    fun ApiClientUserLogin(@Body userdata: List<UserLogin>) : Call<List<UserLogin>>
}*/

}

class RetrofitClient {

    companion object {
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
