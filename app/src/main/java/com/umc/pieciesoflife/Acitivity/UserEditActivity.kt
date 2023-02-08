package com.umc.pieciesoflife.Acitivity

import android.R
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.umc.pieciesoflife.BottomNavBar.BottomNavBarActivity
import com.umc.pieciesoflife.Fragment.MyBookFragment
import com.umc.pieciesoflife.Fragment.UserFragment
import com.umc.pieciesoflife.databinding.ActivityBottomNavBarBinding
import com.umc.pieciesoflife.databinding.ActivityUserEditBinding


class UserEditActivity:AppCompatActivity() {
    private lateinit var viewBinding: ActivityUserEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityUserEditBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // 뒤로가기
        viewBinding.btnUserEditCancel.setOnClickListener {
            this@UserEditActivity.finishAffinity()
    }

        // 확인하기 일단 피니시
        viewBinding.btnUserEditOk.setOnClickListener{
            this@UserEditActivity.finishAffinity()
            Toast.makeText(this, "프로필이 변경됐다능", Toast.LENGTH_SHORT ).show()
        }

    }



}

