package com.example.chat

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class friendsadapter(var context: Context,var friends:ArrayList<user>):RecyclerView.Adapter<friendsadapter.myviewholder>() {
    class myviewholder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var crfriend:TextView=itemView.findViewById(R.id.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        return myviewholder(LayoutInflater.from(context).inflate(R.layout.friends,parent,false))
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        var current=friends[position]
        holder.crfriend.text=current.name.toString()
        holder.crfriend.setOnClickListener{
            var intent=Intent(context, chatactivity::class.java)
            intent.putExtra("name",current.name)
            intent.putExtra("uid",current.uid)
            context.startActivity(intent)


        }
        }

    override fun getItemCount(): Int {

        return friends.size

}
}

//
