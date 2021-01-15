package com.example.enight.view.foodTrucks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.enight.dataBase.foodTruck.FoodTruck
import com.example.enight.databinding.ListItemFoodtrucksBinding

/**
 * this class is the adapter of the food truck fragment to show it in view
 */
class FoodTrucksAdapter(private val clickListener: FoodTruckListener): ListAdapter<FoodTruck, FoodTrucksAdapter.ViewHolder>(
    FoodTruckDiffCallback()){


    /**
     *this class receive a data as binding data, describe it adapt it
     * and update the recycle view
     *
     */
    class ViewHolder private constructor( val binding: ListItemFoodtrucksBinding) : RecyclerView.ViewHolder(binding.root){
        /**
         * this method make the link between the data and correct object in the view
         */
        fun bind(item: FoodTruck, clickListener: FoodTruckListener){
            binding.foodTruck = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        /**
         * this object import the correct link object in the view
         */
        companion object{
            fun from(parent: ViewGroup): ViewHolder {
                val layout = LayoutInflater.from(parent.context)
                val binding = ListItemFoodtrucksBinding.inflate(layout,parent,false)
                return ViewHolder(binding)
            }
        }
    }

    /**
     * this method get data and add it to recycle view
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    /**
     * this method return a view group with the parent received witch is the recycle view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
}

/**
 * this class is used to check how data change
 */
class FoodTruckDiffCallback : DiffUtil.ItemCallback<FoodTruck>() {
    /**
     * this method check if the two data have the same id
     * return true if the two data are the same
     */
    override fun areItemsTheSame(oldItem: FoodTruck, newItem: FoodTruck): Boolean {
        return oldItem.location == newItem.location
    }

    /**
     * this method check if the two data content the same values
     */
    override fun areContentsTheSame(oldItem: FoodTruck, newItem: FoodTruck): Boolean {
        return oldItem==newItem
    }
}

class FoodTruckListener(val clickListener: (location: String) -> Unit) {
    fun onClick(foodTruck: FoodTruck) = clickListener(foodTruck.location)

}