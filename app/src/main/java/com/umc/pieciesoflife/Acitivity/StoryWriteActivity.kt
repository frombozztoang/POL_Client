package com.umc.pieciesoflife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.pieciesoflife.R
import android.widget.ImageButton
import android.content.Intent
import android.view.View
import android.widget.Button
import com.umc.pieciesoflife.TagPersonActivity
import com.umc.pieciesoflife.SaveTitleActivity

class StoryWriteActivity : AppCompatActivity() {
    //private TextView age;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_write)

        /*age=findViewById(R.id.textView48);
        Intent intent_data = getIntent();
        String input=intent_data.getStringExtra("age");
        if (input!=null)
            age.setText(input);*/

        //이 파트가 api 이용해서 답변 저장, 질문 새로고침 해야함. 일단 skip.
        /*Button next = (Button) findViewById(R.id.button_next); //다음 질문 새로고침
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), );
                startActivity(intent); //다음 화면 띄우기
            }
        });*/
        val back = findViewById<View>(R.id.button_back) as ImageButton //뒤로가기
        back.setOnClickListener {
            val intent = Intent(applicationContext, TagPersonActivity::class.java)
            startActivity(intent) //다음 Tag 화면 띄우기
        }
        val stop = findViewById<View>(R.id.button_finish) as Button //이야기 그만하기
        stop.setOnClickListener {
            val intent = Intent(applicationContext, SaveTitleActivity::class.java)
            startActivity(intent) //다음 화면 띄우기
        }
        val skip = findViewById<View>(R.id.button_skip_question) as Button //이야기 건너뛰기
        skip.setOnClickListener {
            val intent = Intent(applicationContext, SaveTitleActivity::class.java)
            startActivity(intent) //다음 화면 띄우기
        }
    }
}