package com.example.coffeetruckfinalproject11

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeetruckfinalproject11.Model.Truck
import com.example.coffeetruckfinalproject11.model.Model
import com.example.coffeetruckfinalproject11.model.TrucksRecycleViewAdapter


class RecycleViewTruck : Fragment() {

    var truckRecycleView : RecyclerView?=null
    var trucks : MutableList<Truck> ?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
        trucks = Model.instance.trucks
        truckRecycleView = view?.findViewById(R.id.rvTruckRecycleView)
        truckRecycleView?.setHasFixedSize(true)

        //layout manager
        truckRecycleView?.layoutManager = LinearLayoutManager(this)
        //adapter
        val adapter = truckRecycleView(trucks)
        adapter.listener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.i("TAG", "TrucksRecycleViewAdapter: Position clicked $position")
            }//fff
            override  fun onTruckClicked(truck: Truck?) {
                Log.i("TAG", "TRUCK $truck")
            }
        }

        truckRecycleView?.adapter = adapter
        //on item click


    }
    interface onItemClickListener{
        fun onItemClick(position: Int)//truck
        fun onTruckClicked(truck: Truck?)
    }

    /*override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycle__view, container, false)
    }*/



    /*inner class TruckViewHolder(val itemView: View):RecyclerView.ViewHolder(itemView){


        var nameTextView: TextView? = null
        var locationTextView: TextView? = null
        var truckCheckBox: CheckBox? = null
        init{
            nameTextView = itemView.findViewById(R.id.tvRowTruckName)
            locationTextView = itemView.findViewById(R.id.tvRowTruckLocation)
            truckCheckBox = itemView.findViewById(R.id.cbRowTruckCheckBox)
            truckCheckBox?.setOnClickListener{
                (truckCheckBox?.tag as? Int)?.let {tag ->
                    var truck = trucks?.get(tag)
                    truck?.checkBox = truckCheckBox.isChecked ?:false
                }
            }
        }

        fun bind(truck:Truck?, position: Int)
        {
            nameTextView?.text = truck?.name
            locationTextView?.text = truck?.location
            truckCheckBox?.apply {
                isChecked = truck?.checkBox ?: false
                tag = position

            }
        }
    }*/




    /*inner class TrucksRecycleViewAdapter: RecyclerView.Adapter<TruckViewHolder>()
    {

        var listener: OnItemClickListener? = null


        override fun getItemCount(): Int = trucks?.size ?: 0

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TruckViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.truck_row_view, parent, false)
            return TruckViewHolder(itemView, listener)
        }


        override fun onBindViewHolder(holder: TruckViewHolder, position: Int) {
            val truck = trucks?.get(position)
            holder.bind(truck, position)



            /*holder.locationTextView?.text = truck.location
            holder.truckCheckBox?.isChecked = truck.checkBox
            holder.truckCheckBox?.tag = position
            holder.truckCheckBox?.setOnClickListener{
                (it.tag as? Int)?.let {tag ->
                    var truck = trucks?.get(tag)
                    truck?.checkBox = holder.truckCheckBox?.isChecked ?:false
                }
            }*/

           // Set the text and checkbox state based on the Truck object

        }
    }*/
}



/*var view: View ?= null
if (convertView == null) {
    view =
    val truckCheckBox: CheckBox? = view.findViewById(R.id.cbRowTruckCheckBox)
}
val truckCheckBox: CheckBox? = view?.findViewById(R.id.cbRowTruckCheckBox)
truckCheckBox?.setOnClickListener{
    (truckCheckBox?.tag as? Int)?.let {tag ->
    var truck = trucks?.get(tag)
    truck?.checkBox = truckCheckBox.isChecked ?:false
    }
}*/


