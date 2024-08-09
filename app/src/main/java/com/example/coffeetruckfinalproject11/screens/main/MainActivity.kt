package com.example.coffeetruckfinalproject11.screens.main

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeetruckfinalproject11.viewmodels.CoffeeTruckViewModel
import com.example.coffeetruckfinalproject11.R

class MainActivity : AppCompatActivity() {
    private val coffeeTruckViewModel: CoffeeTruckViewModel by viewModels()
    private var listViewFragment: ListViewTrucks? = null
    private var addNewCoffeeTruckFragment: AddNewCoffeeTruck? = null

    class ButtonOnClickListener: View.OnClickListener {
        override fun onClick(v: View?) {
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //val addNewCoffeeTruckButton: Button = findViewById(R.id.addNewCoffeeTruckButton)
        //addNewCoffeeTruckButton.setOnClickListener { onAddNewCoffeeTruckClicked() }
        /*val userProfileCreation = userProfileCreation ()
        val transaction = supportFragmentManager.beginTransaction()
        val addProfileCreationButton: Button = findViewById(R.id.btnCreateNewProfile)
        addProfileCreationButton.setOnClickListener(::onAddTruckButtonClicked)
        transaction.add(R.id.fcMainActivity, userProfileCreation)
        transaction.commit()
        if (savedInstanceState == null) {
            displayTruckListView()
        }
        val listener = ButtonOnClickListener()
        addProfileCreationButton.setOnClickListener(listener)
*/
    }

    fun onAddTruckButtonClicked(view: View)
    {
        if (addNewCoffeeTruckFragment == null)
            displayAddNewCoffeeTruckFragment()
        else
            removeAddNewCoffeeTruckFragment()
    }

    private fun userProfileCreation(): Any {
        // val userProfileCreation = userProfileCreation ()
        //  val transaction = supportFragmentManager.beginTransaction()
        // transaction.add(R.id.fcMainActivity, userProfileCreation)
        //transaction.commit()
        return 0
    }

    private fun FrameLayout(): Any {

        return TODO("Provide the return value")
    }

    //functions for the addnewcoffeetruck fragment
    private fun onAddNewCoffeeTruckClicked(view: View)
    {
        if (addNewCoffeeTruckFragment == null)
            displayAddNewCoffeeTruckFragment()
        else
            removeAddNewCoffeeTruckFragment()
    }

    private fun displayAddNewCoffeeTruckFragment()
    {
        addNewCoffeeTruckFragment = AddNewCoffeeTruck()
        addNewCoffeeTruckFragment?.let {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.flAddCoffeeTruck, it)
            transaction.addToBackStack("addNewCoffeeTruck")
            transaction.commit()
        }
    }
    private fun removeAddNewCoffeeTruckFragment()
    {
        addNewCoffeeTruckFragment?.let {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(it)
            transaction.addToBackStack("Tag")
            transaction.commit()
        }
        addNewCoffeeTruckFragment = null
    }


    //functions for the listview fragment
    private fun displayTruckListView() {
        //listViewFragment = ListViewTruck.newInstance("Another try")
        listViewFragment = ListViewTrucks()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcMainActivity, listViewFragment!!)
            .commit()
        addNewCoffeeTruckFragment = null
    }
}