package com.umc.pieciesoflife.Acitivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.pieciesoflife.R
import android.widget.ImageButton
import android.content.Intent
import android.view.View
import android.widget.Button
import com.umc.pieciesoflife.Acitivity.TagMatterActivity
import com.umc.pieciesoflife.Acitivity.TagMoodActivity

class TagPlaceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag_place)
        val back = findViewById<View>(R.id.button_back) as ImageButton //뒤로가기
        back.setOnClickListener {
            val intent = Intent(applicationContext, TagMoodActivity::class.java)
            startActivity(intent) //다음 Tag 화면 띄우기
        }
        val next = findViewById<View>(R.id.button_next) as Button
        next.setOnClickListener {
            val intent = Intent(applicationContext, TagMatterActivity::class.java)
            startActivity(intent) //다음 화면 띄우기
        }
        val skip = findViewById<View>(R.id.button_skip_question) as Button
        skip.setOnClickListener {
            val intent = Intent(applicationContext, TagMatterActivity::class.java)
            startActivity(intent) //다음 화면 띄우기
        }
    }
}