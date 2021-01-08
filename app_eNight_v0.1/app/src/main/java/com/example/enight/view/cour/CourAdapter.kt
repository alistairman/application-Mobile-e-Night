package com.example.enight.view.cour

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.enight.R
import com.example.enight.dataBase.cour.Cour

class CourAdapter: RecyclerView.Adapter<CourAdapter.ViewHolder>() {

    var data = listOf<Cour>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        //val cour: TextView = itemView.findViewById(R.id.cour)
        //val nbCredit: TextView = itemView.findViewById(R.id.nbCredit)
        //val valided: TextView = itemView.findViewById(R.id.valided)
    }

    override fun getItemCount() = data.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    /**override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        //val res = holder.itemView.context.resources
        holder.cour.text = item.cour
        holder.nbCredit.text = item.etuNo.toString()
        holder.valided.text = item.valided.toString()
    }*/

    /*override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.list_item_cours, parent, false)
        return ViewHolder(view)
    }*/
}