package com.example.coffeetruckfinalproject11.Modules.Trucks.Adapter

/*import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeetruckfinalproject11.Model.Model
import com.example.coffeetruckfinalproject11.Model.Truck
import com.example.coffeetruckfinalproject11.Modules.Trucks.RecycleViewTrucks.OnItemClickListener
import com.example.coffeetruckfinalproject11.Modules.Trucks.RecycleViewTrucks.TruckViewHolder
import com.example.coffeetruckfinalproject11.R

class TrucksRecyclerAdapter (var trucks: MutableList<Truck>?): RecyclerView.Adapter<TruckViewHolder>()
{
  var listener : OnItemClickListener? = null
  var trucks : MutableList<Truck>? = null

  override fun getItemCount(): Int = trucks?.size ?: 0

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TruckViewHolder {
      val itemView = LayoutInflater.from(parent.context).inflate(R.layout.truck_row_view, parent, false)
      return TruckViewHolder(itemView, listener, trucks)

  }

  override fun onBindViewHolder(holder: TruckViewHolder, position: Int) {
      val truck = trucks?.get(position)
      holder.bind(truck)
  }
}*/


//From ChatGPT!!!!!!!!!!!!

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeetruckfinalproject11.Model.Truck
import com.example.coffeetruckfinalproject11.Modules.Trucks.RecycleViewTrucks
import com.example.coffeetruckfinalproject11.R

class TrucksRecyclerAdapter(
    private var trucks: MutableList<Truck>?
) : RecyclerView.Adapter<TruckViewHolder>() {

    var listener: RecycleViewTrucks.OnItemClickListener? = null

    override fun getItemCount(): Int = trucks?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TruckViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.truck_row_view, parent, false)
        return TruckViewHolder(itemView, listener, trucks)
    }

    override fun onBindViewHolder(holder: TruckViewHolder, position: Int) {
        val truck = trucks?.get(position)
        holder.bind(truck)
    }
}
