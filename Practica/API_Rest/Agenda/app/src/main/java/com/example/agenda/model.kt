package com.example.agenda

import com.google.gson.annotations.SerializedName

class model (
    val Id:Int,
    val title:String,
    @SerializedName("body")
    val mbody:String

)