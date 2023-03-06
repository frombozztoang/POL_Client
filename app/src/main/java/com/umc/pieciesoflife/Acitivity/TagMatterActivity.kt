package com.umc.pieciesoflife.Acitivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.pieciesoflife.R
import android.widget.ImageButton
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.umc.pieciesoflife.Acitivity.TagObjectActivity
import com.umc.pieciesoflife.Acitivity.TagPlaceActivity
import com.umc.pieciesoflife.databinding.ActivityTagMatterBinding
import com.umc.pieciesoflife.databinding.ActivityTagMoodBinding

class TagMatterActivity : AppCompatActivity() {
    private lateinit var viewBinding:ActivityTagMatterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTagMatterBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        var tagHash = intent.getSerializableExtra("TagHash") as HashMap<Int, String>
        var TagContent : String = ""
        var TagId : Int = 5


        viewBinding.buttonNext.setOnClickListener {
            val inputText = viewBinding.editTextMatter.text.toString()
            if (inputText.isEmpty()) {
                Toast.makeText(this, "태그를 입력하지 않으면 저장할 수 없습니다.", Toast.LENGTH_SHORT).show()
            } else {
                TagContent = viewBinding.editTextMatter.text.toString()
                tagHash.put(TagId, TagContent)
                Log.i("content","$tagHash") //확인
                val intent = Intent(applicationContext, TagObjectActivity::class.java)
                intent.putExtra("TagHash", tagHash)
                startActivity(intent) //다음 화면 띄우기
            }
        }
        viewBinding.buttonBack.setOnClickListener {
            val intent = Intent(applicationContext, TagPlaceActivity::class.java)
            startActivity(intent) //이전 Tag 화면 띄우기
        }
        viewBinding.buttonSkipQuestion.setOnClickListener {
            val intent = Intent(applicationContext, TagObjectActivity::class.java)
            intent.putExtra("TagHash", tagHash)
            startActivity(intent) //다음 화면 띄우기
        }
    }
}