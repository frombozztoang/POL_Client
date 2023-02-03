package com.umc.pieciesoflife.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.pieciesoflife.Acitivity.ExploreDetailedActivity
import com.umc.pieciesoflife.Acitivity.MybookDetailedActivity
import com.umc.pieciesoflife.Adapter.BookRVAdapter
import com.umc.pieciesoflife.DataClass.Book
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.databinding.FragmentUserBookBinding


class UserBookFragment : Fragment() {
    private lateinit var Binding: FragmentUserBookBinding
    private lateinit var bookAdapter: BookRVAdapter   //RV어댑터 생성

    val bookList: ArrayList<Book> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Binding = FragmentUserBookBinding.inflate(inflater, container, false)
        initRecycler()

        // -> 자서전 상세보기 페이지(ExploreDetailedActivity) .. 우선은 걍 intent만 해놓음 서버 연결 후, 클릭된 자서전 내용으로 떠야됌
        bookAdapter.setMyItemClickListener(object : BookRVAdapter.MyItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(context, ExploreDetailedActivity::class.java)
                startActivity(intent)
            }
        })

        return Binding.root
    }

    private fun initRecycler() {
        bookAdapter = BookRVAdapter(bookList)
        Binding.rvBook.adapter = bookAdapter
        Binding.rvBook.layoutManager = LinearLayoutManager(context)

        bookList.apply {
            add(
                Book(
                    profileImg =  R.drawable.ic_flag_level2,
                    userName = "mary",
                    date = "2023.11.12",
                    title = "첫번째 하아",
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
                    title = "두번째 하아",
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
                    title = "하아....",
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
                    title = "네번째 하아....",
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
                    title = "하아....",
                    content = "어쩔티비 ",
                    postTitle = "하아...",
                    postImg = R.drawable.ic_book
                )
            )

            bookAdapter.notifyDataSetChanged()

        }
    }
}