package com.umc.pieciesoflife.Acitivity

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.Query
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
        var storyId = chatroomName.substring(8, chatroomName.indexOf("_"))
        database.collection(path).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (i in task.result!!) {
                    writerId = i.data["writerId"]
                }
            }
        }



        // -> 뒤로
        viewBinding.buttonBack.setOnClickListener {
            finish()
        }
        // -> ChatSendActivity
        viewBinding.buttonChat.setOnClickListener {
            val intent = Intent(applicationContext, ChatSendActivity::class.java)
            intent.putExtra("id", storyId)
            intent.putExtra("myId", myId)
            intent.putExtra("writerId", writerId.toString().toInt())
            startActivity(intent)
        }

        //채팅방 이야기 표지 - GET(/story/{storyId}) 사용
    }
}