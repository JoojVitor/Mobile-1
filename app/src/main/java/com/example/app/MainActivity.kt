package com.example.app

import android.os.Bundle
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itens = ArrayList<String>()

        val txtTituloItem = findViewById<EditText>(R.id.txtTituloItem)
        val btnAdicionarItem = findViewById<Button>(R.id.btnAdicionarItem)
        val lvItens = findViewById<ListView>(R.id.lvItens)

        btnAdicionarItem.setOnClickListener {
            val titulo = txtTituloItem.text.toString()
            if(titulo.isNullOrEmpty()){
                txtTituloItem.error = "Insira um nome para o item."
            }else if(itens.contains(titulo)){
                txtTituloItem.error = "Este item já foi incluído."
            }else{
                itens.add(titulo)
                txtTituloItem.text.clear();
            }
            lvItens.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itens)
        }

        lvItens.onItemLongClickListener = OnItemLongClickListener { parent, view, pos, id ->
            val itemSelecionado = parent.getItemAtPosition(pos) as String
            itens.remove(itemSelecionado)
            lvItens.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itens)

            true
        }
    }
}