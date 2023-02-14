package com.umc.pieciesoflife.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.pieciesoflife.Acitivity.ExploreDetailedActivity
import com.umc.pieciesoflife.Adapter.MyPageRVAdatper
import com.umc.pieciesoflife.Adapter.StoryRVAdapter
import com.umc.pieciesoflife.DTO.MyPageDto.MyPage
import com.umc.pieciesoflife.DTO.MyPageDto.MyPageStory
import com.umc.pieciesoflife.DTO.StoryDto.StoryData
import com.umc.pieciesoflife.GlobalApplication
import com.umc.pieciesoflife.Interface.MyPageService
import com.umc.pieciesoflife.Retrofit.RetrofitClient
import com.umc.pieciesoflife.databinding.FragmentUserBookBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserBookFragment : Fragment() {
    private lateinit var Binding: FragmentUserBookBinding
    private lateinit var myPageAdapter: MyPageRVAdatper   //RV어댑터 생성
    var bookList: ArrayList<MyPageStory> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Binding = FragmentUserBookBinding.inflate(inflater, container, false)
        initRecycler()
        myPageAdapter = MyPageRVAdatper(bookList)
        Binding.rvBook.adapter = myPageAdapter
        Binding.rvBook.layoutManager = LinearLayoutManager(context)

        // -> 자서전 상세보기 페이지(ExploreDetailedActivity) .. 우선은 걍 intent만 해놓음 서버 연결 후, 클릭된 자서전 내용으로 떠야됌
        myPageAdapter.setMyItemClickListener(object : MyPageRVAdatper.MyItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(context, ExploreDetailedActivity::class.java)
                startActivity(intent)
            }
        })

        return Binding.root
    }

    private fun initRecycler() {

        var jwtToken = GlobalApplication.prefs.getString("jwtToken", "default-value")

        val myPageCall: MyPageService = RetrofitClient.myPageService
        myPageCall.getMyPage("Bearer $jwtToken").enqueue(object : Callback<MyPage> {
            // 전송 실패
            override fun onFailure(call: Call<MyPage>, t: Throwable) {
                Log.d("마이페이지 조회 실패", t.message!!)
            }

            override fun onResponse(call: Call<MyPage>, response: Response<MyPage>) {
                response.body()?.let {
                    bookList = it.data.story as ArrayList<MyPageStory>
                    myPageAdapter.addItems(bookList)
                    Log.d("마이페이지 조회 성공", "${it.data.story}")
                } ?: Log.d("마이페이지 body null!!!", "몸통은 비었다.")
            }
        })


    }
}