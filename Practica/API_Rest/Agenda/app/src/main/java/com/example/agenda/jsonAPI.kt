package com.example.agenda



import retrofit2.Call
import retrofit2.http.GET

interface jsonAPI {
    //https://jsonplaceholder.typicode.com/posts
    @GET("posts")
    fun getInfo(): Call<List<model>>

}