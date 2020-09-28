package com.example.risposiparcial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//Firebase
//=================================================================
    private lateinit var database: DatabaseReference// ...
    private lateinit var postReference: DatabaseReference
    private lateinit var postKey: String
    private var postListener: ValueEventListener? = null
//=================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//=================================================================
        val txtName = findViewById<TextView>(R.id.txtName)
        val txtPassword = findViewById<TextView>(R.id.txtPassword)
//=================================================================

//=================================================================

        postReference = FirebaseDatabase.getInstance().reference
            .child("mensajes")
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        btnRegistrar.setOnClickListener {
            // [START initialize_database_ref]
            database = FirebaseDatabase.getInstance().reference
            // [END initialize_database_ref]
            val key = database.child("mensajes").push().key
            if (key == null) {
                // Log.w("error", "Couldn't get push key for posts")
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            }
            val name = this.txtName.getText().toString()
            val password = this.txtPassword.getText().toString()
            //val usuario=this.txtUsuario.getText().toString()
            //val mensaje= this.txtMensaje.getText().toString()
            val post = Post(name, password)
            val postValues = post.toMap()
            val childUpdates = HashMap<String, Any>()
            childUpdates["/mensajes/$name/$key"] = postValues
            childUpdates["/mensajes_usuario/$name/$password/$key"] = postValues
            database.updateChildren(childUpdates)
            //Paso variables al contador
            val name2 = name
            val password2 = password
            val intent = Intent(this@MainActivity, ConsultaAPI::class.java)
            intent.putExtra("Name", "$name2")
            intent.putExtra("Password", "$password2")
            startActivity(intent)
        }
        //Botón para volver
        val btnAtras = findViewById<Button>(R.id.btnAtras)
        btnAtras.setOnClickListener {
            val atras = Intent(this, MainActivity::class.java)
            startActivity(atras)
        }
    }
    // [START post_class]
    @IgnoreExtraProperties
    data class Post(
        var name: String? = "",
        var password: String? = "",
        //var destino: String? = "",
        //var mensaje: String? = "",
        var starCount: Int = 0,
        var stars: MutableMap<String, Boolean> = HashMap()
    ) {

        // [START post_to_map]
        @Exclude
        fun toMap(): Map<String, Any?> {
            return mapOf(
                "Nombre" to name,
                "Contraseña" to password
                //"destino" to destino,
                //"mensaje" to mensaje
            )
        }
        // [END post_to_map]
    }
    //[END post_class]
}
