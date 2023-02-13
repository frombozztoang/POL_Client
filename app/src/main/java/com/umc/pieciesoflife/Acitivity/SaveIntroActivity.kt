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
import com.umc.pieciesoflife.databinding.ActivitySaveFinalBinding
import com.umc.pieciesoflife.databinding.ActivitySaveIntroBinding

class SaveIntroActivity : AppCompatActivity() {
    var editText: EditText? = null
    private lateinit var viewBinding: ActivitySaveIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_intro)
        viewBinding = ActivitySaveIntroBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        var storyQnaList = intent.getSerializableExtra("storyQnaList")
        var storyTagList = intent.getSerializableExtra("storyTagList")
        var bookTitle = intent.getSerializableExtra("bookTitle") as String
        var bookIntro = ""

        //뒤로가기 (제목 작성)
        viewBinding.buttonBack.setOnClickListener {
            val intent = Intent(applicationContext, SaveTitleActivity::class.java)
            startActivity(intent)
        }
        //다음 (색상 정하기)
        viewBinding.buttonNext.setOnClickListener {
            bookIntro=viewBinding.introTxt.text.toString()
            val intent = Intent(applicationContext, SaveColorActivity::class.java)
            intent.putExtra("bookTitle", bookTitle)
            intent.putExtra("bookIntro", bookIntro)
            intent.putExtra("storyQnaList","$storyQnaList")
            intent.putExtra("storyTagList","$storyTagList")
            startActivity(intent) //다음 화면 띄우기
        }
    }
}