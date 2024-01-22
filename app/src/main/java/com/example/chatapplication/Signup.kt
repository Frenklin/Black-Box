package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class Signup : AppCompatActivity() {

    // Declare UI elements
    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnSignup: Button

    // Firebase authentication and database instances
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        supportActionBar?.hide()

        // Initialize Firebase authentication
        mAuth =FirebaseAuth.getInstance()

        edtName = findViewById(R.id.edt_name)
        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_pass)
        btnSignup = findViewById(R.id.btnSignup)

        // Set a click listener for the Signup button
        btnSignup.setOnClickListener{
            // Get the name, email, and password from the input fields
            val name = edtName.text.toString()
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            // Call the signUp function with the provided credentials
            signUp(name,email,password)
        }

    }

    private fun signUp(name: String, email: String, password: String){
        //logic of creating user

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                   //code for jumping home
                    addUserToDatabase(name,email,mAuth.currentUser?.uid!!)
                    val intent = Intent(this@Signup,MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    // If fails, display a message to the user.
                    Toast.makeText(this@Signup, "Some error occured", Toast.LENGTH_SHORT).show()

                }
            }
    }
    // Function to add user information to the Firebase Realtime Database
    private fun addUserToDatabase(name: String, email: String, uid: String){
        // Initialize the database reference
        mDbRef = FirebaseDatabase.getInstance().getReference()
        // Add user information under 'user' node with the user's unique ID
        mDbRef.child("user").child(uid).setValue(User(name,email,uid))
    }

}