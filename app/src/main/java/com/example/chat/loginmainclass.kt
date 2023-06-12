package com.example.chat

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class loginmainclass : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var login: Button
    private lateinit var signup: Button
    private lateinit var myauth: FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginmainclass)
        email=findViewById(R.id.email)
        password=findViewById(R.id.password)
        login=findViewById(R.id.login)
        signup=findViewById(R.id.signup)
        myauth= FirebaseAuth.getInstance()
        supportActionBar?.hide()
        signup.setOnClickListener{
            val intent= Intent(this@loginmainclass,signupmainclass::class.java)
            startActivity(intent)
            finish()
        }
        login.setOnClickListener{
            val em=email.text.toString()
            val pass=password.text.toString()
            myauth.signInWithEmailAndPassword(em, pass)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        var intent= Intent(this,MainActivity::class.java)

                        startActivity(intent)
                        finish()

                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this, "user is not created", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}