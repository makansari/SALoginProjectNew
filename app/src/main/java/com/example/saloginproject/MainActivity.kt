package com.example.saloginproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar.visibility = View.GONE

        buttonLogin.setOnClickListener {

            progressBar.visibility = View.VISIBLE

            var email = editTextEmail.text.toString()
            var password = editTextTextPassword.text.toString()

          //  var userlogin = UserLogin(email!!, password!!)

            var makecall = RetrofitClient.myRetrofit.ApiClientuserLogin(email, password)

            makecall.enqueue(object : Callback<ResponseBody>{
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    progressBar.visibility = View.GONE
                    if(response.isSuccessful){

                        var data = response.body().toString()
                        Log.i("mytag"," is : " + response.body()!!.string())

                      var i = Intent(this@MainActivity, DashBoardActivity::class.java)
                        startActivity(i)
                    }

                    else {
                        var errordata = response.errorBody().toString()
                        Log.i("mytag"," is : " + response.errorBody()!!.string())
                        Toast.makeText(applicationContext, "LOGIN FAILED " ,Toast.LENGTH_LONG).show()

                    }

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                    Log.i("mytag" ,"Failed Call : "+ t.message)
                }


            })


        }
    }
}