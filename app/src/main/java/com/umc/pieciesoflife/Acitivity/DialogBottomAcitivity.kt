package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.umc.pieciesoflife.databinding.ActivityDialogBottomBinding

class DialogBottomAcitivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityDialogBottomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        supportRequestWindowFeature(Window.FEATURE_NO_TITLE) // 팝업창 타이틀 없애기
        window?.setGravity(Gravity.BOTTOM) // 팝업창 아래로


        viewBinding = ActivityDialogBottomBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val itemId = intent.getIntExtra("id", 86) // 호출한 특정 스토리 아이디

        // setContentView 이후에 화면 사이즈 구하기
        val dm = applicationContext.resources.displayMetrics
        val width = (dm.widthPixels) // Display 사이즈의 90%
        window.attributes.width = width


        viewBinding.btnStory.setOnClickListener {
            val intent = Intent(this, DialogStoryActivity::class.java)
            startActivity(intent)
        }

        viewBinding.btnPublic.setOnClickListener {
            val intent = Intent(this, DialogPublicActivity::class.java)
            startActivity(intent)
        }

        viewBinding.btnColor.setOnClickListener {
            val intent = Intent(this, DialogColorActivity::class.java)
            startActivity(intent)
        }

        viewBinding.btnDelete.setOnClickListener {
            val intent = Intent(this, DialogDeleteActivity::class.java)
            intent.putExtra("id", itemId)
            startActivity(intent)
        }
    }

}