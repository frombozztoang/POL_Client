package com.umc.pieciesoflife.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.pieciesoflife.DTO.ChatDTO.Chat
import com.umc.pieciesoflife.databinding.ItemChatBinding
import com.umc.pieciesoflife.databinding.ItemNotiBinding
import java.util.ArrayList

class ChatRVAdapter(private val chatList : ArrayList<Chat>): RecyclerView.Adapter<ChatRVAdapter.ViewHolder>(){

    inner class ViewHolder (private val viewBinding: ItemChatBinding): RecyclerView.ViewHolder(viewBinding.root){
        fun bind(chat: Chat){
            viewBinding.msgStatus.text = chat.stauts
            viewBinding.msgContent.text = chat.content
            viewBinding.msgDate.text = chat.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(chatList[position])
    }

    override fun getItemCount(): Int = chatList.size

}