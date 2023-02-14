package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.umc.pieciesoflife.DTO.ChatDTO.ChatContent
import com.umc.pieciesoflife.databinding.ActivityChatSendBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Objects

class ChatSendActivity: AppCompatActivity() {
    private lateinit var viewBinding: ActivityChatSendBinding

    val database = Firebase.firestore
    var path = "HJ_chatRoom"
    var messageContent = HashMap<String, Any>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityChatSendBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        var storyId = intent.getSerializableExtra("id") as Int
        var myId = intent.getSerializableExtra("myId") as Int
        var writerId = intent.getSerializableExtra("writerId") as Int
        val createAt = LocalDateTime.now().toString() // 생성 시간
        val date = LocalDate.now().toString() // 띄울 날짜
        val time = LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) // 띄울 시간
        Log.d("CHAT TEST", "쪽지 보내기 $storyId, $myId, $writerId, $createAt, $date, $time")

        viewBinding.buttonChatSend.setOnClickListener {
            // 채팅 각각 말풍선 !
            val content = viewBinding.tvChatContent.text.toString()
            messageContent.put("date",createAt)
            messageContent.put("message", content)
            messageContent.put("userId", myId)
            // 채팅방 생성
            val chatContent = ChatContent(createAt, "CR"+storyId+"_"+myId, myId, writerId)
            database.collection(path).document("CR"+storyId+"_"+myId).set(chatContent)
            // 채팅 말풍성 생성
            database.collection(path).document("CR"+storyId+"_"+myId).collection("chatContent").document("$content").set(messageContent)
            Toast.makeText(this, "쪽지 발신 성공!", Toast.LENGTH_SHORT ).show()
            finish()
        }

/*
        // 채팅방 생성 : Firebase에 User, StoryID, 쪽지 내용, 시간 POST
        // collections(채팅방 이름 : chat{MyId}_{WriterId}_{storyId}) -> documents(채팅내용) - field속성으로 보낸사람의 Id, message, date넣어주고
        // 뽑을때 field의 보낸사람 Id랑 MyId비교해서 chat recyclerView에 구분해서 띄우기
        // 채팅방 조회는 내 MyId랑 colletion의 MyId, WirterId 둘 중에 일치하는 거 있으면 조회되게 하기
        viewBinding.buttonChatSend.setOnClickListener {
            //database.collection("testest").add(testData)
            Toast.makeText(this, "쪽지 발신 성공!", Toast.LENGTH_SHORT ).show()
            finish()
        }
 */

        viewBinding.buttonBack.setOnClickListener {
            finish()
        }
    }
}