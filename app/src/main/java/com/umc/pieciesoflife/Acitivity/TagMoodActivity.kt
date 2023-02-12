package com.umc.pieciesoflife.Acitivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.pieciesoflife.R
import android.widget.ImageButton
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.viewbinding.ViewBinding
import com.umc.pieciesoflife.Acitivity.TagPlaceActivity
import com.umc.pieciesoflife.Acitivity.TagYearActivity
import com.umc.pieciesoflife.databinding.ActivityTagMoodBinding

class TagMoodActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityTagMoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTagMoodBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        var tagHash = intent.getSerializableExtra("TagHash") as HashMap<Int, String>

        var TagContent : String = ""
        var TagId : Int = 3


        viewBinding.buttonNext.setOnClickListener {
            TagContent = viewBinding.editTextMood.text.toString()
            tagHash.put(TagId, TagContent)
            Log.i("content","$tagHash") //확인
            val intent = Intent(applicationContext, TagPlaceActivity::class.java)
            intent.putExtra("TagHash", tagHash)
            startActivity(intent) //다음 화면 띄우기
        }
        viewBinding.buttonBack.setOnClickListener {
            val intent = Intent(applicationContext, TagYearActivity::class.java)
            startActivity(intent) //이전 Tag 화면 띄우기
        }
        viewBinding.buttonSkipQuestion.setOnClickListener {
            val intent = Intent(applicationContext, TagPlaceActivity::class.java)
            intent.putExtra("TagHash", tagHash)
            startActivity(intent) //다음 화면 띄우기
        }
    }
}