package com.example.altafirebase

import android.annotation.SuppressLint
//import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
//import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference// ...
    private lateinit var postReference: DatabaseReference
    private lateinit var postKey: String
    private var postListener: ValueEventListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val txtName=findViewById<TextView>(R.id.txtName)
        val txtContraseña=findViewById<TextView>(R.id.txtContraseña)
        //val txtUsuario=findViewById<TextView>(R.id.txtUsuario)
        //val txtDestino=findViewById<TextView>(R.id.txtDestino)

        postReference = FirebaseDatabase.getInstance().reference
            .child("mensajes")

        val btnSubir=findViewById<Button>(R.id.btnSubir)

        btnSubir.setOnClickListener {
            // [START initialize_database_ref]
            database = FirebaseDatabase.getInstance().reference
            // [END initialize_database_ref]
            val key = database.child("mensajes").push().key

            if (key == null) {
                // Log.w("error", "Couldn't get push key for posts")
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()

            }

            val name=this.txtName.getText().toString()
            val contraseña=this.txtContraseña.getText().toString()
            //val usuario=this.txtUsuario.getText().toString()
            //val mensaje= this.txtMensaje.getText().toString()
            val post = Post(contraseña, name)
            val postValues = post.toMap()

            val childUpdates = HashMap<String, Any>()
            childUpdates["/mensajes/$name/$key"] = postValues
            childUpdates["/mensajes_usuario/$name/$contraseña/$key"] = postValues

            database.updateChildren(childUpdates)
        }
    }
}


// [START post_class]
@IgnoreExtraProperties
data class Post(
    var name: String? = "",
    var contraseña: String? = "",
    //var destino: String? = "",
    //var mensaje: String? = "",
    var starCount: Int = 0,
    var stars: MutableMap<String, Boolean> = HashMap()
) {

    // [START post_to_map]
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "contraseña" to contraseña
            //"destino" to destino,
            //"mensaje" to mensaje

        )
    }
    // [END post_to_map]
}
// [END post_class]