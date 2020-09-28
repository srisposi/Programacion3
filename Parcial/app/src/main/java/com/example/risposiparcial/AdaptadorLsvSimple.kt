package com.example.risposiparcial

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.PictureDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.caverock.androidsvg.SVG
import com.caverock.androidsvg.SVGParseException
import com.example.risposiparcial.ConsultaDatos.Companion.consultarDatos
import com.example.risposiparcial.R

class AdaptadorLsvSimple(var contexto: Context, listado: ArrayList<Pais>) :BaseAdapter(){

    var listado:ArrayList<Pais>?=null
    init {
        this.listado=listado
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //  TODO("Not yet implemented")
        var holder :ViewHolder?=null
        var vista:View?=convertView
        if(vista==null)
        {
            vista=LayoutInflater.from(contexto).inflate(R.layout.filacondatos, null)
            holder =ViewHolder(vista)
            vista.tag=holder
        }
        else
        {
            holder=vista.tag as? ViewHolder
        }
        val unPais=getItem(position)as Pais

        val svgFlagString = consultarDatos(unPais.flag)

        holder?.nombre?.text = unPais.nombre
        holder?.region?.text = unPais.region
     //   holder?.timezone?.text = unPais.timezone
        holder?.flag?.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        try {
            val svg: SVG = SVG.getFromString(svgFlagString)
            val drawable: Drawable = PictureDrawable(svg.renderToPicture())
            holder?.flag?.setImageDrawable(drawable)
        } catch (e: SVGParseException) {
            Log.d("ERROR SVG", e.message)
        }

        //holder?.cod?.tex
        //Picasso.get().load(imgUri).into(holder?.bandera)

        return vista!!
    }

    override fun getItem(position: Int): Any {
        //  TODO("Not yet implemented")
        return listado?.get(position)!!
    }

    override fun getItemId(position: Int): Long {
        //TODO("Not yet implemented")
        return  position.toLong()
    }

    override fun getCount(): Int {
        //TODO("Not yet implemented")
        return listado?.count()!!// para optener el valor !!
    }
    private class ViewHolder(vista: View){
        var nombre :TextView? = null
        var region:TextView? = null
        var flag: ImageView? = null
        init {
            nombre = vista.findViewById(R.id.txtNombre)
            region = vista.findViewById(R.id.txtRegion)
            flag = vista.findViewById(R.id.imgBandera)
        }
    }

}