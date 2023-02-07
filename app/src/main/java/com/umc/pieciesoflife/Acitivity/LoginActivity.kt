package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import com.kakao.usermgmt.StringSet.nickname
import com.umc.pieciesoflife.BottomNavBar.BottomNavBarActivity
import com.umc.pieciesoflife.DataClass.FBUser
import com.umc.pieciesoflife.databinding.ActivityLoginBinding

class LoginActivity: AppCompatActivity() {
    private lateinit var viewBinding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    //앱 처음 실행?
    var isFirst : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // FirebaseAuth 인스턴스 초기화화
        auth = Firebase.auth
        // 임의 설정
        val email = "1@naver.com"
        val password = "123"
        val name = "1st"


        val keyHash = Utility.getKeyHash(this)
        Log.d("Hash", keyHash)

        // 로그인 정보 확인
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                Toast.makeText(this, "토큰 정보 보기 실패", Toast.LENGTH_SHORT).show()
            }
            else if (tokenInfo != null) {
                Toast.makeText(this, "토큰 정보 보기 성공", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, BottomNavBarActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                when {
                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                        Toast.makeText(this, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
                        Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidScope.toString() -> {
                        Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                        Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.ServerError.toString() -> {
                        Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                        Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                    }
                    else -> { // Unknown
                        Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, BottomNavBarActivity::class.java)
                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        finish()
                    }
                }
            }
            else if (token != null) {
                Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, BottomNavBarActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }


        viewBinding.btnLogin.setOnClickListener {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    Toast.makeText(this,"Firebase 회원가입 완료", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "Firebase 회원가입 실패", Toast.LENGTH_SHORT).show()
                }
            }

            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            }else{
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }

    /*
    fun FirebaseJoin() {
        auth.createUserWithEmailAndPassword(email.toString(), password.toString()).addOnCompleteListener(this){ task ->
            if(task.isSuccessful) {
                try {
                    val user = auth.currentUser
                    val userId = user?.uid.toString()
                    // Firebase RealtimeDB에 User 정보 추가
                    FirebaseDatabase.getInstance().getReference("User").child("users")
                        .child(userId).setValue(FBUser(name,userId,email))
                    Toast.makeText(this,"Firebase 회원가입 완료", Toast.LENGTH_SHORT).show()
                    Log.e("UserId", userId)
                }catch (e: Exception){
                    e.printStackTrace()
                }
            } else {
                Toast.makeText(this, "Firebase 회원가입 실패", Toast.LENGTH_SHORT).show()
            }
        }

    }
     */

}



