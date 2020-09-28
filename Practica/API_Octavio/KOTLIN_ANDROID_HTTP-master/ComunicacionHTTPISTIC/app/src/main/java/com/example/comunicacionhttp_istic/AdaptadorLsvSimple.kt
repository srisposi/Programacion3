package com.example.comunicacionhttp_istic

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class AdaptadorLsvSimple(var contexto:Context, listado:ArrayList<Pais>) :BaseAdapter(){

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
            vista=LayoutInflater.from(contexto).inflate(R.layout.filaconmasdatos,null)
            holder =ViewHolder(vista)
            vista.tag=holder
        }
        else
        {
            holder=vista.tag as? ViewHolder
        }
        val unPais =
//        val unPais=getItem(position)as Pais

        holder?.timezone?.text=unPais.timezone
        //holder?.timezone_offset?.text=unPais.timezone_offset

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
    private class ViewHolder(vista:View){
        var timezone :TextView?=null
        //var timezone_offset:TextView?=null
        init {
            timezone=vista.findViewById(R.id.textView)
            //timezone_offset=vista.findViewById(R.id.txtBandera)
        }
    }
}