package com.example.chat


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.example.chat.R


class MainActivity : AppCompatActivity() {
    private lateinit var recycleview: RecyclerView
    private lateinit var toi:TextView
    private lateinit var adapter: friendsadapter
    private lateinit var listfriends:ArrayList<user>
    private lateinit var database: DatabaseReference
    private lateinit var myauth: FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycleview=findViewById(R.id.recycleview)
        listfriends= ArrayList()
        toi=findViewById(R.id.toi)
        database= FirebaseDatabase.getInstance().getReference()
        myauth=FirebaseAuth.getInstance()
        adapter= friendsadapter(this,listfriends)
        recycleview.adapter=adapter
        recycleview.layoutManager= LinearLayoutManager(this)

        database.child("user").addValueEventListener(object: ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                listfriends.clear()
                for(i in snapshot.children)
                {var mess=i.getValue(user::class.java)
                    if(mess?.uid!=myauth.currentUser?.uid)
                    {
                        listfriends.add(mess!!)
                    }


                }
//                if(listfriends.size<=1)
//                {
//                    toi.setText("no friends for you in shadow_chat")
//                }
//                else
//                {
//                    toi.setText("these are the friends who are signed in shadow_chat")
//                }
                adapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId== R.id.logi)
        {
            FirebaseAuth.getInstance().signOut()
            var intent= Intent(this,loginmainclass::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}