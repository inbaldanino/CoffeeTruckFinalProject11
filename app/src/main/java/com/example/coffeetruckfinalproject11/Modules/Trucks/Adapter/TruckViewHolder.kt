package com.example.coffeetruckfinalproject11.Modules.Trucks.Adapter

import com.example.coffeetruckfinalproject11.Modules.Trucks.RecycleViewTrucks

/*import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeetruckfinalproject11.Model.Truck
import com.example.coffeetruckfinalproject11.Modules.Trucks.RecycleViewTrucks.OnItemClickListener
import com.example.coffeetruckfinalproject11.R

class TruckViewHolder (val itemView: View,
                       val listener: OnItemClickListener?,
                       var trucks: MutableList<Truck>? ): RecyclerView.ViewHolder(itemView)
        {


        var nameTextView: TextView? = null
        var locationTextView: TextView? = null
        var truckCheckBox: CheckBox? = null
        var truck: Truck? = null
        var trucks : MutableList<Truck> ?=null

        init {
            nameTextView = itemView.findViewById(R.id.tvRowTruckName)
            locationTextView = itemView.findViewById(R.id.tvRowTruckLocation)
            truckCheckBox = itemView.findViewById(R.id.cbRowTruckCheckBox)
            truckCheckBox?.setOnClickListener {
                val truck = trucks?.get(adapterPosition)
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
            locationTextView?.text = truck?.location
            truckCheckBox?.apply {
                isChecked = truck?.checkBox ?: false
            }
        }

    }
}*/

//From ChatGPT!!!!!!!!!!!!

import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeetruckfinalproject11.Model.Truck
import com.example.coffeetruckfinalproject11.R

class TruckViewHolder(
    itemView: View,
    private val listener: RecycleViewTrucks.OnItemClickListener?,
    private val trucks: MutableList<Truck>?
) : RecyclerView.ViewHolder(itemView) {

    private val nameTextView: TextView = itemView.findViewById(R.id.tvRowTruckName)
    private val locationTextView: TextView = itemView.findViewById(R.id.tvRowTruckLocation)
    private val truckCheckBox: CheckBox = itemView.findViewById(R.id.cbRowTruckCheckBox)
    private var truck: Truck? = null

    init {
        truckCheckBox.setOnClickListener {
            val truck = trucks?.get(adapterPosition)
            truck?.checkBox = truckCheckBox.isChecked
        }

        itemView.setOnClickListener {
            Log.i("TAG", "Position clicked $adapterPosition")
            listener?.onItemClick(adapterPosition)
            listener?.onTruckClicked(truck)
        }
    }

    fun bind(truck: Truck?) {
        this.truck = truck
        nameTextView.text = truck?.name
        locationTextView.text = truck?.location
        truckCheckBox.isChecked = truck?.checkBox ?: false
    }
}
