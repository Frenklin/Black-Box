package com.example.chatapplication

import android.content.Context
import android.content.Intent
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

// UserAdapter class is responsible for adapting the User data to a RecyclerView
class UserAdapter(val context: Context, val userList: ArrayList<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    // Function to create a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        // Inflate the layout for each user item in the RecyclerView
        val view: View = LayoutInflater.from(context).inflate(R.layout.user_layout, parent, false)
        return UserViewHolder(view)
    }
    // Function to bind data to the ViewHolder
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        // Get the current user from the user list
        val currentUser = userList[position]
        // Set the user's name in the TextView of the ViewHolder
        holder.textName.text = currentUser.name

        // Set a click listener for the user item in the RecyclerView
        holder.itemView.setOnClickListener(){
            // Create an intent to start the ChatActivity
            val intent = Intent(context,ChatActivity::class.java)

            // Pass user data (name and UID) to the ChatActivity
            intent.putExtra("name", currentUser.name )
            intent.putExtra("uid", currentUser.uid)

            // Start the ChatActivity with the provided intent
            context.startActivities(arrayOf(intent))
        }
    }
    // Function to get the total number of items in the adapter
    override fun getItemCount(): Int {
        return userList.size
    }
    // ViewHolder class for user items
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        // Declare UI element for the user's name
        val textName = itemView.findViewById<TextView>(R.id.txt_name)
    }
}