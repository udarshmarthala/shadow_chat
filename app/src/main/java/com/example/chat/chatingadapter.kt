package com.example.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

var itemsent=1
var itemrecieve=2
class chatingadapter(var context: Context,var chatlist:ArrayList<chattextsclass>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class senderviewholder(itemView: View) :RecyclerView.ViewHolder(itemView)
    {
        var send=itemView.findViewById<TextView>(R.id.sendtext)
    }
    class recieverviewholder(itemView: View) :RecyclerView.ViewHolder(itemView)
    {
        var recieve=itemView.findViewById<TextView>(R.id.recievetext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType==1)
        {
            return senderviewholder(LayoutInflater.from(context).inflate(R.layout.send,parent,false))
        }
        else
        {
            return recieverviewholder(LayoutInflater.from(context).inflate(R.layout.recieve,parent,false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var currentso=chatlist[position]
        if(holder.javaClass==senderviewholder::class.java)
        {
var viewHolder=holder as senderviewholder
            holder.send.text=currentso.sendtext.toString()
        }
        else
        {
            var viewHolder=holder as recieverviewholder
            holder.recieve.text=currentso.sendtext.toString()
        }
    }

    override fun getItemCount(): Int {
       return chatlist.size
    }
private lateinit var myauth:FirebaseAuth
    override fun getItemViewType(position: Int): Int {
        myauth=FirebaseAuth.getInstance()
        var cu=chatlist[position]
        if(cu.uid==myauth.currentUser?.uid)
        {
            return itemsent
        }
        else
        {
            return itemrecieve
        }
    }
}