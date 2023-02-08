package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.umc.pieciesoflife.databinding.ActivityChatSendBinding

class ChatSendActivity: AppCompatActivity() {
    private lateinit var viewBinding: ActivityChatSendBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityChatSendBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // FirebaseAuth 인스턴스 초기화
        auth = Firebase.auth
        // 임의 설정
        val email = "1@naver.com"
        val password = "123"
        val name = "1st"

        viewBinding.buttonBack.setOnClickListener {
            finish()
        }

        viewBinding.buttonChatSend.setOnClickListener {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    Toast.makeText(this,"Firebase 회원가입 완료", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "Firebase 회원가입 실패", Toast.LENGTH_SHORT).show()
                }
            }
            finish()
           // Toast.makeText(this, "쪽지 발신 성공!", Toast.LENGTH_SHORT ).show()
            //이 때, Spring Server에 UserID, StoryID POST
            //Firebase에도 User, StoryID, 쪽지 내용, 시간 POST
        }

    }

}