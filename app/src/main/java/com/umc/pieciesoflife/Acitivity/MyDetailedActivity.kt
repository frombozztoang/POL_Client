package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.umc.pieciesoflife.Fragment.HomeFragment
import com.umc.pieciesoflife.Fragment.SearchFragment
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.databinding.ActivityMyDetailedBinding


class MyDetailedActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMyDetailedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMyDetailedBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnBack.setOnClickListener {
            val intent = Intent(this, HomeFragment::class.java)
            startActivity(intent)
        }
        viewBinding.btnWrite.setOnClickListener {
            val intent = Intent(this, StartNewstoryAcitivity::class.java)
            startActivity(intent)
        }
        viewBinding.btnMenu.setOnClickListener {
            val intent = Intent(this, BottomSheetActivity::class.java)
            startActivity(intent)
        }
        viewBinding.btnLikeDetailed.setOnClickListener {
            // 좋아요 구현
        }
    }

}