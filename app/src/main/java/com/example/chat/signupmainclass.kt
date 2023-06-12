package com.example.chat

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class signupmainclass : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var name: EditText
    private lateinit var signup: Button
    private lateinit var myauth: FirebaseAuth
    private lateinit var database: DatabaseReference
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signupmainclass)
        email=findViewById(R.id.emailso)
        password=findViewById(R.id.passwordso)
        name=findViewById(R.id.nameso)
        signup=findViewById(R.id.signupso)
        myauth= FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance().getReference()
        signup.setOnClickListener{
            val em=email.text.toString()
            val pass=password.text.toString()
            val na=name.text.toString()
            if(pass.length>6 && na.length>6) {

                myauth.createUserWithEmailAndPassword(em, pass)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            database.child("user").child(myauth.uid!!)
                                .setValue(user(em, na, myauth.uid!!))
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(this, "error occured", Toast.LENGTH_SHORT).show()
                        }
                    }


            }
            else
            {
                Toast.makeText(this, "length of password or name should be greater than six", Toast.LENGTH_SHORT).show()
            }
        }
    }
}