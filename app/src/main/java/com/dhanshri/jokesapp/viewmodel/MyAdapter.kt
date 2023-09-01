package com.dhanshri.jokesapp.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dhanshri.jokesapp.R
import com.dhanshri.jokesapp.model.Meme

class MyAdapter(private val mList: List<Meme>, private val onNameClick: (Meme)-> Unit): RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemView = mList[position]

//        holder.imageView.setImageResource(ItemView.url)
        holder.name.text = ItemView.name

        holder.name.setOnClickListener {
            onNameClick(ItemView)
        }

        Glide.with(holder.imageView.context)
            .load(ItemView.url)
            .into(holder.imageView)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val name : TextView = itemView.findViewById(R.id.textView)

    }


}