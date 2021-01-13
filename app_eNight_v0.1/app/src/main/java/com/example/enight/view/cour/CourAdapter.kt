package com.example.enight.view.cour

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.enight.R
import com.example.enight.dataBase.cour.Cour
import com.example.enight.dataBase.profile.Profile
import com.example.enight.databinding.ListItemCourBinding
import com.example.enight.databinding.ListItemProfileBinding
import com.example.enight.view.profile.ProfileAdapter

class CourAdapter: ListAdapter<Cour, CourAdapter.ViewHolder>(CourDiffCallback()){


    class ViewHolder private  constructor(val binding: ListItemCourBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Cour){
            binding.cour = item
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup):ViewHolder{
                val layout = LayoutInflater.from(parent.context)
                val binding = ListItemCourBinding.inflate(layout,parent,false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
}

class CourDiffCallback : DiffUtil.ItemCallback<Cour>() {
    override fun areItemsTheSame(oldItem: Cour, newItem: Cour): Boolean {
        return oldItem.cour == newItem.cour
    }

    override fun areContentsTheSame(oldItem: Cour, newItem: Cour): Boolean {
        return oldItem==newItem
    }
}