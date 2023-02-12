package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import com.umc.pieciesoflife.Adapter.BookDetailRVAdapter
import com.umc.pieciesoflife.DTO.StoryDto.*
import com.umc.pieciesoflife.Fragment.ExploreFragment
import com.umc.pieciesoflife.GlobalApplication
import com.umc.pieciesoflife.Interface.StoryService
import com.umc.pieciesoflife.Retrofit.RetrofitClient
import com.umc.pieciesoflife.Retrofit.RetrofitClient.storyService
import com.umc.pieciesoflife.databinding.ActivityExploreDetailedBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExploreDetailedActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityExploreDetailedBinding
    lateinit var storyDetailStory : StoryDetailStory
    //RV_Deatiled 리사이클러뷰
    private var bookDetailList: ArrayList<StoryDetailQna> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityExploreDetailedBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val bookDetailAdapter = BookDetailRVAdapter(bookDetailList)
        viewBinding.RVDetailed.adapter = bookDetailAdapter
        viewBinding.RVDetailed.layoutManager = LinearLayoutManager(this)

        var jwtToken = GlobalApplication.prefs.getString("jwtToken", "default-value")
        storyService.getStoryDetail("Bearer $jwtToken",1).enqueue(object : Callback<StoryDetail> {
            // 성공 처리
            override fun onResponse(call: Call<StoryDetail>, response: Response<StoryDetail>) {
                if(response.isSuccessful) { // <--> response.code == 200
                    response.body()?.let {
                        var count  = it.data.story.likeCnt
                        var isLiked = it.data.story.liked
                        viewBinding.tvTitleDetailed.text = it.data.story.title
                        viewBinding.tvNameDetailed.text = it.data.story.nickname
                        Picasso.get().load(it.data.story.profileImgUrl).into(viewBinding.imgProfile)
                        viewBinding.tvContent.text = it.data.story.description
                        viewBinding.tvDate.text = it.data.story.date

                        // 좋아요 버튼 클릭 이벤트
                        viewBinding.btnLikeDetailed.text = it.data.story.likeCnt.toString()
                        viewBinding.btnLikeDetailed.isSelected = it.data.story.liked != true
                        viewBinding.btnLikeDetailed.setOnClickListener {
                            if (viewBinding.btnLikeDetailed.isSelected) {
                                count -= 1
                                !viewBinding.btnLikeDetailed.isSelected
                            }
                            else {
                                count += 1
                                !viewBinding.btnLikeDetailed.isSelected
                            }
                        }

                        bookDetailList = it.data.qnaList as ArrayList<StoryDetailQna>
                        bookDetailAdapter.addItems(bookDetailList)
                        Log.d("testttt", "ExploreDetailACtivity:$bookDetailList")
                    }
                }
            }
            override fun onFailure(call: Call<StoryDetail>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("testttt", "ExploreDetailACtivity - onFailure 에러: " + t.message.toString());
            }
        }
        )

        viewBinding.btnBack.setOnClickListener{ // 뒤로가기 버튼
            finish()
        }
        viewBinding.btnSend.setOnClickListener{ // 쪽지 보내기
            val intent = Intent(this, ChatSendActivity::class.java)
            startActivity(intent)
            //이 떄, Spring Server에서 Story ID GET해서 ->ChatSendAcitivity로 값 전달
        }

        bookDetailAdapter.notifyDataSetChanged()
    }
}