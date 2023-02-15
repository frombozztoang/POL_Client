package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import com.umc.pieciesoflife.DTO.StoryDto.StoryColor
import com.umc.pieciesoflife.DTO.StoryDto.StoryColorResult
import com.umc.pieciesoflife.DTO.StoryDto.StoryMain
import com.umc.pieciesoflife.DTO.StoryDto.StoryMainResult
import com.umc.pieciesoflife.Retrofit.RetrofitClient
import com.umc.pieciesoflife.Retrofit.RetrofitClient.storyService
import com.umc.pieciesoflife.databinding.ActivityDialogColorBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DialogColorActivity : AppCompatActivity() {
    var color : String = "" // 변경할 색 저장할 변수
    lateinit var storyColorData : StoryColor
    private lateinit var viewBinding: ActivityDialogColorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDialogColorBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(viewBinding.root)

        val itemId = intent.getIntExtra("id",0)

        // 색 변경 버튼
        viewBinding.btnPurple.setOnClickListener{ color = "#cdb5fa"
            storyColorData = StoryColor(color) }
        viewBinding.btnBlue.setOnClickListener { color = "#9dc4f0"
            storyColorData = StoryColor(color) }
        viewBinding.btnPeach.setOnClickListener{ color = "#F2ACAC"
            storyColorData = StoryColor(color)}
        viewBinding.btnGreen.setOnClickListener{ color =  "#b9df98"
            storyColorData = StoryColor(color) }
        viewBinding.btnYellow.setOnClickListener{ color = "#f7d698"
            storyColorData = StoryColor(color)}
        viewBinding.btnPink.setOnClickListener{ color = "#F8B2E8"
            storyColorData = StoryColor(color)}
        viewBinding.btnMint.setOnClickListener { color = "#90DED3"
            storyColorData = StoryColor(color)}
        viewBinding.btnLime.setOnClickListener { color = "#dfe07e"
            storyColorData = StoryColor(color)}


        // 오케이 버튼
        viewBinding.btnOk.setOnClickListener {
            storyService.patchStoryColor("Bearer ${RetrofitClient.jwtToken}", itemId, storyColorData).enqueue(
            object : Callback<StoryColorResult> {
                // 성공 처리
                override fun onResponse(call: Call<StoryColorResult>, response: Response<StoryColorResult>) {
                    if (response.isSuccessful) { // <--> response.code == 200
                        response.body()?.let { it ->
                            Log.d("storyColor", "${response.body()}")
                            Log.d("StoryColor", " publiConfirmAc에서 detailed로 보내기${color}")
                        }
                    }
                }
                override fun onFailure(call: Call<StoryColorResult>, t: Throwable) {
                    // 포스트 실패
                    Log.d("storyColor", "에러입니다. ${t.message}")
                    t.printStackTrace()
                }
            }
        )
        this@DialogColorActivity.finishAffinity()
        Log.d("StoryColor", "다시 Detail로 돌아가기 $color")
     }

        // 취소 버튼
        viewBinding.btnCancel.setOnClickListener {
            this@DialogColorActivity.finishAffinity()
        }

    }
}




