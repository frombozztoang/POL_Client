package com.umc.pieciesoflife.Fragment

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.pieciesoflife.Acitivity.MybookDetailedActivity
import com.umc.pieciesoflife.Acitivity.NotiActivity
import com.umc.pieciesoflife.Adapter.StoryRVAdapter
import com.umc.pieciesoflife.BottomNavBar.BottomNavBarActivity
import com.umc.pieciesoflife.DTO.StoryDto.Story
import com.umc.pieciesoflife.DTO.StoryDto.StoryData
import com.umc.pieciesoflife.DTO.UserDto.User
import com.umc.pieciesoflife.GlobalApplication
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.Retrofit.RetrofitClient
import com.umc.pieciesoflife.Retrofit.RetrofitClient.storyService
import com.umc.pieciesoflife.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private lateinit var viewBinding: FragmentHomeBinding
    private lateinit var bookAdapter: StoryRVAdapter
    private var level = 0 // 레벨
    private var exp : Int = 0 // 경험치

    var bookList: ArrayList<StoryData> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHomeBinding.inflate(inflater, container, false)

        bookAdapter = StoryRVAdapter(bookList)
        viewBinding.rvMyTitles.adapter = bookAdapter
        viewBinding.rvMyTitles.layoutManager = LinearLayoutManager(context)


        viewBinding.btnProfile.setOnClickListener { // 마이페이지
            val mActivity = activity as BottomNavBarActivity
            mActivity.binding.navigationView.selectedItemId = R.id.myPageFragment
        }

        viewBinding.btnNoti.setOnClickListener { // 알림
            val intent = Intent(context, NotiActivity::class.java)
            startActivity(intent)
        }

        // 프로그레스 바 API 호출
        val jwtToken = GlobalApplication.prefs.getString("jwtToken", "default-value")
        RetrofitClient.userService.getUserInfo("Bearer $jwtToken").enqueue(object :
            Callback<User> {
            // 성공 처리
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) { // <--> response.code == 200
                    response.body()?.let {
                        val score = it.data.score
                        val level = it.data.level
                        viewBinding.tvName.text = it.data.nickname
                        viewBinding.tvName2.text = it.data.nickname
                        // SeekBar 경험치(score)에 따라 움직이게 설정
                        viewBinding.seekBarReal.progress = score
                        viewBinding.seekBarText.progress = score
                        viewBinding.seekBarReal.isEnabled = false // 터치 불가
                        viewBinding.seekBarText.isEnabled = false

                        if ( score < 100) {
                            viewBinding.imgLv1.setImageResource(R.drawable.ic_flag)
                            viewBinding.imgLv2.setImageResource(R.drawable.ic_flag_gray)
                            viewBinding.imgLv3.setImageResource(R.drawable.ic_flag_gray)
                            viewBinding.seekBarText.thumb = resources.getDrawable(R.drawable.seekbar_thumb)
                            viewBinding.lv1.setTypeface(null, Typeface.BOLD)
                        } else if ( score == 100) {
                            viewBinding.imgLv1.setImageResource(R.drawable.ic_flag)
                            viewBinding.imgLv2.setImageResource(R.drawable.ic_flag_level2)
                            viewBinding.imgLv3.setImageResource(R.drawable.ic_flag_gray)
                            viewBinding.seekBarText.thumb = resources.getDrawable(R.drawable.ic_level_two_clear)
                            viewBinding.lv2.setTypeface(null, Typeface.BOLD)
                        } else if ( score < 200) {
                            viewBinding.imgLv1.setImageResource(R.drawable.ic_flag)
                            viewBinding.imgLv2.setImageResource(R.drawable.ic_flag_level2)
                            viewBinding.imgLv3.setImageResource(R.drawable.ic_flag_gray)
                            viewBinding.seekBarText.thumb = resources.getDrawable(R.drawable.ic_level_two)
                            viewBinding.lv2.setTypeface(null, Typeface.BOLD)
                        } else {
                            viewBinding.imgLv1.setImageResource(R.drawable.ic_flag)
                            viewBinding.imgLv2.setImageResource(R.drawable.ic_flag_level2)
                            viewBinding.imgLv3.setImageResource(R.drawable.ic_flag_level3)
                            viewBinding.seekBarText.thumb = resources.getDrawable(R.drawable.ic_level_three_clear)
                            viewBinding.lv2.setTypeface(null, Typeface.BOLD)
                            viewBinding.lv3.setTypeface(null, Typeface.BOLD)
                        }
                        Log.d("testtt", "$score")
                    }
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("testtt", "onFailure 에러: " + t.message.toString());
            }
        }
        )


        // 사용자 대표 이야기 리사이클러뷰
        storyService.getStoryHome("application/json","Bearer $jwtToken",1,0,3,"recent").enqueue(object :
            retrofit2.Callback<Story> {
            // 성공 처리
            override fun onResponse(call: Call<Story>, response: Response<Story>) {
                if (response.isSuccessful) { // <--> response.code == 200
                    response.body()?.let {
                        bookList = it.dataList as ArrayList<StoryData>
                        bookAdapter.addItems(bookList)
                        Log.d("testtt", "$bookList")
                    }
                }
            }

            override fun onFailure(call: Call<Story>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("testtt", "onFailure 에러: " + t.message.toString());
            }
        }
        )

        // -> 자서전 상세보기 .. 얘도 우선 걍 넘김
        bookAdapter.setMyItemClickListener(object : StoryRVAdapter.MyItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(context, MybookDetailedActivity::class.java)
                intent.putExtra("id", bookList[position].id)
                startActivity(intent)
            }
        })

        return viewBinding.root
    }
}
