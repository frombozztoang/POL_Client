package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umc.pieciesoflife.BottomNavBar.BottomNavBarActivity
import com.umc.pieciesoflife.Fragment.UserFragment
import com.umc.pieciesoflife.databinding.ActivityUserEditBinding

class UserEditActivity:AppCompatActivity() {
    private lateinit var viewBinding: ActivityUserEditBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityUserEditBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // 뒤로가기
        viewBinding.btnUserEditCancel.setOnClickListener {
//            supportFragmentManager.beginTransaction()
//                .replace(viewBinding.userEditFrameLayout.id, UserFragment()).commit()


        }

        // 확인하기 일단 피니시
        viewBinding.btnUserEditOk.setOnClickListener{

        }

    }


}

