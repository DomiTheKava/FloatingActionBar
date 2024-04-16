package com.example.floatingactionbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity() : AppCompatActivity() {

    var listItems = ArrayList<String>()
    var adapter: ArrayAdapter<String>? = null

    lateinit var listView: ListView
    lateinit var fab: FloatingActionButton

    lateinit var undoOnClickListener: View.OnClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.lv1)
        fab = findViewById(R.id.fab1)

        adapter = ArrayAdapter( this, android.R.layout.simple_list_item_1, listItems)

        listView.adapter = adapter

        fab.setOnClickListener() {
            addListItem()
            Snackbar.make(it, "add an item", Snackbar.LENGTH_LONG).setAction("UNDO", undoOnClickListener).show()
        }

        undoOnClickListener = View.OnClickListener {
            listItems.removeAt(listItems.size - 1)
            adapter?.notifyDataSetChanged()
            Snackbar.make(it, "item removed", Snackbar.LENGTH_LONG).setAction("Action", null).show()
        }

    }

    fun addListItem() {
        listItems.add("item 1")
        adapter?.notifyDataSetChanged()
    }
}