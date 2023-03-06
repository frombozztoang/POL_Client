package com.umc.pieciesoflife.Adapter


import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.umc.pieciesoflife.DTO.StoryDto.StoryData
import com.umc.pieciesoflife.databinding.ItemBookBinding



//import android.graphics.Color
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.squareup.picasso.Picasso
//import com.umc.pieciesoflife.DTO.StoryDto.StoryData
//import com.umc.pieciesoflife.databinding.ItemBookBinding

class StoryRVAdapter(private var bookList: ArrayList<StoryData>): RecyclerView.Adapter<StoryRVAdapter.DataViewHolder>() {

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

        fun bind(book: StoryData){
            viewBining.bookTitle.text = book.title
            viewBining.bookDate.text = book.createdDate.substring(0,10)
            viewBining.bookContent.text = book.description
            viewBining.bookPost.setImageResource(com.umc.pieciesoflife.R.drawable.ic_book)
            viewBining.bookPost.setColorFilter(Color.parseColor(book.color)) // ("book.color")
            viewBining.bookPostTitle.text = book.title
            if(book.profileImgUrl != null) {
                Picasso.get().load(book.profileImgUrl).into(viewBining.bookPostProfile)
            } else {
                viewBining.bookPostProfile.setImageResource(com.umc.pieciesoflife.R.drawable.ic_pol)
            }
            viewBining.bookPostUserName.text = book.nickname
        }
    }

    //데이터 개별 추가
    fun addItem(book: StoryData){
        bookList.add(book)
        notifyDataSetChanged()
    }

    //데이터 일괄 추가
    fun addItems(bookList: ArrayList<StoryData>){
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
//class StoryRVAdapter(private var bookList: ArrayList<StoryData>): RecyclerView.Adapter<StoryRVAdapter.DataViewHolder>() {
//
//    private var context: Context? = null
//    private var unFilteredList = bookList
//    private var filteredList = bookList
//
//    //클릭 interface 정의
//    interface MyItemClickListener{
//        fun onItemClick(position: Int)
//    }
//    //클릭 리스너 선언
//    private lateinit var mItemClickListner: MyItemClickListener
//    //클릭 리스너 등록
//    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
//        mItemClickListner = itemClickListener
//    }
//
//
//    // View와 Data 연결 함수
//    inner class DataViewHolder(private val viewBinding: ItemBookBinding): RecyclerView.ViewHolder(viewBinding.root) {
//        init{
//            //itemView의 OnItemClick 상속 및 초기화
//            itemView.setOnClickListener {
//                mItemClickListner.onItemClick(adapterPosition)
//            }
//        }
//
//        fun bind(book: StoryData){
//            viewBinding.bookTitle.text = book.title
//            viewBinding.bookDate.text = book.createdDate.substring(0,10)
//            viewBinding.bookContent.text = book.description
//            viewBinding.bookPost.setImageResource(com.umc.pieciesoflife.R.drawable.ic_book)
//            viewBinding.bookPost.setColorFilter(Color.parseColor("#000000")) // ("book.color")
//            viewBinding.bookPostTitle.text = book.title
//            if(book.profileImgUrl != null) {
//                Picasso.get().load(book.profileImgUrl).into(viewBinding.bookPostProfile)
//            } else {
//                viewBinding.bookPostProfile.setImageResource(com.umc.pieciesoflife.R.drawable.ic_pol)
//            }
//            viewBinding.bookPostUserName.text = book.nickname
//        }
//    }
//
//
//    //데이터 개별 추가
//    fun addItem(book: StoryData){
//        bookList.add(book)
//        notifyDataSetChanged()
//    }
//
//    //데이터 일괄 추가
//    fun addItems(bookList: ArrayList<StoryData>){
//        this.bookList = bookList
//        notifyDataSetChanged()
//    }
//
//    //데이터 개별 삭제
//    fun removeItem(position: Int){
//        bookList.removeAt(position)
//        notifyItemRemoved(position)
//    }
//
//    //데이터 일괄 삭제
//    fun clear(){
//        bookList.clear()
//        notifyDataSetChanged()
//    }
//
//    // 뷰 홀더가 처음 생성될 때
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
//            val viewBinding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//            return DataViewHolder(viewBinding)
//
//    }
//
//    // 재활용 및 값을 넣어주는 곳
//    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
//            val item = filteredList?.get(position)
//            holder.bind(item!!)
//    }
//
//
//
//    override fun getItemCount(): Int {
//        return if (filteredList == null){
//            0
//        }else{
//            filteredList?.size!!
//        }
//    }
//
//    fun updateItem(list:ArrayList<StoryData>){
//        this.filteredList = list
//    }
//
//}