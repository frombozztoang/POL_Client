package com.umc.pieciesoflife.Acitivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.pieciesoflife.R
import android.content.Intent
import android.view.View
import android.widget.Button

class StartNewstoryAcitivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_newstory)
        val button = findViewById<View>(R.id.button_ready) as Button
        button.setOnClickListener {
            val intent = Intent(applicationContext, TagAgeActivity::class.java)
            startActivity(intent) //다음 화면 띄우기
        }
    }
}