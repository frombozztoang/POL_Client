package com.umc.pieciesoflife.Fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.pieciesoflife.Adapter.BookAdapter
import com.umc.pieciesoflife.DataClass.Book
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.databinding.FragmentBookBinding


class BookFragment : Fragment() {
    private lateinit var Binding: FragmentBookBinding

    lateinit var bookAdapter: BookAdapter
    val datas = mutableListOf<Book>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentBookBinding.inflate(layoutInflater).root
        initRecycler()
    }

    private fun initRecycler() {
        bookAdapter = BookAdapter(Activity())
        Binding.rvBook.adapter = bookAdapter

        datas.apply {
            add(
                Book(
                    img = R.drawable.ic_test,
                    userName = "mary",
                    date = "2023.11.12",
                    title = "하아....",
                    content = "어쩔티비 ",
                    postTitle = "하아...",
                    postImg = R.drawable.ic_test
                )
            )
            add(
                Book(
                    img = R.drawable.ic_test,
                    userName = "mary",
                    date = "2023.11.12",
                    title = "하아....",
                    content = "어쩔티비 ",
                    postTitle = "하아...",
                    postImg = R.drawable.ic_test
                )
            )
            add(
                Book(
                    img = R.drawable.ic_test,
                    userName = "mary",
                    date = "2023.11.12",
                    title = "하아....",
                    content = "어쩔티비 ",
                    postTitle = "하아...",
                    postImg = R.drawable.ic_test
                )
            )
            add(
                Book(
                    img = R.drawable.ic_test,
                    userName = "mary",
                    date = "2023.11.12",
                    title = "하아....",
                    content = "어쩔티비 ",
                    postTitle = "하아...",
                    postImg = R.drawable.ic_test
                )
            )
            add(
                Book(
                    img = R.drawable.ic_test,
                    userName = "mary",
                    date = "2023.11.12",
                    title = "하아....",
                    content = "어쩔티비 ",
                    postTitle = "하아...",
                    postImg = R.drawable.ic_test
                )
            )

            bookAdapter.datas = datas
            bookAdapter.notifyDataSetChanged()

        }
    }
}