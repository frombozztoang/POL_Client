package com.umc.pieciesoflife.Acitivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.pieciesoflife.R
import android.widget.ImageButton
import android.content.Intent
import android.view.View
import android.widget.Button
import com.umc.pieciesoflife.Acitivity.TagMatterActivity
import com.umc.pieciesoflife.Acitivity.TagMoodActivity
import com.umc.pieciesoflife.databinding.ActivityTagPlaceBinding

class TagPlaceActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityTagPlaceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTagPlaceBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        var tagHash = intent.getSerializableExtra("TagHash") as HashMap<Int, String>

        var TagContent : String = viewBinding.editTextPlace.text.toString()
        var TagId : Int = 4

        tagHash.put(TagId, TagContent)

        viewBinding.buttonNext.setOnClickListener {
            val intent = Intent(applicationContext, TagMatterActivity::class.java)
            intent.putExtra("TagHash", tagHash)
            startActivity(intent) //다음 화면 띄우기
        }
        viewBinding.buttonBack.setOnClickListener {
            val intent = Intent(applicationContext, TagMoodActivity::class.java)
            startActivity(intent) //이전 Tag 화면 띄우기
        }
        viewBinding.buttonSkipQuestion.setOnClickListener {
            val intent = Intent(applicationContext, TagMatterActivity::class.java)
            startActivity(intent) //다음 화면 띄우기
        }
    }
}