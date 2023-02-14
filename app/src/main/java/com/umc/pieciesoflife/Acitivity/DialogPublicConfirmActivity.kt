package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import com.umc.pieciesoflife.DTO.StoryDto.StoryLike
import com.umc.pieciesoflife.DTO.StoryDto.StoryLikeData
import com.umc.pieciesoflife.DTO.StoryDto.StoryOpen
import com.umc.pieciesoflife.DTO.StoryDto.StoryOpenData
import com.umc.pieciesoflife.Retrofit.RetrofitClient
import com.umc.pieciesoflife.Retrofit.RetrofitClient.jwtToken
import com.umc.pieciesoflife.databinding.ActivityDialogPublicConfirmBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DialogPublicConfirmActivity : AppCompatActivity() {
    private var itemId = 0 // 호출한 특정 스토리 아이디
    private lateinit var viewBinding: ActivityDialogPublicConfirmBinding
    lateinit var storyOpenData: StoryOpenData // 요청할 isLiked
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDialogPublicConfirmBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(viewBinding.root)

        itemId = intent.getIntExtra("id", 66) // 호출한 특정 스토리 아이디
//        storyOpenData.isOpened = intent.getBooleanExtra("isOpened", isOpened)
//        RetrofitClient.storyService.patchStoryOpen("Bearer $jwtToken", itemId, storyOpenData.isOpened).enqueue(
//            object : Callback<StoryOpen> {
//                // 성공 처리
//                override fun onResponse(call: Call<StoryOpen>, response: Response<StoryOpen>) {
//                    if (response.isSuccessful) { // <--> response.code == 200
//                        response.body()?.let { it ->
//                            Log.d("storyOpen", "${response.body()}")
//                            // viewBinding.btnLikeDetailed.text = it.data.
//                        }
//                    }
//                }
//                override fun onFailure(call: Call<StoryOpen>, t: Throwable) {
//                    // 포스트 실패
//                    Log.d("storyOpen", "에러입니다. ${t.message}")
//                    t.printStackTrace()
//                }
//            }
//        )

        viewBinding.btnOk.setOnClickListener {
            val intent = Intent(this, MybookDetailedActivity::class.java)
            startActivity(intent)
        }
    }


    }
