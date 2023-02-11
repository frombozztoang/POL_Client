package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.umc.pieciesoflife.Adapter.BookDetailRVAdapter
import com.umc.pieciesoflife.DataClass.BookDetail
import com.umc.pieciesoflife.Fragment.HomeFragment
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.databinding.ActivityMybookDetailedBinding


class MybookDetailedActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMybookDetailedBinding
    private var count : Int = 46 // 좋아요 수
    private var newColor : String? = "" // 자서전 배경색

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMybookDetailedBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        viewBinding.btnBack.setOnClickListener {
            finish()
        }
        viewBinding.btnMenu.setOnClickListener {
            val intent = Intent(this, DialogBottomAcitivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("color", newColor)
            startActivity(intent)
        }

        // DialogColorActivity로부터 배경색 intent 받아와서 적용하기
        newColor = intent.getStringExtra("color")

        if (newColor == "purple")
            viewBinding.constraintLayout2.setBackgroundResource(R.drawable.color_gradient_purple)
        else if (newColor == "blue")
            viewBinding.constraintLayout2.setBackgroundResource(R.drawable.color_gradient_blue)
        else if (newColor == "peach")
            viewBinding.constraintLayout2.setBackgroundResource(R.drawable.color_gradient_peach)
        else if (newColor == "green")
            viewBinding.constraintLayout2.setBackgroundResource(R.drawable.color_gradient_green)
        else if (newColor == "yellow")
            viewBinding.constraintLayout2.setBackgroundResource(R.drawable.color_gradient_yellow)
        else if (newColor == "pink")
            viewBinding.constraintLayout2.setBackgroundResource(R.drawable.color_gradient_pink)
        else if (newColor == "mint")
            viewBinding.constraintLayout2.setBackgroundResource(R.drawable.color_gradient_mint)
        else if (newColor == "lime")
            viewBinding.constraintLayout2.setBackgroundResource(R.drawable.color_gradient_lime)


        // 좋아요 버튼 클릭 이벤트
        viewBinding.btnLikeDetailed.text = count.toString() // 좋아요 개수 서버에서 받아오기(나중에 구현)

        viewBinding.btnLikeDetailed.setOnClickListener {
            if ( viewBinding.btnLikeDetailed.isSelected == true )  count -= 1
            else count += 1
            viewBinding.btnLikeDetailed.isSelected = viewBinding.btnLikeDetailed.isSelected == false
            viewBinding.btnLikeDetailed.text = count.toString()
        }

        //RV_Deatiled 리사이클러뷰
        val bookDetailList: ArrayList<BookDetail> = arrayListOf()

        bookDetailList.apply{
            add(BookDetail("질문1", "답변1"))
            add(BookDetail("질문2", "답변2"))
            add(BookDetail("질문3", "답변3"))
            add(BookDetail("질문4", "답변4"))
        }

        val bookDetailAdapter = BookDetailRVAdapter(bookDetailList)

        viewBinding.rvDetailed.adapter = bookDetailAdapter
        viewBinding.rvDetailed.layoutManager = LinearLayoutManager(this)
    }

}