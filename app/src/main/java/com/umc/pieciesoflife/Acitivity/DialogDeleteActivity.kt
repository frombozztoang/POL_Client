package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import com.umc.pieciesoflife.DTO.StoryDto.StoryDelete
import com.umc.pieciesoflife.DTO.StoryDto.StoryDetail
import com.umc.pieciesoflife.Interface.StoryService
import com.umc.pieciesoflife.Retrofit.RetrofitClient
import com.umc.pieciesoflife.Retrofit.RetrofitClient.storyService
import com.umc.pieciesoflife.databinding.ActivityDialogDeleteBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DialogDeleteActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityDialogDeleteBinding

    lateinit var storyDelete : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDialogDeleteBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(viewBinding.root)

        // 삭제 버튼 눌렀을 때
        viewBinding.btnOk.setOnClickListener {
            storyService.deleteStory(1).enqueue(object : Callback<StoryDelete> {
                // 성공 처리
                override fun onResponse(call: Call<StoryDelete>, response: Response<StoryDelete>) {
                    if(response.isSuccessful) { // <--> response.code == 200
                        response.body()?.let {
                            storyDelete = it.data
                            Log.d("testttt", "DialogDeleteActivity:$storyDelete")
                        }
                    }
                }
                override fun onFailure(call: Call<StoryDelete>, t: Throwable) {
                    // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                    Log.d("testttt", "DialogDeleteActivity: onFailure 에러: " + t.message.toString());
                }
            }
            )


            val intent = Intent(this, DialogDeleteConfirmActivity::class.java)
            startActivity(intent)
        }
        viewBinding.btnCancel.setOnClickListener {
            val intent = Intent(this, MybookDetailedActivity::class.java)
            startActivity(intent)
        }
    }
}