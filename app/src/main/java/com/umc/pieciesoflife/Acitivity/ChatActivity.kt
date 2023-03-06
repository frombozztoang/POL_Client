package com.umc.pieciesoflife.Acitivity

import android.app.Activity
import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.Query
import com.umc.pieciesoflife.Adapter.ChatRVAdapter
import com.umc.pieciesoflife.Adapter.NotiRVAdapter
import com.umc.pieciesoflife.DTO.ChatDTO.Chat
import com.umc.pieciesoflife.DataClass.Noti
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.databinding.ActivityChatBinding

class ChatActivity: AppCompatActivity() {
    private lateinit var viewBinding: ActivityChatBinding

    var chatroomName = "CR94_11"
    val database = Firebase.firestore
    var path = "HJ_chatRoom"

    var writerId : Any? = null
    val messageList : ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // 해당 채팅방 전체 쪽지 조회
        var documentReference = database.collection(path).document(chatroomName).collection("chatContent")
            .orderBy("date", Query.Direction.DESCENDING).get()
        documentReference.addOnSuccessListener {querySnapshot ->
            val messages : MutableList<DocumentSnapshot> = querySnapshot.documents

            for (mes in messages){
                messageList.add(mes.id)
            }
            Log.d("messageList", "$messageList")
        }

        var myId = chatroomName.substring(chatroomName.indexOf("_") + 1)
//        var storyId = chatroomName.substring(8, chatroomName.indexOf("_"))
        database.collection(path).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (i in task.result!!) {
                    writerId = i.data["writerId"]
                }
            }
        }

        //리사이클러뷰 어댑터
        val chats: ArrayList<Chat> = arrayListOf() // 리사이클러뷰용 리스트
        chats.apply {
            add(Chat("받은 쪽지", "23/02/16 03:58", "억울해요"))
            add(Chat("보낸 쪽지", "23/02/16 03:58", "진짜 했어요"))
            add(Chat("받은 쪽지", "23/02/16 03:58", "얘도 채팅방 조회해서 채팅방 전체 내용 조회했다구요"))
            add(Chat("보낸 쪽지", "23/02/16 03:58", "messageList Log에 출력했는데 보ㅅ실래여?"))
            add(Chat("받은 쪽지", "23/02/16 03:58", "저히가 불쌍합니까 ?? 예?? 예?? 제가 한마디 합죠."))
            add(Chat("보낸 쪽지", "23/02/16 03:58", "근데 뼈를 갈았으니까,, 그러니까 ,, 진짜 이쁘게 봐줘요 ,,"))
        }
        val chatRVAdapter = ChatRVAdapter(chats)

        viewBinding.rvMessage.adapter = chatRVAdapter
        viewBinding.rvMessage.layoutManager = LinearLayoutManager(this)


        // -> 뒤로
        viewBinding.buttonBack.setOnClickListener {
            finish()
        }
        // -> ChatSendActivity
        viewBinding.buttonChat.setOnClickListener {
            val intent = Intent(applicationContext, ChatSendActivity::class.java)
          //  intent.putExtra("id", storyId)
            intent.putExtra("myId", myId)
            intent.putExtra("writerId", writerId.toString().toInt())
            startActivity(intent)
        }

        //채팅방 이야기 표지 - GET(/story/{storyId}) 사용
    }
}