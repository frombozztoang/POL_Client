package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.pieciesoflife.Adapter.BookDetailRVAdapter
import com.umc.pieciesoflife.DataClass.BookDetail
import com.umc.pieciesoflife.Fragment.HomeFragment
import com.umc.pieciesoflife.databinding.ActivityMybookDetailedBinding


class MybookDetailedActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMybookDetailedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMybookDetailedBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnBack.setOnClickListener {
            finish()
        }
        viewBinding.btnWrite.setOnClickListener {
            val intent = Intent(this, StoryWriteActivity::class.java)
            startActivity(intent)
        }
        viewBinding.btnMenu.setOnClickListener {

            val intent = Intent(this, DialogBottomAcitivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

        }
        viewBinding.btnLikeDetailed.setOnClickListener {
            viewBinding.btnLikeDetailed.isSelected = viewBinding.btnLikeDetailed.isSelected == false
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