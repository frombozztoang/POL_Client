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
import com.umc.pieciesoflife.Adapter.BookRVAdapter
import com.umc.pieciesoflife.BottomNavBar.BottomNavBarActivity
import com.umc.pieciesoflife.DataClass.Book
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.databinding.FragmentMybookBinding


class MyBookFragment : Fragment() {
    private lateinit var viewBinding: FragmentMybookBinding
    private lateinit var bookAdapter: BookRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMybookBinding.inflate(inflater, container, false)

        //태그 버튼 초기 설정은 전체로
        viewBinding.btnAll.isSelected = true

        // 마이페이지
        viewBinding.btnProfile.setOnClickListener {
            val mActivity = activity as BottomNavBarActivity
            mActivity.changeFragment(UserFragment())
        }

        //알림
        viewBinding.btnNoti.setOnClickListener {
            val intent = Intent(context, NotiActivity::class.java)
            startActivity(intent)
        }

        //이어서 쓰기
        viewBinding.btnWrite.setOnClickListener {
            val intent = Intent(context, StartNewstoryAcitivity::class.java)
            startActivity(intent)
        }

        // 태그 버튼 '전체' 클릭
        viewBinding.btnAll.setOnClickListener {
            viewBinding.btnAll.isSelected = true
            viewBinding.btnAge.isSelected = false
            viewBinding.btnEmo.isSelected = false
            viewBinding.btnPeople.isSelected = false
            viewBinding.btnSitu.isSelected = false
            viewBinding.btnPlace.isSelected = false
            viewBinding.btnYear.isSelected = false
            viewBinding.btnObject.isSelected = false
        }

        // 태그 버튼 '나이' 클릭
        viewBinding.btnAge.setOnClickListener {
            viewBinding.btnAll.isSelected = false
            viewBinding.btnAge.isSelected = true
            viewBinding.btnEmo.isSelected = false
            viewBinding.btnPeople.isSelected = false
            viewBinding.btnSitu.isSelected = false
            viewBinding.btnPlace.isSelected = false
            viewBinding.btnYear.isSelected = false
            viewBinding.btnObject.isSelected = false
        }

        // 태그 버튼 '감정' 클릭
        viewBinding.btnEmo.setOnClickListener {
            viewBinding.btnAll.isSelected = false
            viewBinding.btnAge.isSelected = false
            viewBinding.btnEmo.isSelected = true
            viewBinding.btnPeople.isSelected = false
            viewBinding.btnSitu.isSelected = false
            viewBinding.btnPlace.isSelected = false
            viewBinding.btnYear.isSelected = false
            viewBinding.btnObject.isSelected = false
        }

        // 태그 버튼 '사람' 클릭
        viewBinding.btnPeople.setOnClickListener {
            viewBinding.btnAll.isSelected = false
            viewBinding.btnAge.isSelected = false
            viewBinding.btnEmo.isSelected = false
            viewBinding.btnPeople.isSelected = true
            viewBinding.btnSitu.isSelected = false
            viewBinding.btnPlace.isSelected = false
            viewBinding.btnYear.isSelected = false
            viewBinding.btnObject.isSelected = false
        }

        // 태그 버튼 '상황' 클릭
        viewBinding.btnAge.setOnClickListener {
            viewBinding.btnAll.isSelected = false
            viewBinding.btnAge.isSelected = false
            viewBinding.btnEmo.isSelected = false
            viewBinding.btnPeople.isSelected = false
            viewBinding.btnSitu.isSelected = true
            viewBinding.btnPlace.isSelected = false
            viewBinding.btnYear.isSelected = false
            viewBinding.btnObject.isSelected = false
        }

        // 태그 버튼 '장소' 클릭
        viewBinding.btnPlace.setOnClickListener {
            viewBinding.btnAll.isSelected = false
            viewBinding.btnAge.isSelected = false
            viewBinding.btnEmo.isSelected = false
            viewBinding.btnPeople.isSelected = false
            viewBinding.btnSitu.isSelected = false
            viewBinding.btnPlace.isSelected = true
            viewBinding.btnYear.isSelected = false
            viewBinding.btnObject.isSelected = false
        }

        // 태그 버튼 '연도' 클릭
        viewBinding.btnYear.setOnClickListener {
            viewBinding.btnAll.isSelected = false
            viewBinding.btnAge.isSelected = false
            viewBinding.btnEmo.isSelected = false
            viewBinding.btnPeople.isSelected = false
            viewBinding.btnSitu.isSelected = false
            viewBinding.btnPlace.isSelected = false
            viewBinding.btnYear.isSelected = true
            viewBinding.btnObject.isSelected = false
        }

        // 태그 버튼 '물건' 클릭
        viewBinding.btnObject.setOnClickListener {
            viewBinding.btnAll.isSelected = false
            viewBinding.btnAge.isSelected = false
            viewBinding.btnEmo.isSelected = false
            viewBinding.btnPeople.isSelected = false
            viewBinding.btnSitu.isSelected = false
            viewBinding.btnPlace.isSelected = false
            viewBinding.btnYear.isSelected = false
            viewBinding.btnObject.isSelected = true
        }




        //내 자서전 RV
        var bookList: ArrayList<Book> = arrayListOf()

        bookList.apply {
            add(
                Book(
                    profileImg = R.drawable.ic_flag_level2,
                    userName = "mary",
                    date = "2023.11.12",
                    title = "내 이야기1",
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
                    title = "내 이야기2",
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
                    title = "내 이야기3",
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
                    title = "내 이야기4",
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
                    title = "내 이야기5",
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
                    title = "내 이야기6",
                    content = "어쩔티비 ",
                    postTitle = "하아...",
                    postImg = R.drawable.ic_book
                )
            )

            bookAdapter = BookRVAdapter(bookList)

            viewBinding.rvMybooks.adapter = bookAdapter
            viewBinding.rvMybooks.layoutManager = LinearLayoutManager(context)

            bookAdapter.notifyDataSetChanged()
        }


        // -> 자서전 상세보기 .. 얘도 !!
        bookAdapter.setMyItemClickListener(object : BookRVAdapter.MyItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(context, MybookDetailedActivity::class.java)
                startActivity(intent)
            }
        })


        return viewBinding.root
    }
}

