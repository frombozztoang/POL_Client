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
import com.umc.pieciesoflife.Adapter.BookRVAdapter
import com.umc.pieciesoflife.BottomNavBar.BottomNavBarActivity
import com.umc.pieciesoflife.DataClass.Book
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.databinding.ActivityBottomNavBarBinding
import com.umc.pieciesoflife.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var viewBinding: FragmentHomeBinding
    private lateinit var bookAdapter: BookRVAdapter

    var bookList: ArrayList<Book> = arrayListOf()

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
            mActivity.changeFragment(UserFragment())
        }

        viewBinding.btnNoti.setOnClickListener { // 알림
            val intent = Intent(context, NotiActivity::class.java)
            startActivity(intent)
        }

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

