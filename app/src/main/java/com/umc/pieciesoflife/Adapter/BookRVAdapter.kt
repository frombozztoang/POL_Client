package com.umc.pieciesoflife.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.pieciesoflife.DataClass.Book
import com.umc.pieciesoflife.databinding.ItemBookBinding

class BookRVAdapter(private val bookList: ArrayList<Book>): RecyclerView.Adapter<BookRVAdapter.DataViewHolder>() {

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
    inner class DataViewHolder(private val viewBining: ItemBookBinding): RecyclerView.ViewHolder(viewBining.root) {
        init{
            //itemView의 OnItemClick 상속 및 초기화
            itemView.setOnClickListener {
                mItemClickListner.onItemClick(adapterPosition)
            }
        }

        fun bind(book: Book){
            viewBining.bookTitle.text = book.title
            viewBining.bookDate.text = book.date
            viewBining.bookContent.text = book.content
            viewBining.bookPost.setImageResource(book.postImg)
            viewBining.bookPostTitle.text = book.postTitle
            viewBining.bookPostProfile.setImageResource(book.profileImg)
            viewBining.bookPostUserName.text = book.userName
        }
    }

    //데이터 개별 추가
    fun addItem(book: Book){
        bookList.add(book)
        notifyDataSetChanged()
    }

    //데이터 일괄 추가
    fun addItems(bookList: ArrayList<Book>){
        //this.bookList = bookList
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