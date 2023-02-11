package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.umc.pieciesoflife.Adapter.BookDetailRVAdapter
import com.umc.pieciesoflife.DTO.StoryDto.StoryDetail
import com.umc.pieciesoflife.DTO.StoryDto.StoryDetailData
import com.umc.pieciesoflife.DataClass.BookDetail
import com.umc.pieciesoflife.Fragment.ExploreFragment
import com.umc.pieciesoflife.Interface.StoryService
import com.umc.pieciesoflife.Retrofit.RetrofitClient
import com.umc.pieciesoflife.databinding.ActivityExploreDetailedBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExploreDetailedActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityExploreDetailedBinding
    lateinit var storyDetailData : StoryDetailData
    private var count : Int = 46 // 좋아요 수

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

        // 좋아요 버튼 클릭 이벤트
        viewBinding.btnLikeDetailed.text = count.toString() // 좋아요 개수 서버에서 받아오기(나중에 구현)

        viewBinding.btnLikeDetailed.setOnClickListener {
            if ( viewBinding.btnLikeDetailed.isSelected == true )  count -= 1
            else count += 1
            viewBinding.btnLikeDetailed.isSelected = viewBinding.btnLikeDetailed.isSelected == false
            viewBinding.btnLikeDetailed.text = count.toString()
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

        // DTO 연결시도
        val call: StoryService = RetrofitClient.storyService
        call.getStoryDetail(1).enqueue(object : Callback<StoryDetail> {
            // 성공 처리
            override fun onResponse(call: Call<StoryDetail>, response: Response<StoryDetail>) {
                if(response.isSuccessful) { // <--> response.code == 200
                    response.body()?.let {
                        storyDetailData = it.data
                        Log.d("testttt", "ExploreDetailACtivity:$storyDetailData")
                    }
                }
            }
            override fun onFailure(call: Call<StoryDetail>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("testttt", "onFailure 에러: " + t.message.toString());
            }
        }
        )
        bookDetailAdapter.notifyDataSetChanged()
    }
}