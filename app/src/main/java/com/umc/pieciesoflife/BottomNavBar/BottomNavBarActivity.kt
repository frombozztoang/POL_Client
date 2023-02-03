package com.umc.pieciesoflife.BottomNavBar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.umc.pieciesoflife.Fragment.HomeFragment
import com.umc.pieciesoflife.Fragment.MyBookFragment
import com.umc.pieciesoflife.Fragment.UserFragment
import com.umc.pieciesoflife.Fragment.ExploreFragment
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.databinding.ActivityBottomNavBarBinding

class BottomNavBarActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBottomNavBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

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