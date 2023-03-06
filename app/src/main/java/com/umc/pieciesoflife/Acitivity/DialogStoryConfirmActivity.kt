package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import com.umc.pieciesoflife.DTO.StoryDto.StoryMain
import com.umc.pieciesoflife.DTO.StoryDto.StoryMainResult
import com.umc.pieciesoflife.DTO.StoryDto.StoryOpen
import com.umc.pieciesoflife.DTO.StoryDto.StoryOpenResult
import com.umc.pieciesoflife.Retrofit.RetrofitClient
import com.umc.pieciesoflife.databinding.ActivityDialogStoryConfirmBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DialogStoryConfirmActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityDialogStoryConfirmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDialogStoryConfirmBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(viewBinding.root)

        val itemId = intent.getIntExtra("id", 86) // 호출한 특정 스토리 아이디
        val isMain = intent.getBooleanExtra("isMain",true ) // 공개 여부

        // 문구 수정
        if (isMain == true) {
            viewBinding.tvMenuDelete.setText("대표 이야기에서 취소하였습니다.")
        } else viewBinding.tvMenuDelete.setText("대표 이야기로 지정되었습니다.")

        val storyMainData = StoryMain(isMain) // 요청할 isOpened

        viewBinding.btnOk.setOnClickListener {
            RetrofitClient.storyService.patchStoryMain("Bearer ${RetrofitClient.jwtToken}", itemId, storyMainData).enqueue(
                object : Callback<StoryMainResult> {
                    // 성공 처리
                    override fun onResponse(call: Call<StoryMainResult>, response: Response<StoryMainResult>) {
                        if (response.isSuccessful) { // <--> response.code == 200
                            response.body()?.let { it ->
                                Log.d("storyMain", "${response.body()}")
                                Log.d("StoryMain", " publiConfirmAc에서 detailed로 보내기")
                            }
                        }
                    }
                    override fun onFailure(call: Call<StoryMainResult>, t: Throwable) {
                        // 포스트 실패
                        Log.d("storyMain", "에러입니다. ${t.message}")
                        t.printStackTrace()
                    }
                }
            )
            this@DialogStoryConfirmActivity.finishAffinity()

            Log.d("StoryMain", "다시 Detail로 돌아가기 $isMain")
        }
    }
}