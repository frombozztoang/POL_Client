package com.umc.pieciesoflife.Acitivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.pieciesoflife.R
import android.widget.ImageButton
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Button
import com.umc.pieciesoflife.Acitivity.TagMatterActivity
import com.umc.pieciesoflife.Acitivity.TagPersonActivity
import com.umc.pieciesoflife.databinding.ActivityTagObjectBinding

class TagObjectActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityTagObjectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTagObjectBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        var tagHash = intent.getSerializableExtra("TagHash") as HashMap<Int, String>
        var TagContent : String = ""
        var TagId : Int = 6


        viewBinding.buttonNext.setOnClickListener {
            TagContent = viewBinding.editTextObject.text.toString()
            tagHash.put(TagId, TagContent)
            Log.i("content","$tagHash") //확인
            val intent = Intent(applicationContext, TagPersonActivity::class.java)
            intent.putExtra("TagHash", tagHash)
            startActivity(intent) //다음 화면 띄우기
        }
        viewBinding.buttonBack.setOnClickListener {
            val intent = Intent(applicationContext, TagMatterActivity::class.java)
            startActivity(intent) //이전 Tag 화면 띄우기
        }
        viewBinding.buttonSkipQuestion.setOnClickListener {
            val intent = Intent(applicationContext, TagPersonActivity::class.java)
            startActivity(intent) //다음 화면 띄우기
        }
    }
}