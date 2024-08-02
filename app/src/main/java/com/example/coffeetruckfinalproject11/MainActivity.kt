package com.example.coffeetruckfinalproject11

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

/*class MainActivity : AppCompatActivity() {
    private var listViewFragment: ListViewTrucks? = null
    private var addNewCoffeeTruckFragment: AddNewCoffeeTruck? = null
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
    private fun onAddNewCoffeeTruckClicked(view: View)
    {
        if (addNewCoffeeTruckFragment == null) {
            displayAddNewCoffeeTruckFragment()
        } else {
            removeAddNewCoffeeTruckFragment()
        }
    }

    private fun displayTruckListView() {
        if (listViewFragment == null) {
            listViewFragment = ListViewTrucks()
            supportFragmentManager.beginTransaction()
                .add(R.id.flListViewFragment, listViewFragment!!)
                .commit()
        }
    }

    private fun displayAddNewCoffeeTruckFragment() {
        if (addNewCoffeeTruckFragment == null) {
            addNewCoffeeTruckFragment = AddNewCoffeeTruck()
            supportFragmentManager.beginTransaction()
                .add(R.id.flAddCoffeeTruck, addNewCoffeeTruckFragment!!)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun removeAddNewCoffeeTruckFragment() {
        addNewCoffeeTruckFragment?.let {
            supportFragmentManager.beginTransaction()
                .remove(it)
                .commit()
            addNewCoffeeTruckFragment = null
        }
    }
}*/

class MainActivity : AppCompatActivity() {
    private var listViewFragment: ListViewTrucks? = null
    private var addNewCoffeeTruckFragment: AddNewCoffeeTruck? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //val addNewCoffeeTruckButton: Button = findViewById(R.id.addNewCoffeeTruckButton)
        //addNewCoffeeTruckButton.setOnClickListener { onAddNewCoffeeTruckClicked() }

        if (savedInstanceState == null) {
            displayTruckListView()
        }



    }
    //functions for the addnewcoffeetruck fragment
    private fun onAddNewCoffeeTruckClicked()
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
            transaction.addToBackStack("Tag")
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
        listViewFragment = ListViewTrucks()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcMainActivity, listViewFragment!!)
            .commit()
        addNewCoffeeTruckFragment = null
    }


}
