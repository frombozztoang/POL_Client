package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import com.umc.pieciesoflife.databinding.ActivityDialogColorBinding

class DialogColorActivity : AppCompatActivity() {
    var color : String = ""

    private lateinit var viewBinding: ActivityDialogColorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDialogColorBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(viewBinding.root)

        viewBinding.btnPurple.setOnClickListener( colorListener )

        viewBinding.btnOk.setOnClickListener {

            val intent = Intent(this, MybookDetailedActivity::class.java)
//            intent.putExtra("color", color)
            startActivity(intent)
        }
        viewBinding.btnCancel.setOnClickListener {
            val intent = Intent(this, MybookDetailedActivity::class.java)
            startActivity(intent)
        }

    }

    private val colorListener = View.OnClickListener {
        when (it.id) {
            viewBinding.btnPurple.id -> color = "@color/color_gradient_purple"
    }


    }
}