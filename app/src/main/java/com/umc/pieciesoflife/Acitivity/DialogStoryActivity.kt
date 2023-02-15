package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import com.umc.pieciesoflife.databinding.ActivityDialogStoryBinding

class DialogStoryActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityDialogStoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDialogStoryBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(viewBinding.root)

        val itemId = intent.getIntExtra("id", 86) // 호출한 특정 스토리 아이디
        val isMain = intent.getBooleanExtra("isMain",true ) // 공개 여부

        // 문구 수정
        if (isMain == true) {
            viewBinding.tvMenuStory.setText("대표 이야기를 취소하시겠습니까?")
        } else viewBinding.tvMenuStory.setText("대표 이야기로 지정하시겠습니까?")

        viewBinding.btnOk.setOnClickListener {
            val newIntent = Intent(this, DialogStoryConfirmActivity::class.java)
            newIntent.putExtra("id", itemId)
            newIntent.putExtra("isMain", isMain)
            Log.d("StoryMain", "DialogStoryAc에서 DialogonfirmAc으로 보내기 $isMain")
            startActivity(newIntent)
        }
        viewBinding.btnCancel.setOnClickListener {
            val intent = Intent(this, MybookDetailedActivity::class.java)
            startActivity(intent)
        }
    }
}