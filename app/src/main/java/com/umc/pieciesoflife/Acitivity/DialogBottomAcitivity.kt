package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
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
        val isMain = intent.getBooleanExtra("isMain",false ) // 메인 여부
        val isOpen = intent.getBooleanExtra("isOpen",true ) // 공개 여부


        // setContentView 이후에 화면 사이즈 구하기
        val dm = applicationContext.resources.displayMetrics
        val width = (dm.widthPixels) // Display 사이즈의 90%
        window.attributes.width = width

        // 상태에 따라 메뉴 이름 바꾸기
        if (isMain == true) {
            viewBinding.btnStory.setText("대표 이야기 취소")
        } else viewBinding.btnStory.setText("대표 이야기로 변경")

        if (isOpen == true) {
            viewBinding.btnPublic.setText("비공개로 변경")
        } else viewBinding.btnPublic.setText("공개로 변경")


        viewBinding.btnStory.setOnClickListener {
            val newIntent = Intent(this, DialogStoryActivity::class.java)
            newIntent.putExtra("id", itemId)
            newIntent.putExtra("isMain", isMain)
            Log.d("StoryMain", "DialogBottomAc에서 publicAc으로 보내기 $itemId $isMain")
            startActivity(newIntent)
        }

        viewBinding.btnPublic.setOnClickListener {
            val newIntent = Intent(this, DialogPublicActivity::class.java)
            newIntent.putExtra("id", itemId)
            newIntent.putExtra("isOpen", isOpen)
            Log.d("StoryOpen", "DialogBottomAc에서 publicAc으로 보내기 $itemId $isOpen")
            startActivity(newIntent)
        }

        viewBinding.btnColor.setOnClickListener {
            val newIntent = Intent(this, DialogColorActivity::class.java)
            newIntent.putExtra("id", itemId)
            Log.d("StoryColor", "DialogBottomAc에서 publicAc으로 보내기 $itemId")
            startActivity(newIntent)
        }

        viewBinding.btnDelete.setOnClickListener {
            val intent = Intent(this, DialogDeleteActivity::class.java)
            intent.putExtra("id", itemId)
            startActivity(intent)
        }
    }

}