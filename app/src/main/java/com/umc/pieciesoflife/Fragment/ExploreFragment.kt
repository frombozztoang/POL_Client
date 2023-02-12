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
    var bookList : ArrayList<Book> = arrayListOf()
    lateinit var storyExplore: List<StoryExploreData>

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

        storyService.getStoryExplore(1,0,8,"like").enqueue(object : Callback<StoryExplore>{
            // 성공 처리
            override fun onResponse(call: Call<StoryExplore>, response: Response<StoryExplore>) {
                if(response.isSuccessful) { // <--> response.code == 200
                    response.body()?.let {
                        storyExplore = it.dataList
                        Log.d("testtt", "$storyExplore")
                    }
                }
            }
            override fun onFailure(call: Call<StoryExplore>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("testtt", "onFailure 에러: " + t.message.toString());
            }
        }



        )


//        val call: StoryService = RetrofitClient.storyService
//        call.getStoryExplore(1,1,8,"like").enqueue(object: Callback<StoryExplore> {
//            // 성공시
//            override fun onResponse(call: Call<StoryExplore>, response: Response<StoryExplore>) {
//                if (response.isSuccessful) { // <--> response.code == 200
//                    response.body()?.let {
//                        val responsed2 = it.data.storyList
//                        viewBinding.tvExplore.text = responsed2.toString()
//                        Log.d("testtt", "$responsed2")
//                    }
//                }
//            }
//            // 실패 처리
//            override fun onFailure(call: Call<StoryExplore>, t: Throwable) {
//                Log.d("testtt", "에러입니다. ${t.message}")
//                t.printStackTrace()
//            }
//        })

        return viewBinding.root
    }

    private fun newRecycler() {
        //서버연결 후에는 bookList 통한 "bookAdapter.addItems(Book( ... ))" 이용하면 편함!!!!! - 지금껀 임시얀
        bookList.apply{
            add(
                Book(
                    profileImg = R.drawable.ic_flag_level2,
                    userName = "mary",
                    date = "2023.11.12",
                    title = "최신순1",
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
                    title = "최신순2",
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
                    title = "최신순3",
                    content = "어쩔티비 ",
                    postTitle = "하아...",
                    postImg = R.drawable.ic_book
                )
            )
            add(
                Book(
                    profileImg = R.drawable.ic_flag_level2,
                    userName = "mary",
                    date = "2023.11.12",
                    title = "최신순4",
                    content = "어쩔티비 ",
                    postTitle = "하아...",
                    postImg = R.drawable.ic_book
                )
            )
            add(
                Book(
                    profileImg = R.drawable.ic_flag_level2,
                    userName = "mary",
                    date = "2023.11.12",
                    title = "최신순5",
                    content = "어쩔티비 ",
                    postTitle = "하아...",
                    postImg = R.drawable.ic_book
                )
            )
            add(
                Book(
                    profileImg = R.drawable.ic_flag_level2,
                    userName = "mary",
                    date = "2023.11.12",
                    title = "최신순6",
                    content = "어쩔티비 ",
                    postTitle = "하아...",
                    postImg = R.drawable.ic_book
                )
            )
            add(
                Book(
                    profileImg = R.drawable.ic_flag_level2,
                    userName = "mary",
                    date = "2023.11.12",
                    title = "최신순7",
                    content = "어쩔티비 ",
                    postTitle = "하아...",
                    postImg = R.drawable.ic_book
                )
            )
            add(
                Book(
                    profileImg = R.drawable.ic_flag_level2,
                    userName = "mary",
                    date = "2023.11.12",
                    title = "최신순8",
                    content = "어쩔티비 ",
                    postTitle = "하아...",
                    postImg = R.drawable.ic_book
                )
            )
            bookAdapter.notifyDataSetChanged()

        }
    }

    private fun likeRecycler() {
        bookList.apply{
            add(
                Book(
                    profileImg = R.drawable.ic_flag_level2,
                    userName = "mary",
                    date = "2023.11.12",
                    title = "인기순1",
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
                    title = "인기순2",
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
                    title = "인기순3",
                    content = "어쩔티비 ",
                    postTitle = "하아...",
                    postImg = R.drawable.ic_book
                )
            )
            add(
                Book(
                    profileImg = R.drawable.ic_flag_level2,
                    userName = "mary",
                    date = "2023.11.12",
                    title = "인기순4",
                    content = "어쩔티비 ",
                    postTitle = "하아...",
                    postImg = R.drawable.ic_book
                )
            )
            add(
                Book(
                    profileImg = R.drawable.ic_flag_level2,
                    userName = "mary",
                    date = "2023.11.12",
                    title = "인기순5",
                    content = "어쩔티비 ",
                    postTitle = "하아...",
                    postImg = R.drawable.ic_book
                )
            )
            add(
                Book(
                    profileImg = R.drawable.ic_flag_level2,
                    userName = "mary",
                    date = "2023.11.12",
                    title = "인기순6",
                    content = "인기티비 ",
                    postTitle = "하아...",
                    postImg = R.drawable.ic_book
                )
            )
            add(
                Book(
                    profileImg = R.drawable.ic_flag_level2,
                    userName = "mary",
                    date = "2023.11.12",
                    title = "인기순7",
                    content = "어쩔티비 ",
                    postTitle = "하아...",
                    postImg = R.drawable.ic_book
                )
            )
            add(
                Book(
                    profileImg = R.drawable.ic_flag_level2,
                    userName = "mary",
                    date = "2023.11.12",
                    title = "인기순8",
                    content = "어쩔티비 ",
                    postTitle = "하아...",
                    postImg = R.drawable.ic_book
                )
            )

            bookAdapter.notifyDataSetChanged()

        }
    }
}