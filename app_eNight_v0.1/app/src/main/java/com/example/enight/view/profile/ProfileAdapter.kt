package com.example.enight.view.profile

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.enight.dataBase.profile.Profile
import com.example.enight.databinding.ListItemProfileBinding

/**
 * this class is the adapter of the profile object used to adapt and show data binding
 * in the recycle view
 * the profileDiffCallBack is use the know witch data has changed
 */
class ProfileAdapter: ListAdapter<Profile, ProfileAdapter.ViewHolder>(ProfileDiffCallback()) {


    /**
     *this class receive a data as binding data, describe it adapt it
     * and update the recycle view
     *
     */
    class ViewHolder private  constructor(val binding: ListItemProfileBinding) : RecyclerView.ViewHolder(binding.root){

        /**
         * this method make the link between the data and correct object in the view
         */
        fun bind(item: Profile){
            binding.profile = item
            binding.executePendingBindings()
        }

        /**
         * this object import the correct link object in the view
         */
        companion object{
            fun from(parent: ViewGroup):ViewHolder{
                val layout = LayoutInflater.from(parent.context)
                val binding = ListItemProfileBinding.inflate(layout,parent,false)
                return ViewHolder(binding)
            }
        }
    }

    /**
     * this method get data and add it to recycle view
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
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
class ProfileDiffCallback : DiffUtil.ItemCallback<Profile>() {

    /**
     * this method check if the two data have the same id
     * return true if the two data are the same
     */
    override fun areItemsTheSame(oldItem: Profile, newItem: Profile): Boolean {
        return oldItem.userId == newItem.userId
    }

    /**
     * this method check if the two data content the same values
     */
    override fun areContentsTheSame(oldItem: Profile, newItem: Profile): Boolean {
        return oldItem==newItem
    }
}