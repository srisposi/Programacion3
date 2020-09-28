package com.example.risposiparcial
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.io.Serializable

class Pais(nombre:String, region: String, flag:String):Serializable {
    var nombre=""
    var region=""
    //var timezone=""
    var flag = ""
    init{
        this.nombre=nombre
        this.region=region
        //this.timezone=timezone
        this.flag=flag
        }
}
