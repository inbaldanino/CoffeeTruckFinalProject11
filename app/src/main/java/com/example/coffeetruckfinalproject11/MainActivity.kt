package com.example.coffeetruckfinalproject11

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.coffeetruckfinalproject11.Modules.Trucks.ListViewTrucks
import com.example.coffeetruckfinalproject11.Modules.Trucks.RecycleViewTrucks
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val coffeeTruckViewModel: CoffeeTruckViewModel by viewModels()
    private var listViewFragment: ListViewTrucks? = null
    private var addNewCoffeeTruckFragment: AddNewCoffeeTruck? = null

    class ButtonOnClickListener:View.OnClickListener{
        override fun onClick(v: View?) {
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, RecycleViewTrucks())
            .commit()

        val  navigation = findViewById<BottomNavigationView>(R.id.navigation);
        navigation.setOnItemSelectedListener { item ->
            var fragment : Fragment?= null
            fragment =  when(item.itemId) {
                R.id.home -> RecycleViewTrucks()
                else -> null
            }
            fragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, it)
                    .commit()
            }

            true
        }


        if (savedInstanceState == null) {
            displayTruckListView()
        }
        val listener = ButtonOnClickListener()
    }

    fun onAddTruckButtonClicked(view:View)
    {
        if (addNewCoffeeTruckFragment == null)
            displayAddNewCoffeeTruckFragment()
        else
            removeAddNewCoffeeTruckFragment()
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

private fun FragmentTransaction.add(fcMainActivity: Int, userProfileCreation: Any) {

}
