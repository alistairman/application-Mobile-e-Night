package com.example.enight.view.foodTrucks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.enight.R
import com.example.enight.network.FoodTruck

/**
 * this class is the adapter of the food truck fragment to show it in view
 */
class FoodTrucksAdapter:RecyclerView.Adapter<FoodTrucksAdapter.ViewHolder>(){

    var data = listOf<FoodTruck>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val truckName: TextView = itemView.findViewById(R.id.trucksName)
        //val name: TextView = itemView.findViewById(R.id.name)
        //val firstName: TextView = itemView.findViewById(R.id.firstName)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        //val res = holder.itemView.context.resources
        holder.truckName.text = item.location
        //holder.name.text = item.lastName
        //holder.firstName.text = item.firstName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.list_item_foodtrucks, parent, false)
        return ViewHolder(view)
    }
}