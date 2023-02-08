package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.umc.pieciesoflife.Adapter.BookDetailRVAdapter
import com.umc.pieciesoflife.DataClass.BookDetail
import com.umc.pieciesoflife.Fragment.ExploreFragment
import com.umc.pieciesoflife.databinding.ActivityExploreDetailedBinding

class ExploreDetailedActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityExploreDetailedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityExploreDetailedBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnBack.setOnClickListener{ // 뒤로가기 버튼
            finish()
        }
        viewBinding.btnSend.setOnClickListener{ // 쪽지 보내기
            val intent = Intent(this, ChatSendActivity::class.java)
            startActivity(intent)
            //이 떄, Spring Server에서 Story ID GET해서 ->ChatSendAcitivity로 값 전달
        }
        viewBinding.btnLikeDetailed.setOnClickListener {
            viewBinding.btnLikeDetailed.isSelected = viewBinding.btnLikeDetailed.isSelected == false
        }

        //RV_Deatiled 리사이클러뷰
        val bookDetailList: ArrayList<BookDetail> = arrayListOf()

        bookDetailList.apply{
            add(BookDetail("질문1", "답변1"))
            add(BookDetail("질문2", "답변2"))
            add(BookDetail("질문3", "답변3"))
            add(BookDetail("질문4", "답변4"))
        }

        val bookDetailAdapter = BookDetailRVAdapter(bookDetailList)

        viewBinding.RVDetailed.adapter = bookDetailAdapter
        viewBinding.RVDetailed.layoutManager = LinearLayoutManager(this)

        bookDetailAdapter.notifyDataSetChanged()

    }
}