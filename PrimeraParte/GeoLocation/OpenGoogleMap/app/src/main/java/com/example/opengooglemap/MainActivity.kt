package com.example.opengooglemap

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var mGoogleMapBtn : Button
    val uri = "Carlos Calvo 1186, C1102 AAX, Buenos Aires"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mGoogleMapBtn = findViewById(R.id.btnGoogleMap)
        mGoogleMapBtn.setOnClickListener {
            val uriMap = Uri.parse(uri)
            val intent = Intent(Intent.ACTION_VIEW,uriMap)
            intent.setPackage("com.google.android.apps.maps")
            if(intent.resolveActivity(packageManager)!=null)
            {
                startActivity(intent)
            }
        }

    }
}
