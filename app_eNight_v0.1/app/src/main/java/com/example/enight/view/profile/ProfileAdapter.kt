package com.example.enight.view.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.enight.R
import com.example.enight.dataBase.profile.Profile

class ProfileAdapter: RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    var data = listOf<Profile>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val email: TextView = itemView.findViewById(R.id.email)
        val name: TextView = itemView.findViewById(R.id.name)
        val firstName: TextView = itemView.findViewById(R.id.firstName)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        //val res = holder.itemView.context.resources
        holder.email.text = item.mail
        holder.name.text = item.lastName
        holder.firstName.text = item.firstName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.list_item_profile, parent, false)
        return ViewHolder(view)
    }
}