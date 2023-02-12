package com.umc.pieciesoflife.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.pieciesoflife.Acitivity.ExploreDetailedActivity
import com.umc.pieciesoflife.Acitivity.NotiActivity
import com.umc.pieciesoflife.Adapter.BookRVAdapter
import com.umc.pieciesoflife.BottomNavBar.BottomNavBarActivity
import com.umc.pieciesoflife.DTO.StoryDto.StoryExplore
import com.umc.pieciesoflife.DTO.StoryDto.StoryExploreData
import com.umc.pieciesoflife.DataClass.Book
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.Retrofit.RetrofitClient.storyService
import com.umc.pieciesoflife.databinding.FragmentExploreBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExploreFragment : Fragment() {
    private lateinit var viewBinding: FragmentExploreBinding
    private lateinit var bookAdapter: BookRVAdapter
    var bookList : ArrayList<StoryExploreData> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentExploreBinding.inflate(inflater, container, false)

        //리사이클러뷰 어댑터 설정
        bookAdapter = BookRVAdapter(bookList)
        viewBinding.rvExplore.adapter = bookAdapter
        viewBinding.rvExplore.layoutManager = LinearLayoutManager(context)

        //초기 버튼 및 정렬 설정은 최신순으로
        viewBinding.btnRecent.isSelected = true
        newRecycler()

        //마이페이지
        viewBinding.btnProfile.setOnClickListener {
            val mActivity = activity as BottomNavBarActivity
            mActivity.binding.navigationView.selectedItemId = R.id.myPageFragment
        }

        //알림
        viewBinding.btnNoti.setOnClickListener {
            val intent = Intent(context, NotiActivity::class.java)
            startActivity(intent)
        }

        //최신순 정렬
        viewBinding.btnRecent.setOnClickListener {
            viewBinding.btnRecent.isSelected = true
            viewBinding.btnLike.isSelected = false
            bookAdapter.clear()
            newRecycler()
        }

        //인기순 정렬
        viewBinding.btnLike.setOnClickListener {
            viewBinding.btnLike.isSelected = true
            viewBinding.btnRecent.isSelected = false
            bookAdapter.clear()
            likeRecycler()
        }

        // -> 자서전 상세보기 페이지(ExploreDetailedActivity) .. 우선은 걍 intent만 해놓음 서버 연결 후, 클릭된 자서전 내용으로 떠야됌
        bookAdapter.setMyItemClickListener(object : BookRVAdapter.MyItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(context, ExploreDetailedActivity::class.java)
                startActivity(intent)
            }
        })

        return viewBinding.root
    }

    // 최신순
    private fun newRecycler() {
        storyService.getStoryExplore(1,0,8,"recent").enqueue(object : Callback<StoryExplore> {
            // 성공 처리
            override fun onResponse(call: Call<StoryExplore>, response: Response<StoryExplore>) {
                if (response.isSuccessful) { // <--> response.code == 200
                    response.body()?.let {
                        bookList = it.dataList as ArrayList<StoryExploreData>
                        bookAdapter.addItems(bookList)
                        Log.d("testtt", "$bookList")
                    }
                }
            }

            override fun onFailure(call: Call<StoryExplore>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("testtt", "onFailure 에러: " + t.message.toString());
            }
        }
        )

    }

    // 인기순
    private fun likeRecycler() {
        storyService.getStoryExplore(1,0,8,"like").enqueue(object : Callback<StoryExplore> {
            // 성공 처리
            override fun onResponse(call: Call<StoryExplore>, response: Response<StoryExplore>) {
                if (response.isSuccessful) { // <--> response.code == 200
                    response.body()?.let {
                        bookList = it.dataList as ArrayList<StoryExploreData>
                        bookAdapter.addItems(bookList)
                        Log.d("testtt", "$bookList")
                    }
                }
            }

            override fun onFailure(call: Call<StoryExplore>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("testtt", "onFailure 에러: " + t.message.toString());
            }
        }
        )
    }
}
