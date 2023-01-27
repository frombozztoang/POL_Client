package com.umc.pieciesoflife.myPage

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.databinding.FragmentBookBinding
import kotlinx.android.synthetic.main.fragment_book.*


class BookFragment : Fragment() {
    private lateinit var Binding: FragmentBookBinding

    lateinit var bookAdapter: BookAdapter
    val datas = mutableListOf<BookData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Binding = FragmentBookBinding.inflate(inflater, container, false)

        return Binding.root
        initRecycler()
    }

    private fun initRecycler() {
        bookAdapter = BookAdapter(Activity())
        rv_book.adapter = bookAdapter

        datas.apply {
            add(
                BookData(
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
                BookData(
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
                BookData(
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
                BookData(
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
                BookData(
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