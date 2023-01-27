package com.umc.pieciesoflife

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.pieciesoflife.databinding.ItemNotiBinding

class NotiRVAdapter(private val notiList: ArrayList<Noti>): RecyclerView.Adapter<NotiRVAdapter.DataViewHolder>(){

    inner class DataViewHolder(private val viewBinding: ItemNotiBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(notification: Noti){
            viewBinding.notiUserImg.setImageResource(notification.img)
            viewBinding.notiContent.text = notification.content
            viewBinding.notiTime.text = notification.time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding = ItemNotiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(notiList[position])
    }

    override fun getItemCount(): Int = notiList.size
}