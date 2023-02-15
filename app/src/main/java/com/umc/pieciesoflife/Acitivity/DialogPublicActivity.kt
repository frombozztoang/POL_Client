package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import com.umc.pieciesoflife.databinding.ActivityDialogPublicBinding

class DialogPublicActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityDialogPublicBinding
    private var itemId = 0 // 호출한 특정 스토리 아이디
    private var isOpen : Boolean = false // 공개 여부

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDialogPublicBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(viewBinding.root)

        itemId = intent.getIntExtra("id", 86) // 호출한 특정 스토리 아이디
        isOpen = intent.getBooleanExtra("isOpen",true ) // 공개 여부

        // 문구 수정
        if (isOpen == true) {
            viewBinding.tvMenuOpen.setText("비공개로 설정하시겠습니까?")
        } else viewBinding.tvMenuOpen.setText("공개로 설정하시겠습니까?")

        viewBinding.btnOk.setOnClickListener {
            val newIntent = Intent(this, DialogPublicConfirmActivity::class.java)
            newIntent.putExtra("id", itemId)
            newIntent.putExtra("isOpen", isOpen)
            Log.d("StoryOpen", "DialogBottomAc에서 publiConfirmAc으로 보내기")
            startActivity(newIntent)
        }
        viewBinding.btnCancel.setOnClickListener {
            val intent = Intent(this, MybookDetailedActivity::class.java)
            startActivity(intent)
        }
    }
}