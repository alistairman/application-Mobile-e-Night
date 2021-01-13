package com.example.enight.view.profile

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.enight.dataBase.profile.Profile
import com.example.enight.databinding.ListItemProfileBinding

class ProfileAdapter: ListAdapter<Profile, ProfileAdapter.ViewHolder>(ProfileDiffCallback()) {


    class ViewHolder private  constructor(val binding: ListItemProfileBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Profile){
            binding.profile = item
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup):ViewHolder{
                val layout = LayoutInflater.from(parent.context)
                val binding = ListItemProfileBinding.inflate(layout,parent,false)
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

class ProfileDiffCallback : DiffUtil.ItemCallback<Profile>() {
    override fun areItemsTheSame(oldItem: Profile, newItem: Profile): Boolean {
        return oldItem.userId == newItem.userId
    }

    override fun areContentsTheSame(oldItem: Profile, newItem: Profile): Boolean {
        return oldItem==newItem
    }
}