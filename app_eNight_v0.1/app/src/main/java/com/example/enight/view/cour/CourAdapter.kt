package com.example.enight.view.cour

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.enight.dataBase.cour.Cour
import com.example.enight.databinding.ListItemCourBinding


/**
* this class is the adapter of the course object used to adapt and show data binding
* in the recycle view
* the courseDiffCallBack is use the know witch data has been changed
*/
class CourAdapter(private val clickListener: CourListener): ListAdapter<Cour, CourAdapter.ViewHolder>(CourDiffCallback()){


    /**
     *this class receive a data as binding data, describe it adapt it
     * and update the recycle view
     *
     */
    class ViewHolder private  constructor(val binding: ListItemCourBinding) : RecyclerView.ViewHolder(binding.root){
        /**
         * this method make the link between the data and correct object in the view
         */
        fun bind(item: Cour, clickListener: CourListener){
            binding.cour = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        /**
         * this object import the correct link object in the view
         */
        companion object{
            fun from(parent: ViewGroup):ViewHolder{
                val layout = LayoutInflater.from(parent.context)
                val binding = ListItemCourBinding.inflate(layout,parent,false)
                return ViewHolder(binding)
            }
        }
    }

    /**
     * this method get data and add it to recycle view
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,clickListener)
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
class CourDiffCallback : DiffUtil.ItemCallback<Cour>() {
    /**
     * this method check if the two data have the same id
     * return true if the two data are the same
     */
    override fun areItemsTheSame(oldItem: Cour, newItem: Cour): Boolean {
        return oldItem.courId == newItem.courId
    }

    /**
     * this method check if the two data content the same values
     */
    override fun areContentsTheSame(oldItem: Cour, newItem: Cour): Boolean {
        return oldItem==newItem
    }
}

/**
 * this class is the listener of an click on the courses recycle view
 */
class CourListener(val clickListener: (courId: String) -> Unit) {
    fun onClick(cour: Cour) = clickListener(cour.courId)

}