package com.umc.pieciesoflife.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.pieciesoflife.DataClass.BookDetail
import com.umc.pieciesoflife.databinding.ItemBookDetailBinding

class BookDetailRVAdapter(private val bookDetailList: ArrayList<BookDetail>): RecyclerView.Adapter<BookDetailRVAdapter.DataViewHolder>() {

    inner class DataViewHolder(private val viewBinding: ItemBookDetailBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(bookDetail: BookDetail){
            viewBinding.contentQuestion.text = bookDetail.question
            viewBinding.contentAnswer.text = bookDetail.answer
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding = ItemBookDetailBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return DataViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(bookDetailList[position])
    }

    override fun getItemCount(): Int = bookDetailList.size

}