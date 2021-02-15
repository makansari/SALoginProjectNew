package com.example.saloginproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var email : String
    lateinit var password : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar.visibility = View.GONE

        buttonLogin.setOnClickListener {

            progressBar.visibility = View.VISIBLE

            email = editTextEmail.text.toString()
            password = editTextTextPassword.text.toString()

            //  var userlogin = UserLogin(email!!, password!!)

            var makecall = RetrofitClient.myRetrofit.ApiClientuserLogin(email, password)

            makecall.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    progressBar.visibility = View.GONE



                    if(response.isSuccessful){

                        var data = response.body()
                        Log.i("mytag"," is : " + response.body()!!.string())

                        storeData(email, password)

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
                }
            })
        }
    }

    private fun storeData(email: String, password: String) {


        var makecall2 =  RetrofitClient2.myRetrofit.ApiClientuserLogin(email, password)

        makecall2.enqueue(object : Callback<UserLogin>{
            override fun onResponse(call: Call<UserLogin>, response: Response<UserLogin>) {

                var myresponse = response.body()

                var data = myresponse?.user

                var e =   data?.email
                var n= data?.name

                Toast.makeText(this@MainActivity,"name is $n and email is $e ",Toast.LENGTH_LONG).show()






            }

            override fun onFailure(call: Call<UserLogin>, t: Throwable) {
            }

        })

    }
}