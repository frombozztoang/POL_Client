package com.umc.pieciesoflife.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umc.pieciesoflife.DataClass.Book
import com.umc.pieciesoflife.R


// BookAdapter에 ViewHolder를 같이 innerclass로 생성
// BookDate를 만들어 data를 view와 연결
// img를 쉽게 연결시켜주기 위해 Glide 사용

class BookAdapter(private val context: Context) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    // 데이터 저장하는 변수
    var datas = mutableListOf<Book>()

    // 뷰 홀더가 처음 생성될 때
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book,parent,false)
        return ViewHolder(view)
    }

    // 리스트의 개수
    override fun getItemCount(): Int = datas.size

    // 재활용 및 값을 넣어주는 곳
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }


    // View와 Data를 연결시키는 함수
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val txtBookTitle: TextView = itemView.findViewById(R.id.book_title)
        private val txtBookDate: TextView = itemView.findViewById(R.id.book_date)
        private val txtBookContent: TextView = itemView.findViewById(R.id.book_date)
        private val imgBookPost: ImageView = itemView.findViewById(R.id.book_post)
        private val txtPostTitle : TextView = itemView.findViewById(R.id.book_postTitle)
        private val imgPostProfile : ImageView = itemView.findViewById(R.id.book_postProfile)
        private val txtPostUserName : TextView = itemView.findViewById(R.id.book_postUserName)

        fun bind(item: Book) {
            txtBookTitle.text = item.title
            txtBookDate.text = item.date
            txtBookContent.text = item.content
            imgBookPost.setImageResource(item.postImg)
            txtPostTitle.text = item.postTitle
            imgPostProfile.setImageResource(item.img)
            txtPostUserName.text = item.userName
        }
    }
}