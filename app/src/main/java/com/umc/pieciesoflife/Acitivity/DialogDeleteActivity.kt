package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import com.umc.pieciesoflife.DTO.StoryDto.StoryDelete
import com.umc.pieciesoflife.DTO.StoryDto.StoryDetail
import com.umc.pieciesoflife.GlobalApplication
import com.umc.pieciesoflife.Interface.StoryService
import com.umc.pieciesoflife.Retrofit.RetrofitClient
import com.umc.pieciesoflife.Retrofit.RetrofitClient.storyService
import com.umc.pieciesoflife.databinding.ActivityDialogDeleteBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DialogDeleteActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityDialogDeleteBinding
    var jwtToken = GlobalApplication.prefs.getString("jwtToken", "default-value")
    //lateinit var storyDelete : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDialogDeleteBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(viewBinding.root)

        val itemId = intent.getIntExtra("id", 86) // 호출한 특정 스토리 아이디

        // 삭제 버튼 눌렀을 때
        viewBinding.btnOk.setOnClickListener {
            Log.i("됐냐?","왜안돼 왜!!!")
            storyService.deleteStory(storyId = itemId,
                contentType = "application/json",
                accessToken = "Bearer $jwtToken"
            ).enqueue(object : Callback<Void> {
                // 성공 처리
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if(response.isSuccessful) { // <--> response.code == 200
                        response.body()?.let {
                            //storyDelete = it.data //받아오는 값이 없어서 xx
                            Log.d("제발제발", "StoryDeleteSuccessfully")
                        }
                    }
                    else
                        Log.d("제발제발", "StoryDeleteSuccessfully")
                }
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                    Log.d("제발제발", "DialogDeleteActivity: onFailure 에러: " + t.message.toString());
                }
            }
            )
            Log.i("된거지?","제발좀!!!!!!")


            val intent = Intent(this, DialogDeleteConfirmActivity::class.java)
            startActivity(intent)
        }
        viewBinding.btnCancel.setOnClickListener {
            val intent = Intent(this, MybookDetailedActivity::class.java)
            startActivity(intent)
        }
    }
}