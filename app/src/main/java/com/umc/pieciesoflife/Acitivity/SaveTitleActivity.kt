package com.umc.pieciesoflife.Acitivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.pieciesoflife.R
import android.content.Intent
import android.util.Log
import com.umc.pieciesoflife.databinding.ActivitySaveTitleBinding

class SaveTitleActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySaveTitleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_title)
        viewBinding = ActivitySaveTitleBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        var storyQnaList = intent.getSerializableExtra("storyQnaList")
        var storyTagList = intent.getSerializableExtra("storyTagList")
        var bookTitle = ""

        //뒤로가기 (이야기 작성)
        viewBinding.buttonBack.setOnClickListener {
            val intent = Intent(applicationContext, StoryWriteActivity::class.java)
            startActivity(intent) //다음 Tag 화면 띄우기
        }
        //다음 (한줄소개 작성)
        viewBinding.buttonNext.setOnClickListener {
            bookTitle=viewBinding.titleTxt.text.toString()
            val intent = Intent(applicationContext, SaveIntroActivity::class.java)
            intent.putExtra("bookTitle", bookTitle)
            intent.putExtra("storyQnaList",storyQnaList)
            intent.putExtra("storyTagList",storyTagList)
            Log.i("storyQnaList","$storyQnaList")
            startActivity(intent) //다음 화면 띄우기
        }
    }
}