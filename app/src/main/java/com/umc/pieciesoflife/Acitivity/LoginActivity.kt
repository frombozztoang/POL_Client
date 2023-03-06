package com.umc.pieciesoflife.Acitivity

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import com.umc.pieciesoflife.BottomNavBar.BottomNavBarActivity
import com.umc.pieciesoflife.DTO.KakaoDto.Kakao
import com.umc.pieciesoflife.DTO.UserDto.User
import com.umc.pieciesoflife.GlobalApplication
import com.umc.pieciesoflife.Interface.KakaoService
import com.umc.pieciesoflife.Retrofit.RetrofitClient
import com.umc.pieciesoflife.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity: AppCompatActivity() {
    private lateinit var viewBinding: ActivityLoginBinding
    //앱 처음 실행?
    var isFirst : Boolean = true

    // auth/kakao
    var accessToken = ""
    var profileImgUrl = ""
    var ninkname = ""
    var jwtToken = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


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

            // 로그인 성공
            else if (token != null) {
                Toast.makeText(this, "로그인에 성공하였습니다. accessToken : ${token.accessToken}", Toast.LENGTH_SHORT).show()

                // accessToken 발급, 저장
                Log.d("accessToken",  token.accessToken)
                accessToken = token.accessToken
                GlobalApplication.prefs.setString("accessToken",accessToken)

                // user 프로필, 닉네임 저장
                UserApiClient.instance.me { user, error ->
                    if (error != null) {
                        Log.e(ContentValues.TAG, "사용자 정보 요청 실패", error)
                    }
                    else if (user != null) {
                        Log.d("userInfo", "사용자 정보 요펑 성공")
                        profileImgUrl = user.kakaoAccount?.profile?.thumbnailImageUrl.toString()
                        ninkname = user.kakaoAccount?.profile?.nickname.toString()
                        Log.d("Save User Info ", "프로필 : $profileImgUrl \n닉네임 : $ninkname")

                    }
                }

                // auth/kakao jwt token 발급, 저장
                val call : KakaoService =  RetrofitClient.kakaoService
                call.getAuth(accessToken).enqueue(object : Callback<Kakao> {
                    // 전송 실패
                    override fun onFailure(call: Call<Kakao>, t: Throwable) {
                        Log.d("kakao", t.message!!)
                    }

                    // 전송 성공
                    override fun onResponse(call: Call<Kakao>, response: Response<Kakao>) {
                        response.body()?.let {
                            Log.d("auth/kakao 받아오기 성공 ", "${response.body()}")

                            jwtToken = "${response.body()?.data?.accessToken}"
                            GlobalApplication.prefs.setString("jwtToken",jwtToken)

                        }?: Log.d("response body null", "LoginActivity jwtToken 받아오기 실패!!!")
                    }
                })

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
    }

}



