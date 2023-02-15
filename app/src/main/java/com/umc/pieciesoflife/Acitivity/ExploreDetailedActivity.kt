package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.umc.pieciesoflife.Adapter.BookDetailRVAdapter
import com.umc.pieciesoflife.DTO.StoryDto.*
import com.umc.pieciesoflife.GlobalApplication
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.Retrofit.RetrofitClient.storyService
import com.umc.pieciesoflife.databinding.ActivityExploreDetailedBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExploreDetailedActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityExploreDetailedBinding
    private var itemId = 0 // 호출한 특정 스토리 아이디
    private var myId = 0 // 내 id
    private var writerId = 0 // 작성자 id
    lateinit var likeData: StoryLikeData // 현재 isLiked 정보
    lateinit var requestData: StoryLikeData // 요청할 isLiked
    var likeNum : Int = 1 // 좋아요 개수

    //RV_Deatiled 리사이클러뷰
    private var bookDetailList: ArrayList<StoryDetailQna> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityExploreDetailedBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val bookDetailAdapter = BookDetailRVAdapter(bookDetailList)
        viewBinding.RVDetailed.adapter = bookDetailAdapter
        viewBinding.RVDetailed.layoutManager = LinearLayoutManager(this)

        itemId = intent.getIntExtra("id", 66) // 호출한 특정 스토리 아이디

        val jwtToken = GlobalApplication.prefs.getString("jwtToken", "default-value")
        storyService.getStoryDetail("Bearer $jwtToken", itemId)
            .enqueue(object : Callback<StoryDetail> {
                // 성공 처리
                override fun onResponse(call: Call<StoryDetail>, response: Response<StoryDetail>) {
                    if (response.isSuccessful) { // <--> response.code == 200
                        response.body()?.let {

                            bookDetailList = it.data.qnaList as ArrayList<StoryDetailQna>
                            bookDetailAdapter.addItems(bookDetailList)

                            viewBinding.tvTitleDetailed.text = it.data.story.title
                            viewBinding.tvNameDetailed.text = it.data.story.nickname
                            Picasso.get().load(it.data.story.profileImgUrl)
                                .into(viewBinding.imgProfile)
                            viewBinding.tvContent.text = it.data.story.description
                            viewBinding.tvDate.text = it.data.story.date.substring(0,10)

                            // 좋아요
                            likeNum = it.data.story.likeCnt
                            viewBinding.btnLikeDetailed.text = likeNum.toString()
                            likeData = StoryLikeData(it.data.story.liked) //서버 좋아요를 객체에 담음
                            viewBinding.btnLikeDetailed.isSelected = likeData.isLiked
                            requestData = StoryLikeData(!likeData.isLiked) // like API에 현재 상태의 반대를 보내야
                            itemId = it.data.story.id
                            myId = it.data.story.myId
                            writerId = it.data.story.writerId

                            // 배경색 설정
                            if (it.data.story.color == "#cdb5fa" || it.data.story.color =="#CDB5FA")
                                viewBinding.constraintLayout2.setBackgroundResource(R.drawable.color_gradient_purple)
                            else if (it.data.story.color == "9dc4f0" || it.data.story.color =="#9DC4F0")
                                viewBinding.constraintLayout2.setBackgroundResource(R.drawable.color_gradient_blue)
                            else if (it.data.story.color == "#f2acac"|| it.data.story.color =="#F2ACAC")
                                viewBinding.constraintLayout2.setBackgroundResource(R.drawable.color_gradient_peach)
                            else if (it.data.story.color == "#b9df98"|| it.data.story.color =="#B6DF98")
                                viewBinding.constraintLayout2.setBackgroundResource(R.drawable.color_gradient_green)
                            else if (it.data.story.color == "#f7d698"|| it.data.story.color =="#F7D698")
                                viewBinding.constraintLayout2.setBackgroundResource(R.drawable.color_gradient_yellow)
                            else if ( it.data.story.color == "#f8b2e8"|| it.data.story.color =="#F8B2E8")
                                viewBinding.constraintLayout2.setBackgroundResource(R.drawable.color_gradient_pink)
                            else if (it.data.story.color == "#90ded3"|| it.data.story.color =="#90DED3")
                                viewBinding.constraintLayout2.setBackgroundResource(R.drawable.color_gradient_mint)
                            else if (it.data.story.color == "#dfe07e" || it.data.story.color =="#DFE07E")
                                viewBinding.constraintLayout2.setBackgroundResource(R.drawable.color_gradient_lime)
                            else viewBinding.constraintLayout2.setBackgroundResource(R.drawable.color_gradient_peach)
                        }
                    }
                }

                override fun onFailure(call: Call<StoryDetail>, t: Throwable) {
                    // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                    Log.d(
                        "testttt", "ExploreDetailACtivity - onFailure 에러: " + t.message.toString()
                    )
                }
            })
        viewBinding.btnLikeDetailed.setOnClickListener {
            storyService.postStoryLike("Bearer $jwtToken", itemId, requestData).enqueue(
                object : Callback<StoryLike> {
                    // 성공 처리
                    override fun onResponse(call: Call<StoryLike>, response: Response<StoryLike>) {
                        if (response.isSuccessful) { // <--> response.code == 200
                            response.body()?.let { it ->
                                Log.d("storyLike", "${response.body()}")

                                if (!likeData.isLiked && requestData.isLiked) {
                                    likeNum++
                                    likeData.isLiked = true
                                    requestData.isLiked = !likeData.isLiked // 앞으로 요청할 상태
                                    viewBinding.btnLikeDetailed.isSelected = likeData.isLiked
                                    viewBinding.btnLikeDetailed.text = likeNum.toString()
                                }
                                else if (likeData.isLiked && !requestData.isLiked){
                                    likeNum--
                                    likeData.isLiked = false
                                    requestData.isLiked = !likeData.isLiked // 앞으로 요청할 상태
                                    viewBinding.btnLikeDetailed.isSelected = likeData.isLiked
                                    viewBinding.btnLikeDetailed.text = likeNum.toString()
                                }
                            }
                        }
                    }
                    override fun onFailure(call: Call<StoryLike>, t: Throwable) {
                        // 포스트 실패
                        Log.d("storyLike", "에러입니다. ${t.message}")
                        t.printStackTrace()
                    }
                }
            )
        }

        viewBinding.btnBack.setOnClickListener {
            // 뒤로가기 버튼
            finish()
        }

        viewBinding.btnSend.setOnClickListener {
            // 쪽지 보내기
            val intent = Intent(this, ChatSendActivity::class.java)
            intent.putExtra("id", itemId)
            intent.putExtra("myId", myId)
            intent.putExtra("writerId", writerId)
            Log.d("TEST", "쪽지 보내기 $itemId, $myId, $writerId")
            startActivity(intent)
        }
    }
}