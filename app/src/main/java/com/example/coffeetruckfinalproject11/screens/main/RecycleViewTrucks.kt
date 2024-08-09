package com.example.coffeetruckfinalproject11.screens.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeetruckfinalproject11.Model.Model
import com.example.coffeetruckfinalproject11.Model.Truck
import com.example.coffeetruckfinalproject11.R

class RecycleViewTrucks : Fragment() {
    var truckRecycleView : RecyclerView?=null
    var trucks : MutableList<Truck> ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        trucks = Model.instance.trucks
        truckRecycleView = view?.findViewById(R.id.rvRecycleViewTrucks)
        truckRecycleView?.setHasFixedSize(true)

        //layout manager
        truckRecycleView?.layoutManager = LinearLayoutManager(/*by claude & GPT, instead of this*/context)
        //adapter set
        //truckRecycleView?.adapter = TrucksRecycleAdapter()
        val adapter = TrucksRecycleAdapter()
        adapter.listener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.i("TAG", "TrucksRecycleViewAdapter: Position clicked $position")
            }

            override fun onTruckClicked(truck: Truck?) {
            Log.i("TAG", "Truck $truck")
            }
        }
        truckRecycleView?.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycle_view_trucks, container, false)
    }


    interface OnItemClickListener{
        fun onItemClick(position: Int)
        fun onTruckClicked(truck: Truck?)
    }

    inner class TruckViewHolder(val itemView: View, val listener: OnItemClickListener?):RecyclerView.ViewHolder(itemView){


        var nameTextView: TextView? = null
        var idTextView: TextView? = null
        var truckCheckBox: CheckBox? = null
        var truck: Truck? = null

        init {
             nameTextView = itemView.findViewById(R.id.tvRowTruckName)
             idTextView = itemView.findViewById(R.id.tvRowTruckLocation)
             truckCheckBox = itemView.findViewById(R.id.cbRowTruckCheckBox)
            truckCheckBox?.setOnClickListener {
                var truck = trucks?.get(adapterPosition)
                truck?.checkBox = truckCheckBox?.isChecked ?: false


            }
            itemView.setOnClickListener{
                Log.i("TAG", "TruckViewHolder: Position clicked $adapterPosition")

                listener?.onItemClick(adapterPosition)
                listener?.onTruckClicked(truck)
            }
        }

        fun bind (truck: Truck?)
        {
            this.truck = truck
            nameTextView?.text = truck?.name
            idTextView?.text = truck?.location
            truckCheckBox?.apply {
                isChecked = truck?.checkBox ?: false
            }
        }

    }
    inner class TrucksRecycleAdapter : RecyclerView.Adapter<TruckViewHolder>()
        {
            var listener : OnItemClickListener? = null

            override fun getItemCount(): Int = trucks?.size ?: 0
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TruckViewHolder {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.truck_row_view, parent, false)
                return TruckViewHolder(itemView, listener)

            }

        override fun onBindViewHolder(holder: TruckViewHolder, position: Int) {
            val truck = trucks?.get(position)
            holder.bind(truck)
        }
    }
}