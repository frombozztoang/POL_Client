package com.umc.pieciesoflife.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.umc.pieciesoflife.R
/*
class MessageRVAdapter(private val context: Context) : RecyclerView.Adapter<MessageRVAdapter.ViewHolder>() {

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


    // 데이터 저장하는 변수
    var datas = mutableListOf<Message>()

    // 뷰 홀더가 처음 생성될 때
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message,parent,false)
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
        init{
            //itemView의 OnItemClick 상속 및 초기화
            itemView.setOnClickListener {
                mItemClickListner.onItemClick(adapterPosition)
            }
        }

        private val imgUserProfile: ImageView = itemView.findViewById(R.id.msg_userImg)
        private val txtUserName: TextView = itemView.findViewById(R.id.msg_userName)
        private val txtDate: TextView = itemView.findViewById(R.id.msg_date)
        private val txtContent: TextView = itemView.findViewById(R.id.msg_content)

        fun bind(item: Message) {
            imgUserProfile.setImageResource(item.userImg)
            txtUserName.text=item.userName
            txtDate.text=item.date
            txtContent.text =item.content
        }
    }
}

 */