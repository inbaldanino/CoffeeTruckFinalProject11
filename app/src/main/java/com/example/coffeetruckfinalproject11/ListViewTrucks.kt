package com.example.coffeetruckfinalproject11

import android.os.Bundle
//import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment

class ListViewTrucks : Fragment() {
    var truckList : ListView?=null
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize your views here
        truckList = view.findViewById(R.id.lvTruckListView)
        truckList?.adapter = TruckListAdapter()
    }
    class TruckListAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return 5
        }

        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view: View = convertView ?: LayoutInflater.from(parent?.context)
                .inflate(R.layout.truck_row_view, parent, false)

            val nameTextView: TextView = view.findViewById(R.id.tvRowTruckName)
            nameTextView.text = "Truck Name"

            return view

        }
    }
}

