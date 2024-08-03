package com.example.coffeetruckfinalproject11.Module.Truck

import android.os.Bundle
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
        truckRecycleView?.layoutManager = LinearLayoutManager(this)
        truckRecycleView?.adapter = TrucksRecycleAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycle_view_trucks, container, false)
    }
    inner class TruckViewHolder(val itemView: View):RecyclerView.ViewHolder(itemView){



        var nameTextView: TextView? = null
        var idTextView: TextView? = null
        var truckCheckBox: CheckBox? = null

        init {
             nameTextView = itemView.findViewById(R.id.tvRowTruckName)
             idTextView = itemView.findViewById(R.id.tvRowTruckLocation)
             truckCheckBox = itemView.findViewById(R.id.cbRowTruckCheckBox)
            truckCheckBox?.setOnClickListener {

                (truckCheckBox?.tag as? Int)?.let { tag ->
                    var truck = trucks?.get(tag)
                    truck?.checkBox = truckCheckBox?.isChecked ?: false
                }
            }
        }

        fun bind (truck: Truck?, position: Int)
        {
            nameTextView?.text = truck?.name
            idTextView?.text = truck?.location
            truckCheckBox?.apply {
                isChecked = truck?.checkBox ?: false
                tag = position
            }
        }

    }
    inner class TrucksRecycleAdapter : RecyclerView.Adapter<TruckViewHolder>()
        {
            override fun getItemCount(): Int = trucks?.size ?: 0
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TruckViewHolder {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.truck_row_view, parent, false)
                return TruckViewHolder(itemView)
                }

            override fun onBindViewHolder(holder: TruckViewHolder, position: Int) {
                val truck = trucks?.get(position)
                holder.bind(truck, position)
            }
        }
}