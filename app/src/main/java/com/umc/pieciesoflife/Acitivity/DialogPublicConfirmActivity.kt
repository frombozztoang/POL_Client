package com.umc.pieciesoflife.Acitivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import com.umc.pieciesoflife.DTO.StoryDto.StoryOpen
import com.umc.pieciesoflife.DTO.StoryDto.StoryOpenResult
import com.umc.pieciesoflife.DTO.StoryDto.StoryOpenResultData
import com.umc.pieciesoflife.Retrofit.RetrofitClient.jwtToken
import com.umc.pieciesoflife.Retrofit.RetrofitClient.storyService
import com.umc.pieciesoflife.databinding.ActivityDialogPublicConfirmBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DialogPublicConfirmActivity : AppCompatActivity() {
    private var itemId = 0 // 호출한 특정 스토리 아이디
    private var isOpen : Boolean = false // 메인 여부

    private lateinit var viewBinding: ActivityDialogPublicConfirmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDialogPublicConfirmBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(viewBinding.root)

        itemId = intent.getIntExtra("id", 66) // 호출한 특정 스토리 아이디
        isOpen = intent.getBooleanExtra("isOpen",true ) // 공개 여부


        val storyOpenData = StoryOpen(isOpened = isOpen) // 요청할 isOpened

        viewBinding.btnOk.setOnClickListener {
            storyService.patchStoryOpen("Bearer $jwtToken", itemId, storyOpenData).enqueue(
                object : Callback<StoryOpenResult> {
                    // 성공 처리
                    override fun onResponse(call: Call<StoryOpenResult>, response: Response<StoryOpenResult>) {
                        if (response.isSuccessful) { // <--> response.code == 200
                            response.body()?.let { it ->
                                Log.d("storyOpen", "${response.body()}")
                                Log.d("StoryOpen", " publiConfirmAc에서 detaild로 보내기")
                                // viewBinding.btnLikeDetailed.text = it.data.
                            }
                        }
                    }
                    override fun onFailure(call: Call<StoryOpenResult>, t: Throwable) {
                        // 포스트 실패
                        Log.d("storyOpen", "에러입니다. ${t.message}")
                        t.printStackTrace()
                    }
                }
            )
            this@DialogPublicConfirmActivity.finishAffinity()
        }
    }


    }
