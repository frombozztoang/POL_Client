package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.user.UserApiClient
import com.umc.pieciesoflife.BottomNavBar.BottomNavBarActivity
import com.umc.pieciesoflife.databinding.ActivityMainBinding

class MainActivity:AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // 로그인 정보 확인
        // 로그인 기록 없으면 로그인 화면으로 넘어감
        // 로그인 기록 있으면 메인 화면으로 넘어감
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                Handler().postDelayed({
                    Toast.makeText(this, "토큰 정보 보기 실패", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    finish()
                }, DURATION)
            }
            else if (tokenInfo != null) {
                Handler().postDelayed({
                    Toast.makeText(this, "토큰 정보 보기 성공", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, BottomNavBarActivity::class.java)
                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    finish()
                }, DURATION)
            }
        }

    }

    companion  object {
        private const val DURATION : Long = 3000
    }

}