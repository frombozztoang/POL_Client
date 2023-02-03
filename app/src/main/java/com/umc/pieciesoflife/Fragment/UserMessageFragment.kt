package com.umc.pieciesoflife.Fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.pieciesoflife.Acitivity.ChatActivity
import com.umc.pieciesoflife.Adapter.MessageRVAdapter
import com.umc.pieciesoflife.DataClass.Message
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.databinding.FragmentUserMessageBinding

class UserMessageFragment : Fragment() {
    private lateinit var Binding: FragmentUserMessageBinding
    private lateinit var messageAdapter: MessageRVAdapter


    val messageList: ArrayList<Message> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Binding = FragmentUserMessageBinding.inflate(inflater, container, false)

        //리사이클러뷰 어댑터
        messageAdapter = MessageRVAdapter(Activity())
        Binding.rvMessage.adapter = messageAdapter
        Binding.rvMessage.layoutManager = LinearLayoutManager(context)

        initRecycler()

        //->ChatActivity
        messageAdapter.setMyItemClickListener(object : MessageRVAdapter.MyItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(context, ChatActivity::class.java)
                startActivity(intent)
            }
        })


        return Binding.root
    }


    private fun initRecycler() {
        messageList.apply {
            add(
                Message(
                    userImg = R.drawable.ic_book,
                    userName = "팽팽이",
                    date = "2023/11/12 13:42 ",
                    content = "Android Gradle Plugin can be upgraded 응 싫어 ",
                )
            )
            add(
                Message(
                    userImg = R.drawable.ic_book,
                    userName = "팽팽이2",
                    date = "2023/11/12 13:42 ",
                    content = "Android Gradle Plugin can be upgraded 응 싫어 ",
                )
            )
            add(
                Message(
                    userImg = R.drawable.ic_book,
                    userName = "팽팽이3",
                    date = "2023/11/12 13:42 ",
                    content = "Android Gradle Plugin can be upgraded 응 싫어 ",
                )
            )

            add(
                Message(
                    userImg = R.drawable.ic_book,
                    userName = "팽팽이4",
                    date = "2023/11/12 13:42 ",
                    content = "Android Gradle Plugin can be upgraded 응 싫어 ",
                )
            )

            add(
                Message(
                    userImg = R.drawable.ic_book,
                    userName = "팽팽이5",
                    date = "2023/11/12 13:42 ",
                    content = "Android Gradle Plugin can be upgraded 응 싫어 ",
                )
            )

            messageAdapter.datas = messageList
            messageAdapter.notifyDataSetChanged()

        }
    }
}