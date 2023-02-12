package com.umc.pieciesoflife.Acitivity

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.umc.pieciesoflife.BottomNavBar.BottomNavBarActivity
import com.umc.pieciesoflife.Fragment.MyBookFragment
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.databinding.ActivityBottomNavBarBinding
import com.umc.pieciesoflife.databinding.ActivitySaveFinalBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SaveFinalActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySaveFinalBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySaveFinalBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        var bookTitle = intent.getSerializableExtra("bookTitle") as String
        var bookIntro = intent.getSerializableExtra("bookIntro") as String
        var bookColor = intent.getSerializableExtra("bookColor") as String
        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        val date: LocalDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val formatted_date = date.format(formatter)

        //표지 색상 변경
        viewBinding.bookPost.setImageResource(R.drawable.ic_book)
        viewBinding.bookPost.setColorFilter(Color.parseColor(bookColor))

        //표지 정보 변경
        //제목
        viewBinding.bookPostTitle.setText(bookTitle)
        viewBinding.textViewTitle.setText(bookTitle)
        //날짜
        viewBinding.textViewDate.setText(formatted_date)
        //한줄요약
        viewBinding.textViewIntro.setText(bookIntro)

        // 자서전 완성 시, 다시 MyBookFragment 혹은 UserFragment(새로운 이야기 작성하기 시작했던 곳)로 복귀
        viewBinding.buttonFinish.setOnClickListener {
            // MyBookFragment에서 새로운 이야기 작성하기 이후에 있는 Activity들을 함께 종료
            this@SaveFinalActivity.finishAffinity()
//            transaction.replace(R.id.mainFrameLayout, MyBookFragment()).commit()
        }
    }
}