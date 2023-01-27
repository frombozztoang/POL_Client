package com.umc.pieciesoflife

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
    }
}