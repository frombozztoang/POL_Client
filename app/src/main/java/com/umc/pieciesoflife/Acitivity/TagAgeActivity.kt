package com.umc.pieciesoflife

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.os.Bundle
import com.umc.pieciesoflife.R
import android.widget.ImageButton
import android.content.Intent
import android.view.View
import android.widget.Button
import com.umc.pieciesoflife.MainIntroActivity
import com.umc.pieciesoflife.TagYearActivity

class TagAgeActivity : AppCompatActivity() {
    var editText: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag_age)
        editText = findViewById(R.id.editText_age)
        val back = findViewById<View>(R.id.button_back) as ImageButton //뒤로가기
        back.setOnClickListener {
            val intent = Intent(applicationContext, MainIntroActivity::class.java)
            startActivity(intent) //다음 Tag 화면 띄우기
        }
        val next = findViewById<View>(R.id.button_next) as Button //다음
        next.setOnClickListener {
            val intent = Intent(applicationContext, TagYearActivity::class.java)
            // val input = editText.getText().toString() //텍스트 입력값 받아옴
            startActivity(intent) //다음 Tag 화면 띄우기
        }
        val skip = findViewById<View>(R.id.button_skip_question) as Button //이야기 건너뛰기
        skip.setOnClickListener {
            val intent = Intent(applicationContext, TagYearActivity::class.java)
            startActivity(intent) //다음 화면 띄우기
        }
    }
}