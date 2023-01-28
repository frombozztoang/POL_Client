package com.umc.pieciesoflife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.pieciesoflife.R
import android.content.Intent
import android.view.View
import android.widget.Button
import com.umc.pieciesoflife.TagAgeActivity

class MainIntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_intro)
        val button = findViewById<View>(R.id.button_ready) as Button
        button.setOnClickListener {
            val intent = Intent(applicationContext, TagAgeActivity::class.java)
            startActivity(intent) //다음 화면 띄우기
        }
    }
}