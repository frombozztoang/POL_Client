package com.umc.pieciesoflife.Acitivity

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import com.umc.pieciesoflife.DTO.StoryDto.StoryPost
import com.umc.pieciesoflife.DTO.StoryDto.StoryPostResult
import com.umc.pieciesoflife.DTO.StoryDto.StoryQna
import com.umc.pieciesoflife.DTO.StoryDto.StoryTag
import com.umc.pieciesoflife.GlobalApplication
import com.umc.pieciesoflife.Interface.StoryService
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.Retrofit.RetrofitClient
import com.umc.pieciesoflife.databinding.ActivitySaveFinalBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SaveFinalActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySaveFinalBinding
    var jwtToken = GlobalApplication.prefs.getString("jwtToken", "default-value")
    private var storyQnaList: ArrayList<StoryQna> = arrayListOf()
    private var storyTagList: ArrayList<StoryTag> = arrayListOf()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySaveFinalBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        storyQnaList = intent.getSerializableExtra("storyQnaList") as ArrayList<StoryQna>
        storyTagList = intent.getSerializableExtra("storyTagList") as ArrayList<StoryTag>
        var bookTitle = intent.getSerializableExtra("bookTitle") as String
        var bookIntro = intent.getSerializableExtra("bookIntro") as String
        var bookColor = intent.getSerializableExtra("bookColor") as String
        val manager: FragmentManager = supportFragmentManager
      //  val transaction: FragmentTransaction = manager.beginTransaction()
        val date: LocalDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val formatted_date = date.format(formatter)


        //표지 정보 변경
        //표지 색상 변경
        viewBinding.bookPost.setImageResource(R.drawable.ic_book)
        viewBinding.bookPost.setColorFilter(Color.parseColor(bookColor))
        //제목
        viewBinding.bookPostTitle.setText(bookTitle)
        viewBinding.textViewTitle.setText(bookTitle)
        //날짜
        viewBinding.textViewDate.setText(formatted_date)
        //한줄요약
        viewBinding.textViewIntro.setText(bookIntro)

        //서버 이야기 생성 포스트
        val storyData = StoryPost(bookTitle, bookIntro, bookColor, storyQnaList, storyTagList)
        Log.i("storydata","$storyData") //dto 확인


        // 자서전 완성 시, 다시 MyBookFragment 혹은 UserFragment(새로운 이야기 작성하기 시작했던 곳)로 복귀
        viewBinding.buttonFinish.setOnClickListener {
            // StoryService 객체 생성
            val call: StoryService = RetrofitClient.storyService
            call.postStory("application/json","Bearer $jwtToken", storyData).enqueue(object: Callback<StoryPostResult> {
                override fun onResponse(call: Call<StoryPostResult>, response: Response<StoryPostResult>) {
                    if (response.isSuccessful) {
                        // 포스트 성공
                        Log.d("storyPost","${response.body()}")
                    }
                }
                override fun onFailure(call: Call<StoryPostResult>, t: Throwable) {
                    // 포스트 실패
                    Log.d("storyPost", "에러입니다. ${t.message}")
                    t.printStackTrace()
                }
            })
            // MyBookFragment에서 새로운 이야기 작성하기 이후에 있는 Activity들을 함께 종료
            this@SaveFinalActivity.finishAffinity()
//            transaction.replace(R.id.mainFrameLayout, MyBookFragment()).commit()
        }
    }
}