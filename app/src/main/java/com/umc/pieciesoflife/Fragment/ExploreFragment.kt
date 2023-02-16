package com.umc.pieciesoflife.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.umc.pieciesoflife.Acitivity.ExploreDetailedActivity
import com.umc.pieciesoflife.Acitivity.NotiActivity
import com.umc.pieciesoflife.Adapter.StoryRVAdapter
import com.umc.pieciesoflife.BottomNavBar.BottomNavBarActivity
import com.umc.pieciesoflife.DTO.StoryDto.Story
import com.umc.pieciesoflife.DTO.StoryDto.StoryData
import com.umc.pieciesoflife.Interface.StoryService
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.Retrofit.RetrofitClient.storyService
import com.umc.pieciesoflife.databinding.FragmentExploreBinding
import com.umc.pieciesoflife.scrollPercent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


class ExploreFragment : Fragment() {
    private lateinit var viewBinding: FragmentExploreBinding
    private lateinit var bookAdapter: StoryRVAdapter
    private lateinit var mRecyclerView: RecyclerView
    var bookList : ArrayList<StoryData> = arrayListOf()
    private var totalCount = 0 // 전체 아이템 개수
    private var isNext = false // 다음 페이지 유무
    private var page = 1       // 현재 페이지
    private var size = 40    // 한 번에 가져올 아이템 수
    private var cursorId = 0 // 마지막에 가져온 아이템 아이디
    var like : Boolean = true


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentExploreBinding.inflate(inflater, container, false)

        //리사이클러뷰 어댑터 설정
        bookAdapter = StoryRVAdapter(bookList)
        viewBinding.rvExplore.adapter = bookAdapter
        // viewBinding.rvExplore.setHasFixedSize(true)
        viewBinding.rvExplore.layoutManager = LinearLayoutManager(context)

//        // 네스티드 스크롤뷰
//        viewBinding.scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
//            fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                if(scrollPercent(binding.myRecyclerView) >= 100) {
//                    //dataLoading()
//                }
//            }
//        })
        //초기 버튼 및 정렬 설정은 최신순으로
        viewBinding.btnRecent.isSelected = true
        newRecycler()

        viewBinding.rvExplore.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (scrollPercent(viewBinding.rvExplore) >= 100) {
                    newRecycler()
//                    cursorId = cursorId + size
//                    //dataLoading()
//                    if (viewBinding.btnRecent.isSelected) {
//                        newRecycler()
//                        Log.d("제발", "onScrolled: new")
//                    }
//                    else {
//                        likeRecycler()
//                        Log.d("제발", "onScrolled: like")
//                    }
                }

//                        //최신순 정렬
//                        viewBinding.btnRecent.setOnClickListener {
//                            cursorId = 0
//                            viewBinding.btnRecent.isSelected = true
//                            viewBinding.btnLike.isSelected = false
//                            bookAdapter.clear()
//
//                        }
//
//                        //인기순 정렬
//                        viewBinding.btnLike.setOnClickListener {
//                            cursorId = 0
//                            viewBinding.btnLike.isSelected = true
//                            viewBinding.btnRecent.isSelected = false
//                            bookAdapter.clear()
//                            if (scrollPercent(viewBinding.rvExplore) >= 100) {
//                                likeRecycler()
//                            }
//                        }
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
            cursorId = 0
            viewBinding.btnRecent.isSelected = true
            viewBinding.btnLike.isSelected = false
            like = true
            bookAdapter.clear()
            newRecycler()
        }

        //인기순 정렬
        viewBinding.btnLike.setOnClickListener {
            cursorId = 0
            viewBinding.btnLike.isSelected = true
            viewBinding.btnRecent.isSelected = false
            like = false
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

// addOnScrollListener
//viewBinding.rvExplore.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//        super.onScrolled(recyclerView, dx, dy)
//        if (viewBinding.rvExplore.layoutManager != null &&
//            (viewBinding.rvExplore.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() == bookList.lastIndex - 1) {
//            if (MORE_LOADING && !LOADING) // 추가로 로딩할 게시판이 있는지, 현재 로딩 중인지를 체크하는 변수
//            {
//                LOADING = true // 현재 로딩 중, 게시글을 불러오면 false로 바꿔줍니다
//                rvExplore.post {
//                    boardAdapter.showProgressBar() // recyclerView에 ProgressBar를 띄우는 메서드, 게시글을 다 불러오면 progressbar를 지워줍니다.
//                    newRecycler() //추가 데이터 로드
//                }
//            }
//        }

//    private fun setData(){
//        for (i in 0 until 8){
//            items.add(bookList[i])
//        }
//    }



//    // 2. scroll이 끝에 닿으면 데이터에 null 추가 및 Adapter에 알림
//    private fun initScrollListener(){
//        viewBinding.rvExplore.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//
//                if(!isLoading){
//                    if ((recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition() == size - 1){
//                        Log.e("true", "True")
//                        moreItems()
//                        isLoading =  true
//                    }
//
//                }
//            }
//        })
//    }

//    //3. null 제거 후 새로운 데이터 추가 및 Adapter에 알림
//    fun moreItems(){
//        mRecyclerView = viewBinding.rvExplore
//
//        val runnable = Runnable {
//            bookList.add(null)
//            bookAdapter.notifyItemInserted(bookList.size -1)
//        }
//        mRecyclerView.post(runnable)
//
//        CoroutineScope(mainDispatcher).launch {
//            delay(2000)
//            val runnable2=  Runnable{
//                bookList.removeAt(size - 1)
//                val scrollPosition = size
//                bookAdapter.notifyItemRemoved(scrollPosition)
//                var currentSize = scrollPosition
//                var nextLimit = currentSize+10
//                Log.e("hello", "${nextLimit}")
//                if (currentSize < size-10){
//                    while (currentSize-1<nextLimit){
//                        bookList.add(bookList[currentSize])
//                        currentSize++
//                    }
//                }else{
//                    while (currentSize!=size){
//                        bookList.add(bookList[currentSize])
//                        currentSize++
//                    }
//                }
//                bookAdapter.updateItem(bookList)
//                isLoading = false
//            }
//            runnable2.run()
//        }
//    }


//    private fun parseResult(jsonArray: JSONArray) {
//        for (i in 0 until jsonArray.length()) {
//            try {
//                val jsonObject = jsonArray.getJSONObject(i)
//                book = StoryData
//                bookList.add(book)
//            } catch (e: JSONException) {
//                e.printStackTrace()
//            }
//            adapter = MainAdapter(this@MainActivity, dataArrayList)
//            recyclerView.setAdapter(adapter)
//        }
//    }

    // 최신순
    private fun newRecycler() {
        storyService.getStoryExplore(cursorId,0,size,"recent").enqueue(object : Callback<Story> {
            // 성공 처리
            override fun onResponse(call: Call<Story>, response: Response<Story>) {
                if (response.isSuccessful) { // <--> response.code == 200
                    response.body()?.let {
//                        viewBinding.progressBar.visibility = View.GONE
                        bookList = it.dataList as ArrayList<StoryData>
                        bookAdapter.addItems(bookList)
                        Log.d("제발", "$bookList")
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
        storyService.getStoryExplore(cursorId,0, size,"like").enqueue(object : Callback<Story> {
            // 성공 처리
            override fun onResponse(call: Call<Story>, response: Response<Story>) {
                if (response.isSuccessful) { // <--> response.code == 200
                    response.body()?.let {
                        bookList = it.dataList as ArrayList<StoryData>
                        bookAdapter.addItems(bookList)
                        Log.d("제발", "$bookList")
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