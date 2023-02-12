package com.umc.pieciesoflife.Fragment

import android.content.Intent
import android.os.Bundle
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
import com.umc.pieciesoflife.DTO.StoryDto.StoryData
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.databinding.FragmentMybookBinding


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
        dateRecycler()

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
            viewBinding.btnDate.isSelected = true
            viewBinding.btnAge.isSelected = false
            viewBinding.btnEmo.isSelected = false
            viewBinding.btnPeople.isSelected = false
            viewBinding.btnSitu.isSelected = false
            viewBinding.btnPlace.isSelected = false
            viewBinding.btnYear.isSelected = false
            viewBinding.btnObject.isSelected = false
            bookAdapter.clear()
            dateRecycler() // 날짜 리사이클러
        }

        // 태그 버튼 '나이' 클릭
        viewBinding.btnAge.setOnClickListener {
            viewBinding.btnDate.isSelected = false
            viewBinding.btnAge.isSelected = true
            viewBinding.btnEmo.isSelected = false
            viewBinding.btnPeople.isSelected = false
            viewBinding.btnSitu.isSelected = false
            viewBinding.btnPlace.isSelected = false
            viewBinding.btnYear.isSelected = false
            viewBinding.btnObject.isSelected = false
            bookAdapter.clear()
            ageRecycler()
        }

        // 태그 버튼 '감정' 클릭
        viewBinding.btnEmo.setOnClickListener {
            viewBinding.btnDate.isSelected = false
            viewBinding.btnAge.isSelected = false
            viewBinding.btnEmo.isSelected = true
            viewBinding.btnPeople.isSelected = false
            viewBinding.btnSitu.isSelected = false
            viewBinding.btnPlace.isSelected = false
            viewBinding.btnYear.isSelected = false
            viewBinding.btnObject.isSelected = false
            bookAdapter.clear()
            dateRecycler()
        }

        // 태그 버튼 '사람' 클릭
        viewBinding.btnPeople.setOnClickListener {
            viewBinding.btnDate.isSelected = false
            viewBinding.btnAge.isSelected = false
            viewBinding.btnEmo.isSelected = false
            viewBinding.btnPeople.isSelected = true
            viewBinding.btnSitu.isSelected = false
            viewBinding.btnPlace.isSelected = false
            viewBinding.btnYear.isSelected = false
            viewBinding.btnObject.isSelected = false
            bookAdapter.clear()
            dateRecycler()
        }

        // 태그 버튼 '상황' 클릭
        viewBinding.btnSitu.setOnClickListener {
            viewBinding.btnDate.isSelected = false
            viewBinding.btnAge.isSelected = false
            viewBinding.btnEmo.isSelected = false
            viewBinding.btnPeople.isSelected = false
            viewBinding.btnSitu.isSelected = true
            viewBinding.btnPlace.isSelected = false
            viewBinding.btnYear.isSelected = false
            viewBinding.btnObject.isSelected = false
            bookAdapter.clear()
            dateRecycler()
        }

        // 태그 버튼 '장소' 클릭
        viewBinding.btnPlace.setOnClickListener {
            viewBinding.btnDate.isSelected = false
            viewBinding.btnAge.isSelected = false
            viewBinding.btnEmo.isSelected = false
            viewBinding.btnPeople.isSelected = false
            viewBinding.btnSitu.isSelected = false
            viewBinding.btnPlace.isSelected = true
            viewBinding.btnYear.isSelected = false
            viewBinding.btnObject.isSelected = false
            bookAdapter.clear()
            dateRecycler()
        }

        // 태그 버튼 '연도' 클릭
        viewBinding.btnYear.setOnClickListener {
            viewBinding.btnDate.isSelected = false
            viewBinding.btnAge.isSelected = false
            viewBinding.btnEmo.isSelected = false
            viewBinding.btnPeople.isSelected = false
            viewBinding.btnSitu.isSelected = false
            viewBinding.btnPlace.isSelected = false
            viewBinding.btnYear.isSelected = true
            viewBinding.btnObject.isSelected = false
            bookAdapter.clear()
            dateRecycler()
        }

        // 태그 버튼 '물건' 클릭
        viewBinding.btnObject.setOnClickListener {
            viewBinding.btnDate.isSelected = false
            viewBinding.btnAge.isSelected = false
            viewBinding.btnEmo.isSelected = false
            viewBinding.btnPeople.isSelected = false
            viewBinding.btnSitu.isSelected = false
            viewBinding.btnPlace.isSelected = false
            viewBinding.btnYear.isSelected = false
            viewBinding.btnObject.isSelected = true
            bookAdapter.clear()
            dateRecycler()
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

    private fun dateRecycler() {
    /*
        //서버연결 후에는 bookList 통한 "bookAdapter.addItems(Book( ... ))" 이용하면 편함!!!!! - 지금껀 임시얀
        bookList.apply{
            add(
                Book(
                    profileImg = R.drawable.ic_flag_level2,
                    userName = "mary",
                    date = "2023.11.12",
                    title = "날짜 태그1",
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
                    title = "날짜 태그2",
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
                    title = "날짜 태그3",
                    content = "어쩔티비 ",
                    postTitle = "첫번째 하아",
                    postImg = R.drawable.ic_book
                )
            )
            bookAdapter.notifyDataSetChanged()
        }
        */
    }

    private fun ageRecycler() {
    /*
        //서버연결 후에는 bookList 통한 "bookAdapter.addItems(Book( ... ))" 이용하면 편함!!!!! - 지금껀 임시얀
        bookList.apply{
            add(
                Book(
                    profileImg = R.drawable.ic_flag_level2,
                    userName = "mary",
                    date = "2023.11.12",
                    title = "나이 태그1",
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
                    title = "나이 태그2",
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
                    title = "나이 태그3",
                    content = "어쩔티비 ",
                    postTitle = "첫번째 하아",
                    postImg = R.drawable.ic_book
                )
            )
            bookAdapter.notifyDataSetChanged()
        }

     */

    }

}


