package com.example.coffeetruckfinalproject11.adapters

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


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeetruckfinalproject11.R
import com.example.coffeetruckfinalproject11.model.CoffeeTruck
import com.squareup.picasso.Picasso

class TrucksRecyclerAdapter(
    private var trucks: List<CoffeeTruck>,
    private val listener: OnItemClickListener,
) : RecyclerView.Adapter<TrucksRecyclerAdapter.TruckViewHolder>() {
    interface OnItemClickListener {
        fun onTruckClicked(truck: CoffeeTruck)
    }

    override fun getItemCount(): Int = trucks.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TruckViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.truck_row_view, parent, false)
        return TruckViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TruckViewHolder, position: Int) {
        val truck = trucks[position]
        holder.bind(truck)
    }

    //viewholder
    class TruckViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nameTextView: TextView = itemView.findViewById(R.id.tvRowTruckName)
        private val locationTextView: TextView = itemView.findViewById(R.id.tvRowTruckLocation)
        private val reviewCountTextView: TextView = itemView.findViewById(R.id.tvRowTruckReviewCount)

        private val truckImageView: ImageView = itemView.findViewById(R.id.truckImageView)
        @SuppressLint("SetTextI18n")
        fun bind(truck: CoffeeTruck) {
            nameTextView.text = truck.name
            locationTextView.text = truck.location
            Picasso.get()
                .load(truck.photoUri)
                .into(truckImageView)
            reviewCountTextView.text = "${truck.reviews.filterNot { it.isEmpty() }.size} reviews"
        }
    }
}
