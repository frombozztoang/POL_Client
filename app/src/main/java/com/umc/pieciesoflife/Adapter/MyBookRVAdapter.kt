package com.umc.pieciesoflife.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.umc.pieciesoflife.DTO.StoryDto.StoryExploreData
import com.umc.pieciesoflife.DTO.StoryDto.StoryFilterStory
import com.umc.pieciesoflife.databinding.ItemBookBinding

class MyBookRVAdapter (private var bookList: ArrayList<StoryExploreData>): RecyclerView.Adapter<MyBookRVAdapter.DataViewHolder>() {

    //클릭 interface 정의
    interface MyItemClickListener{
        fun onItemClick(position: Int)
    }
    //클릭 리스너 선언
    private lateinit var mItemClickListner: MyItemClickListener
    //클릭 리스너 등록
    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListner = itemClickListener
    }


    // View와 Data 연결 함수
    inner class DataViewHolder(private val viewBinding: ItemBookBinding): RecyclerView.ViewHolder(viewBinding.root) {
        init{
            //itemView의 OnItemClick 상속 및 초기화
            itemView.setOnClickListener {
                mItemClickListner.onItemClick(adapterPosition)
            }
        }

        fun bind(book: StoryExploreData){
            viewBinding.bookTitle.text = book.title
            viewBinding.bookDate.text = book.createdDate
            viewBinding.bookContent.text = book.description
            viewBinding.bookPost.setImageResource(com.umc.pieciesoflife.R.drawable.ic_book)
            viewBinding.bookPost.setColorFilter(Color.parseColor("#000000")) // ("book.color")
            viewBinding.bookPostTitle.text = book.title
            if(book.profileImgUrl != null) {
                Picasso.get().load(book.profileImgUrl).into(viewBinding.bookPostProfile)
            } else {
                viewBinding.bookPostProfile.setImageResource(com.umc.pieciesoflife.R.drawable.ic_pol)
            }
            viewBinding.bookPostUserName.text = book.nickname
        }
    }

    //데이터 개별 추가
    fun addItem(book: StoryExploreData){
        bookList.add(book)
        notifyDataSetChanged()
    }

    //데이터 일괄 추가
    fun addItems(bookList: ArrayList<StoryExploreData>){
        this.bookList = bookList
        notifyDataSetChanged()
    }

    //데이터 개별 삭제
    fun removeItem(position: Int){
        bookList.removeAt(position)
        notifyItemRemoved(position)
    }

    //데이터 일괄 삭제
    fun clear(){
        bookList.clear()
        notifyDataSetChanged()
    }

    // 뷰 홀더가 처음 생성될 때
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBining = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(viewBining)
    }

    // 재활용 및 값을 넣어주는 곳
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(bookList[position])
    }

    override fun getItemCount(): Int = bookList.size
}