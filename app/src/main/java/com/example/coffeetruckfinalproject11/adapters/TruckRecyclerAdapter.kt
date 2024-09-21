package com.example.coffeetruckfinalproject11.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

    inner class TruckViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nameTextView: TextView = itemView.findViewById(R.id.tvRowTruckName)
        private val locationTextView: TextView = itemView.findViewById(R.id.tvRowTruckLocation)
        private val reviewCountTextView: TextView = itemView.findViewById(R.id.tvRowTruckReviewCount)

        private val truckImageView: ImageView = itemView.findViewById(R.id.truckImageView)
        private val viewCoffeeTruckBtn: Button = itemView.findViewById(R.id.viewBtn)

        @SuppressLint("SetTextI18n")
        fun bind(truck: CoffeeTruck) {
            viewCoffeeTruckBtn.setOnClickListener {
                listener.onTruckClicked(truck)
            }
            nameTextView.text = truck.name
            locationTextView.text = truck.location
            Picasso.get()
                .load(truck.photoUri)
                .into(truckImageView)
            reviewCountTextView.text = "${truck.reviews.filterNot { it.isEmpty() }.size} reviews"
        }
    }
}
