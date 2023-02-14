package com.umc.pieciesoflife.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.pieciesoflife.DTO.StoryDto.StoryDetailData
import com.umc.pieciesoflife.DTO.StoryDto.StoryDetailQna
import com.umc.pieciesoflife.DataClass.BookDetail
import com.umc.pieciesoflife.databinding.ItemBookDetailBinding

class BookDetailRVAdapter(private var bookDetailList: ArrayList<StoryDetailQna>): RecyclerView.Adapter<BookDetailRVAdapter.DataViewHolder>() {

    inner class DataViewHolder(private val viewBinding: ItemBookDetailBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(bookDetail: StoryDetailQna){
            viewBinding.contentTag.text = bookDetail.question
            viewBinding.contentQuestion.text = bookDetail.question
            viewBinding.contentAnswer.text = bookDetail.answer
            // 태그도 매핑해야 함 bookDetail.tagId
        }
    }

    //데이터 개별 추가
    fun addItem(bookDetail: StoryDetailQna){
        bookDetailList.add(bookDetail)
        notifyDataSetChanged()
    }

    //데이터 일괄 추가
    fun addItems(bookDetailList: ArrayList<StoryDetailQna>){
        this.bookDetailList = bookDetailList
        notifyDataSetChanged()
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