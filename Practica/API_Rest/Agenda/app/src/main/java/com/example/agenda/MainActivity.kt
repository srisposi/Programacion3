package com.example.agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit=Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()
        val jsonAPI=retrofit.create(jsonAPI::class.java)
        val mcall: Call<List<model>> = jsonAPI.getInfo()
        mcall.enqueue(object : Callback<List<model>>
        {
            override fun onFailure(call: Call<List<model>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

            override fun onResponse(call: Call<List<model>>, response: Response<List<model>>) {
                val model: List<model> = response.body()!!
                val stringBuilder = StringBuilder()
                for (i in model) {
                    stringBuilder.append(i.tittle)
                    stringBuilder.append("\n")
                    stringBuilder.append((i.id))
                    stringBuilder.append(("\n"))
                    stringBuilder.append((i.mbody))
                    stringBuilder.append(("\n"))
                    stringBuilder.append(("\n"))
                }
                txtUser.movementMethod = ScrollingMovementMethod()
                txtUser.text = stringBuilder
            }

        })
    }
}
