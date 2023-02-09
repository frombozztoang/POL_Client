package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.Spanned
import android.text.TextUtils.concat
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.umc.pieciesoflife.DTO.Question
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.Retrofit.RetrofitClient
import com.umc.pieciesoflife.databinding.ActivityStoryWriteBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StoryWriteActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityStoryWriteBinding
    var mspanable: Spannable? = null
    var hashTagIsComing = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_write)
        viewBinding = ActivityStoryWriteBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

       // val num: Long = 1
        var question: Question
        val result = MutableLiveData<Question>()
        val call = RetrofitClient.questionService

        call.request(1).enqueue(object: Callback<Question>{
            // 성공 처리
            override fun onResponse(call: Call<Question>, response: Response<Question>) {
                if(response.isSuccessful()) { // <--> response.code == 200
                    //result.value = response.body()
                    //Log.d("testt", "결과는 ${result.value}")
                    question = response.body() as Question
                    Log.d("testt", "결과는 "+question)
                    Log.d("testt",response.toString())
                    Log.d("testt", response.body().toString())
                //    Log.i(javaClass.simpleName, "api 받아오기 성공 : ${response.body()?.questionTemplate}")
                }
            }
            // 실패 처리
            override fun onFailure(call: Call<Question>, t: Throwable) {
                t.message?.let { Log.e("QUESTIONTest", it) }
                Log.d("testt", "에러입니다. ${t.message}")
                t.printStackTrace()
            }
        })

        //태그 담을 리스트
        val ageTagList: ArrayList<String> = arrayListOf<String>()
        val matterTagList: ArrayList<String> = arrayListOf<String>()
        val moodTagList: ArrayList<String> = arrayListOf<String>()
        val objectTagList: ArrayList<String> = arrayListOf<String>()
        val personTagList: ArrayList<String> = arrayListOf<String>()
        val placeTagList: ArrayList<String> = arrayListOf<String>()
        val yearTagList: ArrayList<String> = arrayListOf<String>()

        var str = "" //리스트에 입력할 문자열

        //select tag view 보이지 않기
        setTagInvisible()

        val edtTxtMine = findViewById<View>(R.id.editTextTextMultiLine_write_story) as EditText
        mspanable = edtTxtMine.text
        //스팬을 이용하여 태그 색깔 바꾸기
        edtTxtMine.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                var startChar: String? = null
                try {
                    startChar = Character.toString(s[start])
                    Log.i(javaClass.simpleName, "CHARACTER OF NEW WORD: $startChar")
                } catch (ex: Exception) {
                    startChar = ""
                }
                Log.i(javaClass.simpleName, "Start: $start count: $count")

                if (startChar == "#") {
                    //태그 선택 화면 보이기
                    setTagVisible()
                    changeTheColorDefault(s.toString().substring(start), start, start + count)
                    hashTagIsComing++
                }
                if (startChar == " ") { //공백 문자가 나올때까지 태그로 인식
                    //카운트 초기화
                    hashTagIsComing = 0
                }
                if (hashTagIsComing != 0) {
                    str= concat(str,startChar) as String
                    changeTheColorDefault(s.toString().substring(start), start, start + count)
                    hashTagIsComing++

                    //태그 버튼 선택 ->리스트에 인풋&글자 색 바꾸기&태그 선택화면 내리기
                    viewBinding.tagAgeButton.setOnClickListener { //나이 버튼 선택
                        //문자열 색깔 바꾸기
                        changeAgeColor(s.toString().substring(start), start-1, start + count)
                        //리스트에 문자열 입력하기
                        str=edtTxtMine.text.toString().substring(start,start+count) //str 문자열 자르기
                        Log.i(javaClass.simpleName,"str :$str") //str 문자열 잘 받아와졌는지 확인
                        ageTagList.add(str)
                        str="" //str 초기화
                        setTagInvisible()
                        hashTagIsComing = 0
                    }
                    viewBinding.tagMatterButton.setOnClickListener { //상황 버튼 선택
                        changeMatterColor(s.toString().substring(start), start-1, start + count)
                        str=edtTxtMine.text.toString().substring(start,start+count)
                        Log.i(javaClass.simpleName,"str :$str")
                        matterTagList.add(str)
                        str=""
                        setTagInvisible()
                        hashTagIsComing = 0
                    }
                    viewBinding.tagMoodButton.setOnClickListener { //감정 버튼 선택
                        changeMoodColor(s.toString().substring(start), start-1, start + count)
                        str=edtTxtMine.text.toString().substring(start,start+count) //str 문자열 자르기
                        Log.i(javaClass.simpleName,"str :$str") //str 문자열 잘 받아와졌는지 확인
                        moodTagList.add(str)
                        str="" //str 초기화
                        setTagInvisible()
                        hashTagIsComing = 0
                    }
                    viewBinding.tagObjectButton.setOnClickListener { //물건 버튼 선택
                        changeObjectColor(s.toString().substring(start), start-1, start + count)
                        str=edtTxtMine.text.toString().substring(start,start+count) //str 문자열 자르기
                        Log.i(javaClass.simpleName,"str :$str") //str 문자열 잘 받아와졌는지 확인
                        objectTagList.add(str)
                        str="" //str 초기화
                        setTagInvisible()
                        hashTagIsComing = 0
                    }
                    viewBinding.tagPersonButton.setOnClickListener { //인물  버튼 선택
                        changePersonColor(s.toString().substring(start), start-1, start + count)
                        str=edtTxtMine.text.toString().substring(start,start+count) //str 문자열 자르기
                        Log.i(javaClass.simpleName,"str :$str") //str 문자열 잘 받아와졌는지 확인
                        personTagList.add(str)
                        str="" //str 초기화
                        setTagInvisible()
                        hashTagIsComing = 0
                    }
                    viewBinding.tagPlaceButton.setOnClickListener { //장소 버튼 선택
                        changePlaceColor(s.toString().substring(start), start-1, start + count)
                        str=edtTxtMine.text.toString().substring(start,start+count) //str 문자열 자르기
                        Log.i(javaClass.simpleName,"str :$str") //str 문자열 잘 받아와졌는지 확인
                        placeTagList.add(str)
                        str="" //str 초기화
                        setTagInvisible()
                        hashTagIsComing = 0
                    }
                    viewBinding.tagYearButton.setOnClickListener { //연도 버튼 선택
                        changeYearColor(s.toString().substring(start), start-1, start + count)
                        str=edtTxtMine.text.toString().substring(start,start+count) //str 문자열 자르기
                        Log.i(javaClass.simpleName,"str :$str") //str 문자열 잘 받아와졌는지 확인
                        yearTagList.add(str)
                        str="" //str 초기화
                        setTagInvisible()
                        hashTagIsComing = 0
                    }
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {
                // TODO Auto-generated method stub
            }
        })

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

        val next = findViewById<View>(R.id.button_next) as Button //다음 질문 생성버튼
        next.setOnClickListener {

        }
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
    fun setTagVisible(){
        viewBinding.selectTagView.visibility=View.VISIBLE
        viewBinding.tagAgeButton.visibility=View.VISIBLE
        viewBinding.tagMatterButton.visibility=View.VISIBLE
        viewBinding.tagMoodButton.visibility=View.VISIBLE
        viewBinding.tagObjectButton.visibility=View.VISIBLE
        viewBinding.tagPersonButton.visibility=View.VISIBLE
        viewBinding.tagPlaceButton.visibility=View.VISIBLE
        viewBinding.tagYearButton.visibility=View.VISIBLE
    }
    fun setTagInvisible() {
        viewBinding.selectTagView.visibility=View.INVISIBLE
        viewBinding.tagAgeButton.visibility=View.INVISIBLE
        viewBinding.tagMatterButton.visibility=View.INVISIBLE
        viewBinding.tagMoodButton.visibility=View.INVISIBLE
        viewBinding.tagObjectButton.visibility=View.INVISIBLE
        viewBinding.tagPersonButton.visibility=View.INVISIBLE
        viewBinding.tagPlaceButton.visibility=View.INVISIBLE
        viewBinding.tagYearButton.visibility=View.INVISIBLE
    }

    private fun changeTheColorDefault(s: String, start: Int, end: Int) {
        mspanable!!.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.gray)),
            start,
            end,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    private fun changeAgeColor(s: String, start: Int, end: Int) {
        mspanable!!.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.age)),
            start,
            end,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    private fun changeMoodColor(s: String, start: Int, end: Int) {
        mspanable!!.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.mood)),
            start,
            end,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    private fun changeYearColor(s: String, start: Int, end: Int) {
        mspanable!!.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.year)),
            start,
            end,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    private fun changeMatterColor(s: String, start: Int, end: Int) {
        mspanable!!.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.matter)),
            start,
            end,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    private fun changeObjectColor(s: String, start: Int, end: Int) {
        mspanable!!.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.`object`)),
            start,
            end,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    private fun changePersonColor(s: String, start: Int, end: Int) {
        mspanable!!.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.person)),
            start,
            end,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    private fun changePlaceColor(s: String, start: Int, end: Int) {
        mspanable!!.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.place)),
            start,
            end,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }

}
