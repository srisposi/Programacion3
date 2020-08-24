package com.example.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_producto.view.*
import android.widget.ArrayAdapter

//Clase Adaptador para que coloque la clase Producto en el listView
class ProductoAdapter(private val nContext: Context, private val listaProducto: List<Producto>) : ArrayAdapter<Producto>(nContext, 0, listaProducto){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(nContext).inflate(R.layout.item_producto,parent,false)
        val producto = listaProducto[position]
        layout.txtName.text = producto.nombre
        layout.txtPrice.text = producto.precio.toString()
        layout.imageView.setImageResource(producto.imagen)
        return layout
    }
}