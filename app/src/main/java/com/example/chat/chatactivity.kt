package com.example.chat

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.example.chat.R


class chatactivity : AppCompatActivity() {
    private lateinit var recyclerview: RecyclerView
    private lateinit var textchat: EditText
    private lateinit var sendtext: ImageView
    private lateinit var adapter: chatingadapter
    private lateinit var arralist:ArrayList<chattextsclass>
    private lateinit var myauth: FirebaseAuth
    private lateinit var database: DatabaseReference
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatactivity)
        recyclerview=findViewById(R.id.recycleviewso)
        textchat=findViewById(R.id.chattexto)
        sendtext=findViewById(R.id.sendtexto)
        myauth=FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance().getReference()
        arralist= ArrayList()
        var na=intent.getStringExtra("name")
        var uio=intent.getStringExtra("uid")
        var sendclass=uio+myauth.currentUser?.uid
        var reciverclass=myauth.currentUser?.uid+uio
        adapter=chatingadapter(this,arralist)
        recyclerview.layoutManager= LinearLayoutManager(this)
        recyclerview.adapter=adapter



        database.child("chats").child(sendclass).child("message").addValueEventListener(object:
            ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                arralist.clear()
                for(postsnapshot in snapshot.children)
                {
                    var messoo=postsnapshot.getValue(chattextsclass::class.java)
                    arralist.add(messoo!!)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        sendtext.setOnClickListener{
            var message=textchat.text.toString()
            var mess=chattextsclass(message,myauth.currentUser?.uid)





//            writing sendclass as well as reciever class is very important


            database.child("chats").child(sendclass).child("message").push().setValue(mess).addOnSuccessListener {
                database.child("chats").child(reciverclass).child("message").push().setValue(mess)


            }
            textchat.setText("")
        }
    }
}