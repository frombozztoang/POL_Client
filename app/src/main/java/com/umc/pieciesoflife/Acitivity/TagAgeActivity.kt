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

        var TagContent : String = ""
        var TagId = "1"

        //여기서부터 다시 확인하고 넣어지나 봐야함
        viewBinding.buttonNext.setOnClickListener {
            TagContent = viewBinding.editTextAge.text.toString()
            val tagArray: Array<Array<String?>> = Array(10) { arrayOfNulls<String?>(2) }
            tagArray[0]=arrayOf(TagId,TagContent)
            Log.i("content","$tagArray") //확인
            val intent = Intent(applicationContext, TagYearActivity::class.java)
            intent.putExtra("TagArray", tagArray)
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