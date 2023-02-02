package com.umc.pieciesoflife.Fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.pieciesoflife.Adapter.BookAdapter
import com.umc.pieciesoflife.Adapter.MessageAdapter
import com.umc.pieciesoflife.DataClass.Book
import com.umc.pieciesoflife.DataClass.Message
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {
    private lateinit var Binding: FragmentMessageBinding

    // 어댑터 생성
    lateinit var messageAdapter: MessageAdapter
    val messageList: ArrayList<Message> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Binding = FragmentMessageBinding.inflate(inflater, container, false)
        initRecycler()
        return Binding.root
    }
    private fun initRecycler() {
        messageAdapter = MessageAdapter(Activity())
        Binding.rvMessage.adapter = messageAdapter
        Binding.rvMessage.layoutManager = LinearLayoutManager(context)

        messageList.apply {
            add(
                Message(
                    userImg = R.drawable.ic_book,
                    userName = "mary",
                    date = "2023/11/12 13:42 ",
                    content = "Android Gradle Plugin can be upgraded 응 싫어 ",
                )
            )
            add(
                Message(
                    userImg = R.drawable.ic_book,
                    userName = "mary",
                    date = "2023/11/12 13:42 ",
                    content = "Android Gradle Plugin can be upgraded 응 싫어 ",
                )
            )
            add(
                Message(
                    userImg = R.drawable.ic_book,
                    userName = "mary",
                    date = "2023/11/12 13:42 ",
                    content = "Android Gradle Plugin can be upgraded 응 싫어 ",
                )
            )

            add(
                Message(
                    userImg = R.drawable.ic_book,
                    userName = "mary",
                    date = "2023/11/12 13:42 ",
                    content = "Android Gradle Plugin can be upgraded 응 싫어 ",
                )
            )

            messageAdapter.datas = messageList
            messageAdapter.notifyDataSetChanged()

        }
    }
}