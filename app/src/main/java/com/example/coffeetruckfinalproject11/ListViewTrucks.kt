package com.example.coffeetruckfinalproject11

import android.graphics.ColorSpace
import android.graphics.ColorSpace.Model
import android.os.Bundle
//import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
//import com.example.coffeetruckfinalproject11.Model.Truck

class ListViewTrucks : Fragment() {
    var truckList : ListView?=null
    //var trucks :MutableList<Truck> ?=null

    /*override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_list_view_trucks, container, false)
        return view
    }

    val truckListView : ListView = findViewById(R.id.truckListView)*/
    // lateinit var truckListView: ListView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_view_trucks, container, false)
    }

    //this is claude suggestions
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize your views here
        //trucks = Model.instance.trucks
        truckList = view.findViewById(R.id.lvTruckListView)
        truckList?.adapter = TruckListAdapter()
    }
    class TruckListAdapter : BaseAdapter(/*val trucks: MutableList<Truck>*/) {
        override fun getCount(): Int = 20//trucks?.size ?: 0

        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            //val truck = trucks.get(position)
            val view: View = convertView ?: LayoutInflater.from(parent?.context)
                .inflate(R.layout.truck_row_view, parent, false)

            val nameTextView: TextView = view.findViewById(R.id.tvRowTruckName)
            val locationTextView: TextView = view.findViewById(R.id.tvRowTruckLocation)
            val truckCheckBox : CheckBox = view.findViewById(R.id.cbRowTruckCheckBox)

            nameTextView.text = "Hello"
            return view

        }
    }
}

