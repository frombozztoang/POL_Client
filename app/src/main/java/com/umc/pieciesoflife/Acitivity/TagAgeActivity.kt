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
    lateinit var tagHash : HashMap<Int, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTagAgeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        tagHash = HashMap()

        var TagContent : String = viewBinding.editTextAge.text.toString()
        var TagId : Int = 1

        tagHash.put(TagId, TagContent)

        viewBinding.buttonNext.setOnClickListener {
            val intent = Intent(applicationContext, TagYearActivity::class.java)
            intent.putExtra("TagHash", tagHash)
            startActivity(intent)
        }
        viewBinding.buttonBack.setOnClickListener {
            val intent = Intent(applicationContext, StartNewstoryAcitivity::class.java)
            startActivity(intent) //이전 Tag 화면 띄우기
        }
        viewBinding.buttonSkipQuestion.setOnClickListener {
            val intent = Intent(applicationContext, TagYearActivity::class.java)
            startActivity(intent) //다음 화면 띄우기
        }



    }
}