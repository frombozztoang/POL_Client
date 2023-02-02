package com.umc.pieciesoflife.Fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.pieciesoflife.Adapter.BookAdapter
import com.umc.pieciesoflife.DataClass.Book
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.databinding.FragmentBookBinding


class BookFragment : Fragment() {
    private lateinit var Binding: FragmentBookBinding

    // 어댑터 생성성
    lateinit var bookAdapter: BookAdapter
   // val datas = mutableListOf<Book>()

    val bookList: ArrayList<Book> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Binding = FragmentBookBinding.inflate(inflater, container, false)
        initRecycler()
        return Binding.root
    }

    private fun initRecycler() {
        bookAdapter = BookAdapter(Activity())
        Binding.rvBook.adapter = bookAdapter
        Binding.rvBook.layoutManager = LinearLayoutManager(context)

        bookList.apply {
            add(
                Book(
                    img = R.drawable.ic_flag_level2,
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
                    img = R.drawable.ic_flag_level2,
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
                    img = R.drawable.ic_flag_level2,
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
                    img = R.drawable.ic_flag_level2,
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
                    img = R.drawable.ic_flag_level2,
                    userName = "mary",
                    date = "2023.11.12",
                    title = "하아....",
                    content = "어쩔티비 ",
                    postTitle = "하아...",
                    postImg = R.drawable.ic_book
                )
            )

            bookAdapter.datas = bookList
            bookAdapter.notifyDataSetChanged()

        }
    }
}