package com.umc.pieciesoflife.BottomNavBar

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.kakao.sdk.user.UserApiClient
import com.umc.pieciesoflife.Fragment.HomeFragment
import com.umc.pieciesoflife.Fragment.MyBookFragment
import com.umc.pieciesoflife.Fragment.UserFragment
import com.umc.pieciesoflife.Fragment.ExploreFragment
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.databinding.ActivityBottomNavBarBinding

class BottomNavBarActivity : AppCompatActivity() {
    lateinit var binding : ActivityBottomNavBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 사용자 정보 요청 (기본)
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e(ContentValues.TAG, "사용자 정보 요청 실패", error)
            }
            else if (user != null) {
                Log.d("userInfo", "사용자 정보 요청 성공" +
                        "\n회원번호: ${user.id}" +
                        "\n이메일: ${user.kakaoAccount?.email}" +
                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                        "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")
            }
        }


        initNavigationBar() //네이게이션 바의 각 메뉴 탭을 눌렀을 때 화면이 전환되도록 하는 함수
        
    }

    fun initNavigationBar() {
        binding.navigationView.run {
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.homeFragment -> {
                        changeFragment(HomeFragment())
                    }
                    R.id.searchFragment -> {
                        changeFragment(ExploreFragment())
                    }
                    R.id.myBookFragment -> {
                        changeFragment(MyBookFragment())
                    }
                    R.id.myPageFragment-> {
                        changeFragment(UserFragment())
                    }

                }
                true
            }
            selectedItemId = R.id.homeFragment
        }
    }

    fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.mainFrameLayout.id, fragment).commit()
    }

}