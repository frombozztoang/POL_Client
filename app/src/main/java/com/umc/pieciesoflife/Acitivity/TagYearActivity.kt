package com.umc.pieciesoflife.Acitivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.pieciesoflife.R
import android.widget.ImageButton
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Button
import com.umc.pieciesoflife.Acitivity.TagAgeActivity
import com.umc.pieciesoflife.Acitivity.TagMoodActivity
import com.umc.pieciesoflife.databinding.ActivityTagYearBinding

class TagYearActivity : AppCompatActivity() {
    private lateinit var viewBinding:ActivityTagYearBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTagYearBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        var tagHash = intent.getSerializableExtra("TagHash") as HashMap<Int, String>
        var TagContent : String = ""
        var TagId : Int = 2

        viewBinding.buttonNext.setOnClickListener {
            TagContent = viewBinding.editTextYear.text.toString()
            tagHash.put(TagId, TagContent)
            Log.i("content","$tagHash") //확인
            val intent = Intent(applicationContext, TagMoodActivity::class.java)
            intent.putExtra("TagHash", tagHash)
            startActivity(intent) //다음 화면 띄우기
        }
        viewBinding.buttonBack.setOnClickListener {
            val intent = Intent(applicationContext, TagAgeActivity::class.java)
            startActivity(intent) //이전 Tag 화면 띄우기
        }
        viewBinding.buttonSkipQuestion.setOnClickListener {
            val intent = Intent(applicationContext, TagMoodActivity::class.java)
            startActivity(intent) //다음 화면 띄우기
        }
    }
}