package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import com.umc.pieciesoflife.databinding.ActivityDialogColorBinding

class DialogColorActivity : AppCompatActivity() {
    var color : String = " " // 변경할 색 저장할 변수

    private lateinit var viewBinding: ActivityDialogColorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDialogColorBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(viewBinding.root)

        // 색 변경 버튼
        viewBinding.btnPurple.setOnClickListener( colorListener )
        viewBinding.btnBlue.setOnClickListener( colorListener )
        viewBinding.btnPeach.setOnClickListener( colorListener )
        viewBinding.btnGreen.setOnClickListener( colorListener )
        viewBinding.btnYellow.setOnClickListener( colorListener )
        viewBinding.btnPink.setOnClickListener( colorListener )
        viewBinding.btnMint.setOnClickListener( colorListener )
        viewBinding.btnLime.setOnClickListener( colorListener )

        // 오케이 버튼
        viewBinding.btnOk.setOnClickListener {
            val intent = Intent(this, MybookDetailedActivity::class.java)
            intent.putExtra("color", color)
            startActivity(intent)
        }
        viewBinding.btnCancel.setOnClickListener {
            val intent = Intent(this, MybookDetailedActivity::class.java)
            startActivity(intent)
        }

    }

    private val colorListener = View.OnClickListener {
        when (it.id) {
            viewBinding.btnPurple.id -> color = "purple"
            viewBinding.btnBlue.id -> color = "blue"
            viewBinding.btnPeach.id -> color = "peach"
            viewBinding.btnGreen.id -> color = "green"
            viewBinding.btnYellow.id -> color = "yellow"
            viewBinding.btnPink.id -> color = "pink"
            viewBinding.btnMint.id -> color = "mint"
            viewBinding.btnLime.id -> color = "lime"
    }
    }
}