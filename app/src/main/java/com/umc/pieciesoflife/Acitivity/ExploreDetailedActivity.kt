package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.umc.pieciesoflife.Fragment.SearchFragment
import com.umc.pieciesoflife.databinding.ActivityDetailedExploreBinding

class ExploreDetailedActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailedExploreBinding

    var i = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener{ // 뒤로가기 버튼
            val intent = Intent(this, SearchFragment::class.java)
            startActivity(intent)
        }

        binding.btnSend.setOnClickListener{ // 쪽지 보내기
            val intent = Intent(this, ChatSendActivity::class.java)
            startActivity(intent)
        }
    }
}