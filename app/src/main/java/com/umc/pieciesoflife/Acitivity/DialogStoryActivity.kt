package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import com.umc.pieciesoflife.databinding.ActivityDialogStoryBinding

class DialogStoryActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityDialogStoryBinding
    private var itemId = 0 // 호출한 특정 스토리 아이디
    private var isMain : Boolean = false // 공개 여부

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDialogStoryBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(viewBinding.root)


        itemId = intent.getIntExtra("id", 86) // 호출한 특정 스토리 아이디
        isMain = intent.getBooleanExtra("isMain",true ) // 공개 여부

        viewBinding.btnOk.setOnClickListener {
            val newIntent = Intent(this, DialogStoryConfirmActivity::class.java)
            newIntent.putExtra("id", itemId)
            newIntent.putExtra("isMain", isMain)
            Log.d("StoryMain", "DialogBottomAc에서 DialogonfirmAc으로 보내기 $isMain")
            startActivity(newIntent)
        }
        viewBinding.btnCancel.setOnClickListener {
            val intent = Intent(this, MybookDetailedActivity::class.java)
            startActivity(intent)
        }
    }
}