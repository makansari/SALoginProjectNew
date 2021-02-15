package com.example.saloginproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonLogin.setOnClickListener {

            var email = editTextEmail.text.toString()
            var password = editTextTextPassword.text.toString()

          //  var userlogin = UserLogin(email!!, password!!)

            var makecall = RetrofitClient.myRetrofit.ApiClientuserLogin(email, password)

            makecall.enqueue(object : Callback<ResponseBody>{
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    runOnUiThread {
                        Toast.makeText(applicationContext, "checking response : " + response.body()!!.toString(),Toast.LENGTH_LONG).show()
                    }

                    if(response.isSuccessful){

                        var data = response.body().toString()
                        Log.i("mytag"," is : " + response.body()!!.string())
                    }

                    else {
                        var errordata = response.errorBody().toString()
                        Log.i("mytag"," is : " + response.errorBody()!!.string())

                    }

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                    Log.i("mytag" ,"Failed Call : "+ t.message)
                }


            })


        }
    }
}