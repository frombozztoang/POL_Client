package com.example.pieciesoflife.myPage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pieciesoflife.R


// BookAdapter에 ViewHolder를 같이 innerclass로 생성
// BookDater를 만들어 data를 view와 연결
// img를 쉽게 연결시켜주기 위해 Glide 사용

class BookAdapter(private val context: Context) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    var datas = mutableListOf<BookData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recycler_book,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtBookTitle: TextView = itemView.findViewById(R.id.book_title)
        private val txtBookDate: TextView = itemView.findViewById(R.id.book_date)
        private val txtBookContent: TextView = itemView.findViewById(R.id.book_date)
        private val imgBookPost: ImageView = itemView.findViewById(R.id.book_post)
        private val txtPostTitle : TextView = itemView.findViewById(R.id.book_postTitle)
        private val imgPostProfile : ImageView = itemView.findViewById(R.id.book_postprofile)
        private val txtPostUserName : TextView = itemView.findViewById(R.id.book_postUserName)

        fun bind(item: BookData) {
            txtBookTitle.text = item.title
            txtBookDate.text = item.date.toString()
            txtBookContent.text = item.content
            Glide.with(itemView).load(item.img).into(imgBookPost)
            txtPostTitle.text = item.postTitle
            Glide.with(itemView).load(item.img).into(imgPostProfile)
            txtPostUserName.text = item.userName

        }
    }


}