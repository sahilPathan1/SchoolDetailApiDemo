package com.example.schooldetailapidemo.ui.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.schooldetailapidemo.R
import com.example.schooldetailapidemo.databinding.ItemChatListBinding
import com.example.schooldetailapidemo.ui.activity.ChatActivity
import com.example.schooldetailapidemo.ui.model.ChatListModel

class ChatListAdapter(val context: Context, val chatList: ArrayList<ChatListModel.Data>) :
    RecyclerView.Adapter<ChatListAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemChatListBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemChatListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ChatListAdapter.ViewHolder, position: Int) {
       val list = chatList[position]
       var image  =  Glide.with(context)
            .load(list.profilePicture)
           .placeholder(R.drawable.ic_launcher_background)
            .into(holder.binding.chatImage)

        holder.binding.tvChatName.text = list.name
        holder.binding.tvMessage.text = list.message
        holder.binding.tvTime.text = list.createdAgo

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("Image",image.toString())
            intent.putExtra("Name",list.name)
            intent.putExtra("receiverId",list.id.toString())
            Log.d("receiver_id-------------------------------- adapter", "" + list.id.toString())
            context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return chatList.size
    }
}

