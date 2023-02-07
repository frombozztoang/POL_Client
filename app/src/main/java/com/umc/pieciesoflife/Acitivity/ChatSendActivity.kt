package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.umc.pieciesoflife.databinding.ActivityChatSendBinding

class ChatSendActivity: AppCompatActivity() {
    private lateinit var viewBinding: ActivityChatSendBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityChatSendBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.buttonBack.setOnClickListener {
            finish()
        }

        viewBinding.buttonChatSend.setOnClickListener {
            finish()
            Toast.makeText(this, "쪽지 발신 성공!", Toast.LENGTH_SHORT ).show()
            //이 때, Spring Server에 UserID, StoryID POST
            //Firebase에는 쪽지 내용, 시간 POST
        }

    }

}