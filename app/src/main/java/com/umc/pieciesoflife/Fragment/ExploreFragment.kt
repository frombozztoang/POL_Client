package com.umc.pieciesoflife.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.umc.pieciesoflife.Acitivity.ExploreDetailedActivity
import com.umc.pieciesoflife.Acitivity.NotiActivity
import com.umc.pieciesoflife.Adapter.StoryRVAdapter
import com.umc.pieciesoflife.BottomNavBar.BottomNavBarActivity
import com.umc.pieciesoflife.DTO.StoryDto.Story
import com.umc.pieciesoflife.DTO.StoryDto.StoryData
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.Retrofit.RetrofitClient
import com.umc.pieciesoflife.Retrofit.RetrofitClient.storyService
import com.umc.pieciesoflife.databinding.FragmentExploreBinding
import com.umc.pieciesoflife.scrollPercent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExploreFragment : Fragment() {
    private lateinit var viewBinding: FragmentExploreBinding
    private lateinit var bookAdapter: StoryRVAdapter
    var bookList : ArrayList<StoryData> = arrayListOf()
    private var totalCount = 0 // 전체 아이템 개수
    private var isNext = false // 다음 페이지 유무
    private var page = 0       // 현재 페이지
    private var size = 8     // 한 번에 가져올 아이템 수
    private var cursorId = 0 // 마지막에 가져온 아이템 아이디

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentExploreBinding.inflate(inflater, container, false)

        //리사이클러뷰 어댑터 설정
        bookAdapter = StoryRVAdapter(bookList)
        viewBinding.rvExplore.adapter = bookAdapter
        viewBinding.rvExplore.setHasFixedSize(true)
        viewBinding.rvExplore.layoutManager = LinearLayoutManager(context)

        //초기 버튼 및 정렬 설정은 최신순으로
        viewBinding.btnRecent.isSelected = true
        newRecycler()

        viewBinding.rvExplore.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(scrollPercent(viewBinding.rvExplore) >= 100) {
                    //dataLoading()
                    likeRecycler()

                }
            }
        })

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
        bookAdapter.setMyItemClickListener(object : StoryRVAdapter.MyItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(context, ExploreDetailedActivity::class.java)
                intent.putExtra("id", bookList[position].id)
                startActivity(intent)
            }
        })

        return viewBinding.root
    }

    // 최신순
    private fun newRecycler() {
        storyService.getStoryExplore(cursorId,0,size,"recent").enqueue(object : Callback<Story> {
            // 성공 처리
            override fun onResponse(call: Call<Story>, response: Response<Story>) {
                if (response.isSuccessful) { // <--> response.code == 200
                    response.body()?.let {

                        bookList = it.dataList as ArrayList<StoryData>
                        bookAdapter.addItems(bookList)

                        Log.d("testtt", "$bookList")
                        cursorId += size
                    }
                }
            }
            override fun onFailure(call: Call<Story>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("testtt", "onFailure 에러: " + t.message.toString());
            }
        }
        )
    }



    // 인기순
    private fun likeRecycler() {
        storyService.getStoryExplore(0,0,size,"like").enqueue(object : Callback<Story> {
            // 성공 처리
            override fun onResponse(call: Call<Story>, response: Response<Story>) {
                if (response.isSuccessful) { // <--> response.code == 200
                    response.body()?.let {
                        bookList = it.dataList as ArrayList<StoryData>
                        bookAdapter.addItems(bookList)
                        Log.d("testtt", "$bookList")
                        cursorId += size
                    }
                }
            }

            override fun onFailure(call: Call<Story>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("testtt", "onFailure 에러: " + t.message.toString());
            }
        }
        )
    }


}