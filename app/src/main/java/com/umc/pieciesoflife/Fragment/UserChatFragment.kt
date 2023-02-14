package com.umc.pieciesoflife.Fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.umc.pieciesoflife.Acitivity.ChatActivity
import com.umc.pieciesoflife.Adapter.ChatRVAdapter
import com.umc.pieciesoflife.DataClass.ChatRoom
import com.umc.pieciesoflife.databinding.FragmentUserMessageBinding

class UserChatFragment : Fragment() {
    private lateinit var Binding: FragmentUserMessageBinding
    private lateinit var chatRVAdapter: ChatRVAdapter
//    private lateinit var messageAdapter: MessageRVAdapter

    val database = Firebase.firestore
    var path = "HJ_chatRoom"
    val chatList: ArrayList<ChatRoom> = arrayListOf() // 리사이클러뷰용 리스트
    var userId = 11
    // 모든 채팅방의 chats
    val recentmessagelist : ArrayList<String> = arrayListOf() // 가장 최근 메세지 담는 리스트
    val othersId : ArrayList<Int> = arrayListOf()
    val mylist = mutableListOf<String>() // '내가' 있는 모든 채팅방 이름

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Binding = FragmentUserMessageBinding.inflate(inflater, container, false)

        // userFragment에서 userId 받아오기
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            val result = bundle.getString("bundleKey")
            userId = result.toString().toInt()
            Log.d("유저아이딩가딩가링", "$result")
        }

        // firebase에서 내가 포함된 채팅방 조회
        var collectionReference = database.collection(path)
        // sender(myId)가 나인 경우
        var query1 = collectionReference.whereEqualTo("myId",userId)
        query1.get().addOnSuccessListener { querySnapshot ->
            val doc1: MutableList<DocumentSnapshot> = querySnapshot.documents
            for (doc in doc1){
                mylist.add(doc.id)
                Log.d("ChatGET", doc.toString())
            }
            Log.d("ChatGET ID", "$mylist")
            Log.d("mylist", "$mylist")
        }

        // writerId가 나인 경우
        var query2 = collectionReference.whereEqualTo("writerId",userId)
        query2.get().addOnSuccessListener { querySnapshot ->
            val doc2: MutableList<DocumentSnapshot> = querySnapshot.documents
            for (doc in doc2){
                mylist.add(doc.id)
                Log.d("ChatGET", doc.toString())
            }
            Log.d("ChatGET ID", "$mylist")
            Log.d("mylistt", "$mylist")

            if(mylist.size != 0){
                Log.d("mylistt", "$mylist")
                for(i in mylist.indices){
                    Log.d("documentReference", "맹")
                    var documentReference = database.collection(path).document(mylist[i]).collection("chatContent").orderBy("date", Query.Direction.DESCENDING).limit(1).get()
                    documentReference.addOnSuccessListener { querySnapshot ->
                        val lastmessages : MutableList<DocumentSnapshot> = querySnapshot.documents
                        for (last in lastmessages){
                            recentmessagelist.add(last.id)
                        }
                        Log.d("documentReference", documentReference.toString())
                        Log.d("RecentMessageList", "$recentmessagelist")

                    }
                    var chatroomName = mylist.get(i)
                    var myId = chatroomName.substring(chatroomName.indexOf("_") + 1)
                    Log.d("myId", "$myId")
                    var myIdInt = myId.toInt()
                    database.collection(path).get().addOnCompleteListener { task ->
                        if(task.isSuccessful){
                            for( j in task.result!!){
                                if(j.id == mylist[i])   {
                                    var writerId = j.data["writerId"]
                                    var writerIdInt = writerId.toString().toInt()
                                    Log.d("whywhy", "$writerId")
                                    if(writerIdInt == userId){
                                        othersId.add(myIdInt)
                                    }else{
                                        othersId.add(writerIdInt)
                                    }
                                }
                            }
                        }
                        Log.d("OthersId", "$othersId")
                    }
                }
            }
        }


        //리사이클러뷰 어댑터
        chatRVAdapter = ChatRVAdapter(Activity())
        Binding.rvMessage.adapter = chatRVAdapter
        Binding.rvMessage.layoutManager = LinearLayoutManager(context)

        initRecycler()

        //->ChatActivity
        chatRVAdapter.setMyItemClickListener(object : ChatRVAdapter.MyItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(context, ChatActivity::class.java)
                startActivity(intent)
            }
        })


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