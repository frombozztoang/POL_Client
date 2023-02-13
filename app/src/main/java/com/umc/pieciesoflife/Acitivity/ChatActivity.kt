package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.umc.pieciesoflife.databinding.ActivityChatBinding

class ChatActivity: AppCompatActivity() {
    private lateinit var viewBinding: ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // -> 뒤로
        viewBinding.buttonBack.setOnClickListener {
            finish()
        }

        // -> ChatSendActivity
        viewBinding.buttonChat.setOnClickListener {
            startActivity(Intent(this@ChatActivity, ChatSendActivity::class.java))
        }

        //채팅방 이야기 표지 - GET(/story/{storyId}) 사용
    }
}