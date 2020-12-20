package com.example.enight

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.enight.dataBase.email.Email

class NightShopAdapter: RecyclerView.Adapter<NightShopAdapter.ViewHolder>(){

    var data  = listOf<Email>()
        set(value) {
            field=value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount() = data.size

    class ViewHolder private constructor(itemView : View) : RecyclerView.ViewHolder(itemView){
        //val mail: TextView = itemView.findViewById(R.id.email_editText_profile)
        //val time: TextView = itemView.findViewById(R.id)

        fun bind(item : Email){
            //mail.text = item.mail
            //time.text = item.time
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.list_item_users, parent, false)
                return ViewHolder(view)
            }
        }
    }
}