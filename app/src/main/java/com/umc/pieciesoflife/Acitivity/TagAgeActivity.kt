package com.umc.pieciesoflife.Acitivity

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.os.Bundle
import com.umc.pieciesoflife.R
import android.widget.ImageButton
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import com.umc.pieciesoflife.databinding.ActivityTagAgeBinding

class TagAgeActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityTagAgeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTagAgeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        var tagHash = HashMap<Int, String>()
        var TagContent : String = ""
        var TagId = 1

        //다음
        viewBinding.buttonNext.setOnClickListener {
            TagContent = viewBinding.editTextAge.text.toString()
            tagHash.put(TagId,TagContent) //태그아이디&태그내용 넣기
            Log.i("content","$tagHash") //확인
            val intent = Intent(applicationContext, TagYearActivity::class.java)
            intent.putExtra("TagHash", tagHash)
            startActivity(intent)
        }
        //뒤로가기
        viewBinding.buttonBack.setOnClickListener {
            val intent = Intent(applicationContext, StartNewstoryAcitivity::class.java)
            startActivity(intent) //이전 Tag 화면 띄우기
        }
        //질문 건너뛰기
        viewBinding.buttonSkipQuestion.setOnClickListener {
            val intent = Intent(applicationContext, TagYearActivity::class.java)
            intent.putExtra("TagHash", tagHash)
            startActivity(intent) //다음 화면 띄우기
        }



    }
}