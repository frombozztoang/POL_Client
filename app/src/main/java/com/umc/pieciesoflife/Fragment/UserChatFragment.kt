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
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.umc.pieciesoflife.Acitivity.ChatActivity
import com.umc.pieciesoflife.Adapter.ChatRoomRVAdapter
import com.umc.pieciesoflife.DTO.MyPageDto.ChatRooms
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.databinding.FragmentUserMessageBinding

class UserChatFragment : Fragment() {
    private lateinit var Binding: FragmentUserMessageBinding
    private lateinit var chatRVAdapter: ChatRoomRVAdapter

    val database = Firebase.firestore
    var path = "HJ_chatRoom"
    // 모든 채팅방의 chats
    val recentmessagelist : ArrayList<String> = arrayListOf() // 가장 최근 메세지 담는 리스트
    val othersId : ArrayList<Int> = arrayListOf()
    val mylist = mutableListOf<String>() // '내가' 있는 모든 채팅방 이름

    val chatRooms: ArrayList<ChatRooms> = arrayListOf() // 리사이클러뷰용 리스트

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Binding = FragmentUserMessageBinding.inflate(inflater, container, false)


        setFragmentResultListener("requestKey") { requestKey, bundle ->
            var userId = bundle.getString("bundleKey").toString().toInt()
            Log.d("유저아이딩가딩가링", "$userId")

            //리사이클러뷰 어댑터
            chatRVAdapter = ChatRoomRVAdapter(Activity())
            Binding.rvMessage.adapter = chatRVAdapter
            Binding.rvMessage.layoutManager = LinearLayoutManager(context)

            // firebase에서 내가 포함된 채팅방 조회
            var collectionReference = database.collection(path)
            // sender(myId)가 나인 경우
            var query1 = collectionReference.whereEqualTo("myId", userId)
            query1.get().addOnSuccessListener { querySnapshot ->
                val doc1: MutableList<DocumentSnapshot> = querySnapshot.documents
                for (doc in doc1) {
                    mylist.add(doc.id)
                    Log.d("ChatGET", doc.toString())
                }
                Log.d("ChatGET ID", "$mylist")
                Log.d("mylist", "$mylist")
            }

            // writerId가 나인 경우
            var query2 = collectionReference.whereEqualTo("writerId", userId)
            query2.get().addOnSuccessListener { querySnapshot ->
                val doc2: MutableList<DocumentSnapshot> = querySnapshot.documents
                for (doc in doc2) {
                    mylist.add(doc.id)
                    Log.d("ChatGET", doc.toString())
                }
                Log.d("ChatGET ID", "$mylist")
                Log.d("mylistt", "$mylist")

                if (mylist.size != 0) {
                    Log.d("mylistt", "$mylist")
                    for (i in mylist.indices) {
                        Log.d("documentReference", "맹")
                        var documentReference = database.collection(path).document(mylist[i]).collection("chatContent")
                                .orderBy("date", Query.Direction.DESCENDING).limit(1).get()
                        documentReference.addOnSuccessListener { querySnapshot ->
                            val lastmessages: MutableList<DocumentSnapshot> = querySnapshot.documents
                            for (last in lastmessages) {
                                recentmessagelist.add(last.id)
                                //val item = last.toObject(ChatRooms::class.java)
                                //chatRVAdapter.addItem(item!!)
                                //chatRooms.add(item!!)
                            }
                            if (i==mylist.size-1){
                                Log.d("documentReference", documentReference.toString())
                                Log.d("찐RecentMessageList", "$recentmessagelist")
                            }
                            //여기 이후로 recentmessagelist에 값이 안 담김
                        }

                        var chatroomName = mylist.get(i)
                        var myId = chatroomName.substring(chatroomName.indexOf("_") + 1)
                        Log.d("myId", "$myId")
                        var myIdInt = myId.toInt()
                        database.collection(path).get().addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                for (j in task.result!!) {
                                    if (j.id == mylist[i]) {
                                        var writerId = j.data["writerId"]
                                        var writerIdInt = writerId.toString().toInt()
                                        Log.d("whywhy", "$writerId")
                                        if (writerIdInt == userId) {
                                            othersId.add(myIdInt)
                                        } else {
                                            othersId.add(writerIdInt)
                                        }
                                    }
                                }
                                Log.d("찐OthersId", "$othersId")
                            }

                        }
                    }
                }

            }

            initRecycler()


            //->ChatActivity
            chatRVAdapter.setMyItemClickListener(object : ChatRoomRVAdapter.MyItemClickListener {
                override fun onItemClick(position: Int) {
                    val intent = Intent(context, ChatActivity::class.java)
                    startActivity(intent)
                }
            })

        }

        return Binding.root
    }

    private fun initRecycler() {
        //for(i in othersId.indices) {
        //    Log.d("othersidsize", "$othersId")
        //    chatRVAdapter.addItem(ChatRooms("R.drawable.ic_pol", othersId[i].toString(), "2023", recentmessagelist[i]))
        //}

        /*
        for(i in othersId.indices){
            chatList.apply {
                add(
                    ChatRooms(
                        userImg = "R.drawable.ic_pol",
                        userName = othersId[i].toString(),
                        date = "2023",
                        content = recentmessagelist[i]
                    )
                )
            }

            chatRVAdapter.chatRooms = chatList
        }

         */


        chatRooms.apply {
            add(
                ChatRooms(
                    userImg = R.drawable.ic_book,
                    userName = "핑핑이",
                    date = "2023/11/12 13:42 ",
                    content = "제 머리가 핑핑핑 돌아요",
                )
            )
            add(
                ChatRooms(
                    userImg = R.drawable.ic_book,
                    userName = "핑핑이2",
                    date = "2023/11/12 13:42 ",
                    content = "서버 진짜 다 했는데 배포를 못했어요.. 왤까요?? 짐을 바리바리 싸들고 왔는데 문을 안 열어줘요",
                )
            )
            add(
                ChatRooms(
                    userImg = R.drawable.ic_book,
                    userName = "핑핑이3",
                    date = "2023/11/12 13:42 ",
                    content = "그래서 이번엔 클라가 나서봤어요. 네.. 괜히 나섰어요.. 도대체 뭘 한걸까요? ",
                )
            )

            add(
                ChatRooms(
                    userImg = R.drawable.ic_book,
                    userName = "핑핑이4",
                    date = "2023/11/12 13:42 ",
                    content = "데이터 받아오면 뭐해요 띄우지를 못해요 꾸역꾸역 띄워봤는데 디자인 너무 구려서 코박고 죽고싶었어요 ",
                )
            )

            add(
                ChatRooms(
                    userImg = R.drawable.ic_book,
                    userName = "핑핑이5",
                    date = "2023/11/12 13:42 ",
                    content = "그래서 핑핑이가 등장했어요 이뻐해주세요 우리 핑핑이,, 우리 POL도요,, ",
                )
            )

            chatRVAdapter.datas = chatRooms
            chatRVAdapter.notifyDataSetChanged()

        }

    }
}