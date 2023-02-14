package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.umc.pieciesoflife.Adapter.BookDetailRVAdapter
import com.umc.pieciesoflife.DTO.StoryDto.StoryDetail
import com.umc.pieciesoflife.DTO.StoryDto.StoryDetailData
import com.umc.pieciesoflife.DTO.StoryDto.StoryDetailQna
import com.umc.pieciesoflife.DTO.StoryDto.StoryDetailStory
import com.umc.pieciesoflife.DataClass.BookDetail
import com.umc.pieciesoflife.Fragment.HomeFragment
import com.umc.pieciesoflife.GlobalApplication
import com.umc.pieciesoflife.Interface.StoryService
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.Retrofit.RetrofitClient
import com.umc.pieciesoflife.Retrofit.RetrofitClient.storyService
import com.umc.pieciesoflife.databinding.ActivityExploreDetailedBinding
import com.umc.pieciesoflife.databinding.ActivityMybookDetailedBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MybookDetailedActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMybookDetailedBinding
    private var itemId = 0 // 호출한 특정 스토리 아이디
    private var count : Int = 46 // 좋아요 수
    private var newColor : String? = "" // 자서전 배경색

    lateinit var storyDetailData : StoryDetailData
    private var bookDetailList: ArrayList<StoryDetailQna> = arrayListOf()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMybookDetailedBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val myBookDetailAdapter = BookDetailRVAdapter(bookDetailList)
        viewBinding.rvDetailed.adapter = myBookDetailAdapter
        viewBinding.rvDetailed.layoutManager = LinearLayoutManager(this)

        itemId = intent.getIntExtra("id", 86) // 호출한 특정 스토리 아이디

        // DTO 연결시도
        var jwtToken = GlobalApplication.prefs.getString("jwtToken", "default-value")
        storyService.getStoryDetail("Bearer $jwtToken",itemId).enqueue(object : Callback<StoryDetail> {
            // 성공 처리
            override fun onResponse(call: Call<StoryDetail>, response: Response<StoryDetail>) {
                if(response.isSuccessful) { // <--> response.code == 200
                    response.body()?.let {
                        var count = it.data.story.likeCnt
                        viewBinding.tvTitleDetailed.text = it.data.story.title
                        viewBinding.tvNameDetailed.text = it.data.story.nickname
                        Picasso.get().load(it.data.story.profileImgUrl).into(viewBinding.imgProfile)
                        viewBinding.tvContent.text = it.data.story.description
                        viewBinding.tvDate.text = it.data.story.date.substring(0,10)

                        // 좋아요 버튼 클릭 이벤트
                        viewBinding.btnLikeDetailed.text = it.data.story.likeCnt.toString()
                        viewBinding.btnLikeDetailed.isSelected = it.data.story.liked != true
                        viewBinding.btnLikeDetailed.setOnClickListener {
                            if (viewBinding.btnLikeDetailed.isSelected) {
                                count -= 1
                            }
                            else count -= 1
                        }

                        bookDetailList = it.data.qnaList as ArrayList<StoryDetailQna>
                        myBookDetailAdapter.addItems(bookDetailList)
                        Log.d("testtttt", "MyBookDetailACtivity:$bookDetailList")
                    }
                }
            }
            override fun onFailure(call: Call<StoryDetail>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("testttt", "MyBookDetailACtivity - onFailure 에러: " + t.message.toString());
            }
        }
        )

        viewBinding.btnBack.setOnClickListener {
            finish()
        }

        viewBinding.btnMenu.setOnClickListener {
            val intent = Intent(this, DialogBottomAcitivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("color", newColor)
            startActivity(intent)
        }

        // DialogColorActivity로부터 배경색 intent 받아와서 적용하기
        newColor = intent.getStringExtra("color")

        if (newColor == "purple")
            viewBinding.constraintLayout2.setBackgroundResource(R.drawable.color_gradient_purple)
        else if (newColor == "blue")
            viewBinding.constraintLayout2.setBackgroundResource(R.drawable.color_gradient_blue)
        else if (newColor == "peach")
            viewBinding.constraintLayout2.setBackgroundResource(R.drawable.color_gradient_peach)
        else if (newColor == "green")
            viewBinding.constraintLayout2.setBackgroundResource(R.drawable.color_gradient_green)
        else if (newColor == "yellow")
            viewBinding.constraintLayout2.setBackgroundResource(R.drawable.color_gradient_yellow)
        else if (newColor == "pink")
            viewBinding.constraintLayout2.setBackgroundResource(R.drawable.color_gradient_pink)
        else if (newColor == "mint")
            viewBinding.constraintLayout2.setBackgroundResource(R.drawable.color_gradient_mint)
        else if (newColor == "lime")
            viewBinding.constraintLayout2.setBackgroundResource(R.drawable.color_gradient_lime)

//
//        bookDetailList.apply{
//            add(StoryDetailQna("질문1", "답변1"))
//            add(StoryDetailQna("질문2", "답변2"))
//            add(StoryDetailQna("질문3", "답변3"))
//            add(StoryDetailQna("질문4", "답변4"))
//        }

    }

}