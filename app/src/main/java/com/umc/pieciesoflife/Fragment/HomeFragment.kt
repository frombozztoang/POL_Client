package com.umc.pieciesoflife.Fragment

import android.content.Intent
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.pieciesoflife.Acitivity.MybookDetailedActivity
import com.umc.pieciesoflife.Acitivity.NotiActivity
import com.umc.pieciesoflife.Adapter.BookRVAdapter
import com.umc.pieciesoflife.BottomNavBar.BottomNavBarActivity
import com.umc.pieciesoflife.DTO.StoryDto.StoryExplore
import com.umc.pieciesoflife.DTO.StoryDto.StoryExploreData
import com.umc.pieciesoflife.DTO.UserDto.User
import com.umc.pieciesoflife.DataClass.Book
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.Retrofit.RetrofitClient
import com.umc.pieciesoflife.Retrofit.RetrofitClient.userService
import com.umc.pieciesoflife.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private lateinit var viewBinding: FragmentHomeBinding
    private lateinit var bookAdapter: BookRVAdapter

    var bookList: ArrayList<StoryExploreData> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHomeBinding.inflate(inflater, container, false)

        bookAdapter = BookRVAdapter(bookList)
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

        userService.getUserInfo("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiLqs6DsirntnawiLCJuaWNrbmFtZSI6IuqzoOyKue2drCIsImlkIjo4LCJleHAiOjE2NzcwMTIwNTN9.bDZCi4jvhHNSoXonQupwuAb2GJPO-RPin5oqj59d3PDmmloiS7XcZTH4qDAcNhUXDNzqQ-P-GQISDVKbPsyMdQ").enqueue(object :
            Callback<User> {
            // 성공 처리
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) { // <--> response.code == 200
                    response.body()?.let {
                        val score = it.data.score
                        val level = it.data.level

                        // SeekBar 경험치(score)에 따라 움직이게 설정
                        viewBinding.seekBarReal.progress = score
                        viewBinding.seekBarText.progress = score
                        viewBinding.seekBarReal.isEnabled = false // 터치 불가
                        viewBinding.seekBarText.isEnabled = false

                        if ( score < 50) {
                            viewBinding.imgLv1.setImageResource(R.drawable.ic_flag)
                            viewBinding.imgLv2.setImageResource(R.drawable.ic_flag_gray)
                            viewBinding.imgLv3.setImageResource(R.drawable.ic_flag_gray)
                            viewBinding.seekBarText.thumb = resources.getDrawable(R.drawable.seekbar_thumb)
                            viewBinding.lv1.setTypeface(null, Typeface.BOLD)
                        } else if ( score == 50) {
                            viewBinding.imgLv1.setImageResource(R.drawable.ic_flag)
                            viewBinding.imgLv2.setImageResource(R.drawable.ic_flag_level2)
                            viewBinding.imgLv3.setImageResource(R.drawable.ic_flag_gray)
                            viewBinding.seekBarText.thumb = resources.getDrawable(R.drawable.ic_level_two_clear)
                            viewBinding.lv2.setTypeface(null, Typeface.BOLD)
                        } else if ( score < 100) {
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


/*
        bookList.apply {
            add(
                Book(
                    profileImg = R.drawable.ic_flag_level2,
                    userName = "mary",
                    date = "2023.11.12",
                    title = "대표이야기1",
                    content = "어쩔티비 ",
                    postTitle = "첫번째 하아",
                    postImg = R.drawable.ic_book
                )
            )
            add(
                Book(
                    profileImg = R.drawable.ic_flag_level2,
                    userName = "mary",
                    date = "2023.11.12",
                    title = "대표이야기2",
                    content = "어쩔티비 ",
                    postTitle = "두번째 하아",
                    postImg = R.drawable.ic_book
                )
            )
            add(
                Book(
                    profileImg = R.drawable.ic_flag_level2,
                    userName = "mary",
                    date = "2023.11.12",
                    title = "대표이야기3",
                    content = "어쩔티비 ",
                    postTitle = "하아...",
                    postImg = R.drawable.ic_book
                )
            )

            bookAdapter.notifyDataSetChanged()
        }

 */

        // -> 자서전 상세보기 .. 얘도 우선 걍 넘김
        bookAdapter.setMyItemClickListener(object : BookRVAdapter.MyItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(context, MybookDetailedActivity::class.java)
                startActivity(intent)
            }
        })

        return viewBinding.root
    }
}

