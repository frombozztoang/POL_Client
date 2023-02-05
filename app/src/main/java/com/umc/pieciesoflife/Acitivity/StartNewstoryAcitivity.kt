package com.umc.pieciesoflife.Acitivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.pieciesoflife.R
import android.content.Intent
import android.view.View
import android.widget.Button
import com.umc.pieciesoflife.databinding.ActivityStartNewstoryBinding

class StartNewstoryAcitivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityStartNewstoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityStartNewstoryBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

       viewBinding.buttonBack.setOnClickListener {
           finish()
       }
        viewBinding.buttonReady.setOnClickListener {
            val intent = Intent(applicationContext, TagAgeActivity::class.java)
            startActivity(intent) //다음 화면 띄우기
        }

    }
}