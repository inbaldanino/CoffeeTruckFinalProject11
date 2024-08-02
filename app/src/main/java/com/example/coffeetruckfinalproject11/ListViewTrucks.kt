package com.example.coffeetruckfinalproject11

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.coffeetruckfinalproject11.Model.Truck
import com.example.coffeetruckfinalproject11.model.Model

class ListViewTrucks : Fragment() {
    var truckList : ListView?=null
    var trucks : MutableList<Truck> ?=null

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
        trucks = Model.instance.trucks
        truckList = view.findViewById(R.id.lvTruckListView)
        truckList?.adapter = TruckListAdapter(trucks)
        truckList?.setOnItemClickListener {parent, view, position, id ->
            Log.i("TAG", "Row was click at: $position")
        }
    }
    class TruckListAdapter (val trucks: MutableList<Truck>?): BaseAdapter() {
        override fun getCount(): Int = trucks?.size ?: 0

        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }


        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            // Use convertView if it's not null, otherwise inflate a new view
            val view: View = convertView ?: LayoutInflater.from(parent?.context)
                .inflate(R.layout.truck_row_view, parent, false)

            // Retrieve the Truck object for the current position
            val truck = trucks?.get(position) ?: return view // Return early if truck is null

            // Find views in the layout
            val nameTextView: TextView = view.findViewById(R.id.tvRowTruckName)
            val locationTextView: TextView = view.findViewById(R.id.tvRowTruckLocation)
            val truckCheckBox: CheckBox = view.findViewById(R.id.cbRowTruckCheckBox)

            // Set the text and checkbox state based on the Truck object
            nameTextView.text = truck.name
            locationTextView.text = truck.location
            truckCheckBox.isChecked = truck.checkBox

            // Update the Truck object when the checkbox state changes
            truckCheckBox.setOnCheckedChangeListener { _, isChecked ->
                truck.checkBox = isChecked
            }

            return view
        }


    }
}

