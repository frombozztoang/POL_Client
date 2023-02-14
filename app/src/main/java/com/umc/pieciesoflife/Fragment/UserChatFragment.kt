package com.umc.pieciesoflife.Fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.umc.pieciesoflife.Acitivity.ChatActivity
//import com.umc.pieciesoflife.Adapter.MessageRVAdapter
import com.umc.pieciesoflife.DataClass.ChatRoom
import com.umc.pieciesoflife.databinding.FragmentUserMessageBinding

class UserChatFragment : Fragment() {
    private lateinit var Binding: FragmentUserMessageBinding
//    private lateinit var messageAdapter: MessageRVAdapter
//
//    val database = Firebase.firestore
//    var path = "HJ_chatRoom"
//    val messageList: ArrayList<ChatRoom> = arrayListOf() // 리사이클러뷰용 리스트
//    val mylist : ArrayList<String> = arrayListOf() // '내가' 있는 모든 채팅방 이름



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Binding = FragmentUserMessageBinding.inflate(inflater, container, false)

//        var userId = 0 //intent.getExtra어쩌구
//        //firebase에서 내가 포함된 채팅방 조회
//        var collectionReference = database.collection(path)
//
//        var query = collectionReference.whereEqualTo("myId",userId)
//        //var doc1 : Collection<QueryDocumentSnapshot> = query.get().
//
//
//
//
//
//        //리사이클러뷰 어댑터
//        messageAdapter = MessageRVAdapter(Activity())
//        Binding.rvMessage.adapter = messageAdapter
//        Binding.rvMessage.layoutManager = LinearLayoutManager(context)
//
//        initRecycler()
//
//        //->ChatActivity
//        messageAdapter.setMyItemClickListener(object : MessageRVAdapter.MyItemClickListener{
//            override fun onItemClick(position: Int) {
//                val intent = Intent(context, ChatActivity::class.java)
//                startActivity(intent)
//            }
//        })


        return Binding.root
    }


    private fun initRecycler() {
        /*
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

         */
    }
}