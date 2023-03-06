package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.kakao.sdk.user.UserApiClient
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.databinding.ActivityDialogUserEditBinding


class DialogUserEditActivity: AppCompatActivity(){

    private lateinit var viewBinding: ActivityDialogUserEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 뒷배경 흐리게
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE) // 팝업창 타이틀 없애기
        window?.setGravity(Gravity.BOTTOM) // 팝업창 아래로

        var profileImgUrl = ""
        var defaultFile = ""
        val userIntent = intent
        val nickname = userIntent.getStringExtra("nickname")

        if(userIntent.getStringExtra("imgProfile")!=null) {
            profileImgUrl = userIntent.getStringExtra("imgProfile")!!
        } else {
            defaultFile = userIntent.getStringExtra("defaultFile")!!
        }

        viewBinding = ActivityDialogUserEditBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // setContentView 이후에 화면 사이즈 구하기
        val dm = applicationContext.resources.displayMetrics
        val width = (dm.widthPixels) // Display 사이즈의 90%
        window.attributes.width = width


        viewBinding.btnProfileEdit.setOnClickListener{
            val intent = Intent(this, UserEditActivity::class.java)
            intent.putExtra("nickname",nickname)
            if(profileImgUrl != "") {
                intent.putExtra("imgProfile", profileImgUrl)
            } else {
                intent.putExtra("defaultFile", R.drawable.ic_default_profileimg)
            }

            startActivity(intent)
        }

        viewBinding.btnLogout.setOnClickListener{
            // 로그아웃
            UserApiClient.instance.logout { error ->
                if (error != null) {
                    Toast.makeText(this, "로그아웃에 실패하였습니다.", Toast.LENGTH_SHORT ).show()
                    finish()
                }
                else {
                    val intent = Intent(this, MainActivity::class.java)
                    Toast.makeText(this, "로그아웃 성공.", Toast.LENGTH_SHORT ).show()
                    ActivityCompat.finishAffinity(this)
                    startActivity(intent)
                }
            }
        }


    }
}


