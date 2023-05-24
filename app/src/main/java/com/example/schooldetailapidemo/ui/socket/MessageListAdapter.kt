package com.example.schooldetailapidemo.ui.socket

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.schooldetailapidemo.databinding.ItemMessageListBinding
import com.example.schooldetailapidemo.databinding.ItemReceiveMessageListBinding
import com.example.schooldetailapidemo.ui.model.ChatHistoryModel


class MessageListAdapter(
    var context: Context,
    private var chatHistoryModel: ArrayList<ChatHistoryModel.Data>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val view_type_send = 0
        private const val view_type_received = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == view_type_send) {
            val sendData =
                ItemMessageListBinding.inflate(LayoutInflater.from(context), parent, false)
            SendDataViewHolder(sendData)
        } else {
            val receiveData =
                ItemReceiveMessageListBinding.inflate(LayoutInflater.from(context), parent, false)
            ReceivedDataViewHolder(receiveData)
        }
    }
    override fun getItemCount(): Int=chatHistoryModel.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SendDataViewHolder){
            val datetime = chatHistoryModel[position].createdAt
            val time = datetime.substring(11, 16)
            holder.binding.tvItem.text = chatHistoryModel[position].message
            holder.binding.tvTime.text = time


        }else if (holder is ReceivedDataViewHolder){
            val datetime = chatHistoryModel[position].createdAt
            val time = datetime.substring(11, 16)
            holder.binding.tvItem.text  = chatHistoryModel[position].message
            holder.binding.tvTime.text = time

        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (chatHistoryModel[position].status=="sent") {
            view_type_send
        }
        else{
            view_type_received
        }
    }
    inner class SendDataViewHolder(var binding: ItemMessageListBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class ReceivedDataViewHolder(var binding: ItemReceiveMessageListBinding) :
        RecyclerView.ViewHolder(binding.root)
}