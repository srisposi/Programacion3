package com.example.comunicacionhttp_istic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.JsonReader
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONStringer
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class ListadoPaises : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_paises)
        var lsvPaises=findViewById<ListView>(R.id.LsvPaises)

        val datos=consultarDatos("https://api.openweathermap.org/data/2.5/onecall?lat=-34.7633&lon=-58.2117&appid=55ea6e5640cbb97fb596cd7ebb890b72")
        //Log.d("ConsultaSimple" ,datos)
       // val arrayDenombreDePaises= emptyArray<String>()
        val arrayDenombreDePaises:  ArrayList<String> = ArrayList()
        val arrayDeobjetosDePaises:  ArrayList<Pais> = ArrayList()
        val datosJSONObject= JSONObject(datos)
        arrayDenombreDePaises.add( datosJSONObject.getString("timezone"))
        Log.d("pais timezone", datosJSONObject.getString("timezone") )
        /*for ( i in 0..10)
        {
            //var pais= datosJSONObject.getJSONObject(i)
            // Log.d(pais.getString("name") ,pais.toString(0) )

            arrayDenombreDePaises.add( datosJSONObject.getString("timezone"))
            //arrayDeobjetosDePaises.add(Pais( datosJSONObject.getString("timezone")))
        }*/
        val adaptadorSimple=AdaptadorLsvSimple(this,arrayDeobjetosDePaises)
        lsvPaises.adapter=adaptadorSimple

      // val adaptador= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayDenombreDePaises)
       //lsvPaises.adapter=adaptador

    }
    @Throws(IOException::class)
    private fun consultarDatos(url:String):String{
        val policy= StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var datosDescargados: InputStream?=null
        try{
            val direccionWEB= URL(url)
            val conexion=direccionWEB.openConnection() as HttpURLConnection
            conexion.requestMethod="GET"
            conexion.connect()
            datosDescargados=conexion.inputStream
            return datosDescargados.bufferedReader().use{
                it.readText()
            }
        }catch (e: IOException)
        {
            Toast.makeText(this,"${e.message}", Toast.LENGTH_SHORT).show()
        }
        finally {
            if(datosDescargados!=null)
            {
                datosDescargados.close()
            }
        }
        return "NADA"
    }
}
