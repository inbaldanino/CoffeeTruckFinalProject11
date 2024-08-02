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
    var trucks: MutableList<Truck>? = null

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
            truckList?.setOnItemClickListener { parent, view, position, id ->
                Log.i("TAG", "Row was clicked at: $position")
            }        }
    }
    class TruckListAdapter (val trucks: MutableList<Truck>?): BaseAdapter() {
        override fun getCount(): Int = trucks?.size ?: 0

        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }

        override fun getItemId(position: Int): Long = 0



        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            // Use convertView if it's not null, otherwise inflate a new view

            // Retrieve the Truck object for the current position
            val truck = trucks?.get(position)
            var view: View? = null
            if (convertView == null) {
                view = LayoutInflater.from(parent?.context).inflate(R.layout.truck_row_view, parent, false)
                val truckCheckBox: CheckBox? = view?.findViewById(R.id.cbRowTruckCheckBox)
                truckCheckBox?.setOnClickListener {

                    (truckCheckBox?.tag as? Int)?.let { tag ->
                        var truck = trucks?.get(tag)
                        truck?.checkBox = truckCheckBox?.isChecked ?: false
                    }
                }
            }
            view = view ?: convertView

            val nameTextView: TextView? = view?.findViewById(R.id.tvRowTruckName)
            val idTextView: TextView? = view?.findViewById(R.id.tvRowTruckLocation)
            val truckCheckBox: CheckBox? = view?.findViewById(R.id.cbRowTruckCheckBox)

            nameTextView?.text = truck?.name
            idTextView?.text = truck?.location
            truckCheckBox?.apply {
                isChecked = truck?.checkBox ?: false
                tag = position
            }

            return view!!
            // Find views in the layout

            // Update the Truck object when the checkbox state changes
            /*truckCheckBox.setOnCheckedChangeListener { _, isChecked ->
                truck.checkBox = isChecked
                Log.i("TAG", "checkbox clicked: $position")
            }*/

        }


    }


}






