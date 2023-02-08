package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.umc.pieciesoflife.databinding.ActivityChatSendBinding

class ChatSendActivity: AppCompatActivity() {
    private lateinit var viewBinding: ActivityChatSendBinding
    private lateinit var auth: FirebaseAuth

    val database = Firebase.firestore
    val testData = hashMapOf(
        "createAt" to "00:00:00",
        "message" to "하위",
        "userId" to "3"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityChatSendBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.buttonBack.setOnClickListener {
            finish()
        }

        viewBinding.buttonChatSend.setOnClickListener {
            database.collection("testest").add(testData)
            Toast.makeText(this, "쪽지 발신 성공!", Toast.LENGTH_SHORT ).show()
            finish()
            //이 때, Spring Server에 UserID, StoryID POST
            //Firebase에도 User, StoryID, 쪽지 내용, 시간 POST
        }
    }
}