package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.databinding.ActivityDetailedExploreBinding

class ExploreDetailedActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailedExploreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener{ // 뒤로가기 버튼
            val intent = Intent(this, NotiActivity::class.java)
            startActivity(intent)
        }

        binding.btnSend.setOnClickListener{ // 알림 페이지 이동
            val intent = Intent(this, ChatSendActivity::class.java)
            startActivity(intent)
        }

        /* 좋아요 버튼 클릭 */

    }



    // 토스트 코드
    private fun toast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }
}