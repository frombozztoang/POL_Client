package com.umc.pieciesoflife.Acitivity


import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.os.Bundle
import com.umc.pieciesoflife.R
import android.widget.ImageButton
import android.content.Intent
import android.view.View
import android.widget.Button
import com.umc.pieciesoflife.Acitivity.SaveColorActivity
import com.umc.pieciesoflife.Acitivity.SaveTitleActivity

class SaveIntroActivity : AppCompatActivity() {
    var editText: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_intro)
        val back = findViewById<View>(R.id.button_back) as ImageButton //뒤로가기
        back.setOnClickListener {
            val intent = Intent(applicationContext, SaveTitleActivity::class.java)
            startActivity(intent) //다음 Tag 화면 띄우기
        }
        val next = findViewById<View>(R.id.button_next) as Button //다음
        next.setOnClickListener {
            val intent = Intent(applicationContext, SaveColorActivity::class.java)
            startActivity(intent) //다음 화면 띄우기
        }
    }
}