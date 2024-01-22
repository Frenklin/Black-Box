package com.example.chatapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.runtime.currentComposer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

// Adapter class for handling messages in a RecyclerView
class MessageAdapter(val context: Context, val messageList: ArrayList<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    // View type constants for sent and received messages
        val ITEM_RECEIVE = 1
        val ITEM_SENT = 2

    // Function to create a new ViewHolder based on the item view type
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        if (viewType == 1){
            //inflate recive
            val view: View = LayoutInflater.from(context).inflate(R.layout.recive, parent, false)
            return ReceiveViewHolder(view)
        }else{
            //inflate sent
            val view: View = LayoutInflater.from(context).inflate(R.layout.sent, parent, false)
            return SentViewHolder(view)
        }

    }
    // Function to bind data to the ViewHolder based on its type
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentMessage = messageList[position]

        if(holder.javaClass == SentViewHolder::class.java){

        // If the ViewHolder is of type SentViewHolder, bind sent message data
            val viewHolder = holder as SentViewHolder
            holder.sentMessage.text = currentMessage.message

        }else{
            // If the ViewHolder is of type ReceiveViewHolder, bind received message data
            val viewHolder = holder as ReceiveViewHolder
            holder.receiveMessage.text = currentMessage.message
        }
    }
    // Function to determine the item view type based on the sender's ID
    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]

        // Compare the sender's ID with the current user's ID to determine the view type
        if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
            return ITEM_SENT
        }else{
            return ITEM_RECEIVE
        }
    }
    // Function to get the total number of items in the adapter
    override fun getItemCount(): Int {
        return messageList.size
    }


    // ViewHolder class for sent messages
    class SentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val sentMessage = itemView.findViewById<TextView>(R.id.txt_sent_message)

    }
    // ViewHolder class for received messages
    class ReceiveViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val receiveMessage = itemView.findViewById<TextView>(R.id.txt_receive_message)
    }
}

