package com.umc.pieciesoflife.Adapter

import android.graphics.Color
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
            if (bookDetail.question.contains('[')) { // 태그의 시작을 포함하고 있다면
                val strData = bookDetail.question
                val strArr = strData.split("[","]")
                val str = "#" +  strArr[1]
                viewBinding.contentTag.text = str
                viewBinding.contentQuestion.text = strArr[2]

                if (bookDetail.tagId == 1) viewBinding.contentTag.setTextColor(Color.parseColor("#6B9A85"))
                else if (bookDetail.tagId == 2) viewBinding.contentTag.setTextColor(Color.parseColor("#A56E4E"))
                else if (bookDetail.tagId == 3) viewBinding.contentTag.setTextColor(Color.parseColor("#567DA7"))
                else if (bookDetail.tagId == 4) viewBinding.contentTag.setTextColor(Color.parseColor("#5F8D8D"))
                else if (bookDetail.tagId == 5) viewBinding.contentTag.setTextColor(Color.parseColor("#93666F"))
                else if (bookDetail.tagId == 6) viewBinding.contentTag.setTextColor(Color.parseColor("#8D8565"))
                else if (bookDetail.tagId == 7) viewBinding.contentTag.setTextColor(Color.parseColor("#9467A3"))
                else viewBinding.contentTag.setTextColor(Color.parseColor("#000000"))
                // viewBinding.contentQuestion.setTextColor() <- Activity에서 진행
            }
           else {
               viewBinding.contentTag.text = ""
               viewBinding.contentQuestion.text = bookDetail.question
            }
            viewBinding.contentAnswer.text = bookDetail.answer

//            viewBinding.contentTag.text = bookDetail.question
//            viewBinding.contentQuestion.text = bookDetail.question
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