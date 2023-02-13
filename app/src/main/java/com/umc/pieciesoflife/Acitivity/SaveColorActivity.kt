package com.umc.pieciesoflife.Acitivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.pieciesoflife.R
import android.widget.ImageButton
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.Button
import com.umc.pieciesoflife.Acitivity.SaveFinalActivity
import com.umc.pieciesoflife.Acitivity.SaveIntroActivity
import com.umc.pieciesoflife.databinding.ActivitySaveColorBinding


class SaveColorActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivitySaveColorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_color)
        viewBinding = ActivitySaveColorBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        var storyQnaList = intent.getSerializableExtra("storyQnaList")
        var storyTagList = intent.getSerializableExtra("storyTagList")
        var bookColor = "#CDB5FA"
        var bookTitle = intent.getSerializableExtra("bookTitle") as String
        var bookIntro = intent.getSerializableExtra("bookIntro") as String

        //북포스트 색깔 변경
        viewBinding.colorBtn1.setOnClickListener{
            viewBinding.bookPost.setImageResource(R.drawable.ic_book)
            viewBinding.bookPost.setColorFilter(Color.parseColor("#CDB5FA"))
            bookColor="#CDB5FA"
        }
        viewBinding.colorBtn2.setOnClickListener{
            viewBinding.bookPost.setImageResource(R.drawable.ic_book)
            viewBinding.bookPost.setColorFilter(Color.parseColor("#9DC4F0"))
            bookColor="#9DC4F0"
        }
        viewBinding.colorBtn3.setOnClickListener{
            viewBinding.bookPost.setImageResource(R.drawable.ic_book)
            viewBinding.bookPost.setColorFilter(Color.parseColor("#F2ACAC"))
            bookColor="#F2ACAC"
        }
        viewBinding.colorBtn4.setOnClickListener{
            viewBinding.bookPost.setImageResource(R.drawable.ic_book)
            viewBinding.bookPost.setColorFilter(Color.parseColor("#B9DF98"))
            bookColor="#B9DF98"
        }
        viewBinding.colorBtn5.setOnClickListener{
            viewBinding.bookPost.setImageResource(R.drawable.ic_book)
            viewBinding.bookPost.setColorFilter(Color.parseColor("#F7D698"))
            bookColor="#F7D698"
        }
        viewBinding.colorBtn6.setOnClickListener{
            viewBinding.bookPost.setImageResource(R.drawable.ic_book)
            viewBinding.bookPost.setColorFilter(Color.parseColor("#F8B2E8"))
            bookColor="#F8B2E8"
        }
        viewBinding.colorBtn7.setOnClickListener{
            viewBinding.bookPost.setImageResource(R.drawable.ic_book)
            viewBinding.bookPost.setColorFilter(Color.parseColor("#90DED3"))
            bookColor="#90DED3"
        }
        viewBinding.colorBtn8.setOnClickListener{
            viewBinding.bookPost.setImageResource(R.drawable.ic_book)
            viewBinding.bookPost.setColorFilter(Color.parseColor("#DFE07E"))
            bookColor="#DFE07E"
        }


        //val back = findViewById<View>(R.id.button_back) as ImageButton
        //뒤로가기
        viewBinding.buttonBack.setOnClickListener {
            val intent = Intent(applicationContext, SaveIntroActivity::class.java)
            intent.putExtra("bookTitle", bookTitle)
            startActivity(intent) //다음 Tag 화면 띄우기
        }
        //val next = findViewById<View>(R.id.button_next) as Button
        //다음
        viewBinding.buttonNext.setOnClickListener {
            val intent = Intent(applicationContext, SaveFinalActivity::class.java)
            intent.putExtra("bookColor", bookColor)
            intent.putExtra("bookIntro", bookIntro)
            intent.putExtra("bookTitle", bookTitle)
            intent.putExtra("storyQnaList",storyQnaList)
            intent.putExtra("storyTagList",storyTagList)
            Log.i("storyQnaList","$storyQnaList")
            startActivity(intent) //다음 화면 띄우기
        }
    }
}