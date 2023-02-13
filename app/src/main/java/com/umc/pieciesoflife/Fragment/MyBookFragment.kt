package com.umc.pieciesoflife.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.pieciesoflife.Acitivity.MybookDetailedActivity
import com.umc.pieciesoflife.Acitivity.NotiActivity
import com.umc.pieciesoflife.Acitivity.StartNewstoryAcitivity
import com.umc.pieciesoflife.Adapter.StoryRVAdapter
import com.umc.pieciesoflife.BottomNavBar.BottomNavBarActivity
import com.umc.pieciesoflife.DTO.StoryDto.*
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.Retrofit.RetrofitClient.storyService
import com.umc.pieciesoflife.databinding.FragmentMybookBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyBookFragment : Fragment() {
    private lateinit var viewBinding: FragmentMybookBinding

    private lateinit var bookAdapter: StoryRVAdapter
    var bookList: ArrayList<StoryData> = arrayListOf()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMybookBinding.inflate(inflater, container, false)

        //리사이클러뷰 어댑터 설정
        bookAdapter = StoryRVAdapter(bookList)
        viewBinding.rvMybooks.adapter = bookAdapter
        viewBinding.rvMybooks.layoutManager = LinearLayoutManager(context)

        //태그 버튼 초기 설정은 날짜로
        viewBinding.btnDate.isSelected = true
        initRecycler(1)

        // 마이페이지
        viewBinding.btnProfile.setOnClickListener {
            val mActivity = activity as BottomNavBarActivity
            mActivity.binding.navigationView.selectedItemId = R.id.myPageFragment
        }

        //알림
        viewBinding.btnNoti.setOnClickListener {
            val intent = Intent(context, NotiActivity::class.java)
            startActivity(intent)
        }

        //이야기 새로 쓰기
        viewBinding.btnWrite.setOnClickListener {
            val intent = Intent(context, StartNewstoryAcitivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK // 새로운 tash로 시작
            startActivity(intent)
        }


        // 태그 버튼 '날짜' 클릭
        viewBinding.btnDate.setOnClickListener {
            setTagBtn(true,false, false,false,false,false,false,false)
            initRecycler(1)
        }

        // 태그 버튼 '나이' 클릭
        viewBinding.btnAge.setOnClickListener {
            setTagBtn(false,true, false,false,false,false,false,false)
            initRecycler(2)
        }

        // 태그 버튼 '감정' 클릭
        viewBinding.btnEmo.setOnClickListener {
            setTagBtn(false,false, true,false,false,false,false,false)
            initRecycler(3)
            // 변수 버튼 고유번호? 담고 그거 함수 변수 만들어서 기싸이클러 함수
        }

        // 태그 버튼 '사람' 클릭
        viewBinding.btnPeople.setOnClickListener {
            setTagBtn(false,false, false,true,false,false,false,false)
            initRecycler(4)
        }

        // 태그 버튼 '상황' 클릭
        viewBinding.btnSitu.setOnClickListener {
            setTagBtn(false,false, false,false,true,false,false,false)
            initRecycler(5)
        }

        // 태그 버튼 '장소' 클릭
        viewBinding.btnPlace.setOnClickListener {
            setTagBtn(false,false, false,false,false,true,false,false)
            initRecycler(6)
        }

        // 태그 버튼 '연도' 클릭
        viewBinding.btnYear.setOnClickListener {
            setTagBtn(false,false, false,false,false,false,true,false)
            initRecycler(7)
        }

        // 태그 버튼 '물건' 클릭
        viewBinding.btnObject.setOnClickListener {
            setTagBtn(false,false, false,false,false,false,false,true)
            initRecycler(8)
        }


        // -> 자서전 상세보기 .. 얘도 !!
        bookAdapter.setMyItemClickListener(object : StoryRVAdapter.MyItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(context, MybookDetailedActivity::class.java)
                startActivity(intent)
            }
        })

        return viewBinding.root
    }

    fun setTagBtn( btnDate: Boolean,btnAge:Boolean, btnEmo: Boolean, btnPeople: Boolean,btnSitu : Boolean, btnPlace:Boolean, btnYear: Boolean, btnObject:Boolean ) {
        viewBinding.btnDate.isSelected = btnDate
        viewBinding.btnAge.isSelected = btnAge
        viewBinding.btnEmo.isSelected = btnEmo
        viewBinding.btnPeople.isSelected = btnPeople
        viewBinding.btnSitu.isSelected = btnSitu
        viewBinding.btnPlace.isSelected = btnPlace
        viewBinding.btnYear.isSelected = btnYear
        viewBinding.btnObject.isSelected = btnObject
        bookAdapter.clear()
    }

   lateinit var storyTag: String

    private fun initRecycler(tagId: Int) {
//        var jwtToken = GlobalApplication.prefs.getString("jwtToken", "default-value")
        val JWTTOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiLsnbTrs7TtmIQiLCJuaWNrbmFtZSI6IuydtOuztO2YhCIsImlkIjoxNywiZXhwIjoxNjc3MDYwNzE5fQ.qVi4R1_Khq8ZW2FibwW1FEVrIm3cfZj_bxRWAMjIltmiEqpqbiAuRtKyB-9GlMOpUgev-vteTBKhlMiYRpdODg"
        storyService.getStoryFilter("application/json","Bearer $JWTTOKEN",tagId,0, 5,"").enqueue(object : Callback<Story> {
            // 성공 처리
            override fun onResponse(call: Call<Story>, response: Response<Story>) {
                if (response.isSuccessful) { // <--> response.code == 200
                    response.body()?.let {

                        bookList = it.dataList as ArrayList<StoryData>
                        bookAdapter.addItems(bookList)
                    }
                }
            }
            override fun onFailure(call: Call<Story>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("testtt", "onFailure 에러: " + t.message.toString());
            }
        })



    }}




