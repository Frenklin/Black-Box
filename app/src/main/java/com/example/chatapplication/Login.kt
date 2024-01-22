package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import java.net.PasswordAuthentication

class Login : AppCompatActivity() {

    // Declare UI elements
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignup: Button

    // Firebase authentication instance
    private lateinit var mAuth: FirebaseAuth

    // Function called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the layout for the activity
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        // Initialize Firebase authentication
        mAuth =FirebaseAuth.getInstance()

        // Initialize UI elements
        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_pass)
        btnLogin = findViewById(R.id.btnLogin)
        btnSignup = findViewById(R.id.btnSignup)

        // Set a click listener for the Signup button
        btnSignup.setOnClickListener{
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }

        // Set a click listener for the Login button
        btnLogin.setOnClickListener{
            // Get the email and password from the input fields
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            login(email,password);
        }
    }

    private fun login(email: String, password: String){
        //logic of loggin user

        // Authenticate user using Firebase
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // for logging user

                    val intent = Intent(this@Login,MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    // If fails, display a message to the user.

                    Toast.makeText(this@Login, "User does not exist", Toast.LENGTH_SHORT).show()

                }
            }
    }

}