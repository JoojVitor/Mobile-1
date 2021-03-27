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
            if(!titulo.isNullOrEmpty() and !itens.contains(titulo)){
                itens.add(titulo)
            }else{
                mostreErro()
                txtTituloItem.hint = "Insira o nome do item!"
            }
            lvItens.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itens)
        }

        lvItens.setOnItemLongClickListener(OnItemLongClickListener { parent, view, pos, id ->
            val itemSelecionado = parent.getItemAtPosition(pos) as String
            itens.remove(itemSelecionado)
            lvItens.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itens)

            true
        })
    }

    fun mostreErro(){
        val inconsistencia = AlertDialog.Builder(this)
        inconsistencia.setTitle("Nome não informado")
            .setMessage("Insira um nome para o novo item!")
            .setIcon(R.drawable.app_icon)
            .setPositiveButton("Ok") { dialog, which -> dialog.dismiss() }
        inconsistencia.show()
    }
}