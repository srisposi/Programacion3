package com.example.risposiparcial

import android.content.Intent
import android.graphics.drawable.Drawable
import android.graphics.drawable.PictureDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.caverock.androidsvg.SVG
import com.caverock.androidsvg.SVGParseException
import com.example.risposiparcial.ConsultaDatos.Companion.consultarDatos
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detalle_pais.*
import kotlinx.android.synthetic.main.activity_main.*

class DetallePais : AppCompatActivity() {

    //Firebase
    //=================================================================
    private lateinit var database: DatabaseReference// ...
    private lateinit var postReference: DatabaseReference
    private lateinit var postKey: String
    private var postListener: ValueEventListener? = null
    //=================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pais)

        val pais = intent.getSerializableExtra("PaisDetalle") as Pais
        txtNombre.text = pais.nombre
        txtRegion.text = pais.region

        val svgFlagString = consultarDatos(pais.flag)

        imgBandera.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        try {
            val svg: SVG = SVG.getFromString(svgFlagString)
            val drawable: Drawable = PictureDrawable(svg.renderToPicture())
            imgBandera.setImageDrawable(drawable)
        } catch (e: SVGParseException) {
            Log.d("ERROR SVG", e.message)

        }

        //==========================================================================
        //Firebase Guardado
        val txtNombre = findViewById<TextView>(R.id.txtNombre)
        val txtRegion = findViewById<TextView>(R.id.txtRegion)

        postReference = FirebaseDatabase.getInstance().reference
            .child("Países")

        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        btnGuardar.setOnClickListener {
            // [START initialize_database_ref]
            database = FirebaseDatabase.getInstance().reference
            // [END initialize_database_ref]
            val key = database.child("Países").push().key
            if (key == null) {
                // Log.w("error", "Couldn't get push key for posts")
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            }
            val nombre = this.txtNombre.getText().toString()
            val region = this.txtRegion.getText().toString()

            val post = MainActivity.Post(nombre, region)
            val postValues = post.toMap()
            val childUpdates = HashMap<String, Any>()
            childUpdates["/Países/$nombre/$key"] = postValues
            childUpdates["/Info_Países/$nombre/$region/$key"] = postValues
            database.updateChildren(childUpdates)

            val intent = Intent(this@DetallePais, MainActivity::class.java)
            intent.putExtra("Nombre", "$nombre")
            intent.putExtra("Region", "$region")
            startActivity(intent)
        }

    }
    // [START post_class]
    @IgnoreExtraProperties
    data class Post(
        var nombre: String? = "",
        var region: String? = "",
        var starCount: Int = 0,
        var stars: MutableMap<String, Boolean> = HashMap()
    ) {

        // [START post_to_map]
        @Exclude
        fun toMap(): Map<String, Any?> {
            return mapOf(
                "Nombre" to nombre,
                "Region" to region
                //"destino" to destino,
                //"mensaje" to mensaje
            )
        }

    }
}
