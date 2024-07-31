package com.example.coffeetruckfinalproject11

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var listViewFragment : ListViewTrucks ?= null
    var addNewCoffeeTruckFragment : AddNewCoffeeTruck ?= null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val addNewCoffeeTruckButton: Button = findViewById(R.id.addNewCoffeeTruckButton)
        addNewCoffeeTruckButton.setOnClickListener(::onAddNewCoffeeTruckClicked)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        displayTruckListView()

    }
    fun onAddNewCoffeeTruckClicked(view: View)
    {
        if (addNewCoffeeTruckFragment == null)
        {
            displayAddNewCoffeeTruckFragment()
        }
        else
        {
            removeAddNewCoffeeTruckFragment()
        }
    }

    fun displayTruckListView() {
        listViewFragment = ListViewTrucks()
        listViewFragment?.let {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.flListViewFragment, it)
            transaction.addToBackStack("TAG")
            transaction.commit()
        }
    }

    fun displayAddNewCoffeeTruckFragment() {
        addNewCoffeeTruckFragment = AddNewCoffeeTruck()
        addNewCoffeeTruckFragment?.let {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.flAddCoffeeTruck, it)
            transaction.addToBackStack("TAG")
            transaction.commit()
        }
    }

    /*fun  removeTruckListView() {
        listViewFragment = ListViewTrucks()
        listViewFragment?.let {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(it)
            transaction.addToBackStack("TAG")
            transaction.commit()
        }
    }*/

    fun removeAddNewCoffeeTruckFragment() {
        addNewCoffeeTruckFragment = AddNewCoffeeTruck()
        addNewCoffeeTruckFragment?.let {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(it)
            transaction.addToBackStack("TAG")
            transaction.commit()

        }
        addNewCoffeeTruckFragment = null
    }
}
