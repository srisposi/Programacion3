package com.example.risposiparcial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.risposiparcial.ConsultaDatos.Companion.consultarDatos
import kotlinx.android.synthetic.main.activity_consulta_a_p_i.*
import org.json.JSONArray

class ConsultaAPI : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta_a_p_i)


//=============================================================================
        //Recibimos las variables de Registro
        val intent = intent
        val name2 = intent.getStringExtra("Name")
        val password2 = intent.getStringExtra("Password")
        //=============================================================
        val lblContador = findViewById<TextView>(R.id.textView)
        //==============================================================
        if (name2==null && password2==null)
        {
            //lblVisor.visibility=True
            textView.text= "Bienvenido " + name2 + " !!!!"
        }
//============================================================================

        val btnVerificar=findViewById<Button>(  R.id.btnVerificar)
        val btnConsultaSimple=findViewById<Button>(  R.id.btnConsultaSimple)
        val btnIrListado=findViewById<Button>(  R.id.btnIrListado)

        btnIrListado.setOnClickListener{
            val intento1 = Intent(this, ListadoPaises::class.java)
            startActivity(intento1)
        }
        btnVerificar.setOnClickListener{
            if (ControlDeConexion.hayConexion(this))
            {
                Toast.makeText(this,"Estamos conectados",Toast.LENGTH_SHORT).show()

            }else
            {
                Toast.makeText(this,"Sin Conexión",Toast.LENGTH_SHORT).show()
            }
        }
        btnConsultaSimple.setOnClickListener{
            val datos=consultarDatos("https://restcountries.eu/data/afg.svg")
            Log.d("ConsultaSimple" ,datos)
            val datosArrayJson= JSONArray(datos)

            for ( i in 0..datosArrayJson.length()-1)
            {
                var pais= datosArrayJson.getJSONObject(i)
                Log.d(pais.getString("name") ,pais.toString(0) )
                Log.d("pais", pais.getString("first_name"))
            }

        }

    }
    /*
    * TESTIN API REST
    * https://www.mockaroo.com/ GENERAR DATOS
    * https://mocki.io/fake-json-api API REST FAKE
    * https://api.mocki.io/v1/0b4979ea
    * */
    override fun onStart() {
        super.onStart()
        if (ControlDeConexion.hayConexion(this))
        {
            Toast.makeText(this,"Estamos conectados",Toast.LENGTH_SHORT).show()

        }else
        {
            Toast.makeText(this,"Sin Conexión", Toast.LENGTH_SHORT).show()
        }




    }
}
