package com.umc.pieciesoflife.Acitivity

import android.content.ContentValues
import android.content.ContentValues.TAG
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
import com.umc.pieciesoflife.KakaoLogin.OAuthTokenResponse
import com.umc.pieciesoflife.KakaoLogin.OAuthTokenService
import com.umc.pieciesoflife.Retrofit.RetrofitClient
import com.umc.pieciesoflife.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class LoginActivity: AppCompatActivity() {
    private lateinit var viewBinding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    //앱 처음 실행?
    var isFirst : Boolean = true

    // auth/kakao
    private var accessToken = ""

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

//        // 로그인 정보 확인
//        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
//            if (error != null) {
//                Toast.makeText(this, "토큰 정보 보기 실패", Toast.LENGTH_SHORT).show()
//            }
//            else if (tokenInfo != null) {
//                Toast.makeText(this, "토큰 정보 보기 성공", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this, BottomNavBarActivity::class.java)
//                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//                finish()
//            }
//        }

        // 로그인 성공 후 토큰 발급
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
                    }
                }
            }
            else if (token != null) {
                Toast.makeText(this, "로그인에 성공하였습니다. accessToken : ${token.accessToken}", Toast.LENGTH_SHORT).show()
                Log.d("accessToken",  token.accessToken)
                accessToken = token.accessToken
                val intent = Intent(this, BottomNavBarActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }

        // 로그인하기 버튼 클릭 시 카카오톡 설치 유무에 따라서 카카오톡으로 로그인, 카카오 계정으로 로그인
        viewBinding.btnLogin.setOnClickListener {
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            }else{
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }

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

        // 토큰 정보 보기
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                Log.d("Token", "토큰 정보 보기 실패", error)
            }
            else if (tokenInfo != null) {
                Log.d("Token", "토큰 정보 보기 성공" +
                        "\n회원번호: ${tokenInfo.id}" +
                        "\n만료시간: ${tokenInfo.expiresIn} 초")
            }
        }

        // auth/kakao retrofit
            val tokenService = RetrofitClient.TokenService
            tokenService.postAuth("Bearer $accessToken").enqueue(object : Callback<OAuthTokenResponse> {
                // 전송 실패
                override fun onFailure(call: Call<OAuthTokenResponse>, t: Throwable) {
                    Log.d("kakao", t.message!!)
                }

                // 전송 성공
                override fun onResponse(
                    call: Call<OAuthTokenResponse>,
                    response: Response<OAuthTokenResponse>
                ) {
                    val result = response.body()
                    Log.d("kakao", " ${result}")
                    Log.i(javaClass.simpleName, "api 받아오기 성공 : ${response.body()?.data}")

                    Log.d("kakao", "response : ${response.body()?.data}") // 정상출력

                    // 전송은 성공 but 서버 4xx 에러
                    Log.d("kakao: 에러바디", "response : ${response.errorBody()}")
                    Log.d("kakao: 메시지", "response : ${response.message()}")
                    Log.d("kakao: 코드", "response : ${response.code()}")
                }
            })



    }

}



