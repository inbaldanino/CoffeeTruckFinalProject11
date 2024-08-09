package com.example.coffeetruckfinalproject11.screens.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeetruckfinalproject11.model.Model
import com.example.coffeetruckfinalproject11.model.Truck
import com.example.coffeetruckfinalproject11.Modules.Trucks.adapters.TrucksRecyclerAdapter
import com.example.coffeetruckfinalproject11.R

/*class TrucksFragment : Fragment()
{
    var truckRecycleView : RecyclerView?=null
    var trucks : MutableList<Truck> ?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_trucks_fragments, container, false)

        trucks = Model.instance.trucks
        truckRecycleView = view?.findViewById(R.id.rvTrucksFrafmentsList)
        truckRecycleView?.setHasFixedSize(true)
        //layout manager
        truckRecycleView?.layoutManager = LinearLayoutManager(context)
        val adapter = TrucksRecycleAdapter(trucks)
        adapter.listener = object : TrucksRecycleAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.i("TAG", "TrucksRecycleViewAdapter: Position clicked $position")
            }

            override fun onTruckClicked(truck: Truck?) {
                Log.i("TAG", "Truck $truck")
            }
        }
        truckRecycleView?.adapter = adapter

        return view
    }
}*/

//From ChatGPT!!!!!!!!!!!!
class TrucksFragment : Fragment() {
    private var truckRecyclerView: RecyclerView? = null
    private var trucks: MutableList<Truck>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_trucks_fragments, container, false)

        trucks = Model.instance.trucks
        truckRecyclerView = view.findViewById(R.id.rvTrucksFrafmentsList)
        truckRecyclerView?.setHasFixedSize(true)
        truckRecyclerView?.layoutManager = LinearLayoutManager(context)

        val adapter = TrucksRecyclerAdapter(trucks)
        adapter.listener = object : RecycleViewTrucks.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.i("TAG", "Position clicked $position")
            }

            override fun onTruckClicked(truck: Truck?) {
                Log.i("TAG", "Truck clicked $truck")
            }
        }

        truckRecyclerView?.adapter = adapter
        return view
    }
}
