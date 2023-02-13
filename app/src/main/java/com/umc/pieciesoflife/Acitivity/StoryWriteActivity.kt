package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import android.graphics.Color
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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.umc.pieciesoflife.DTO.QuestionDto.Question
import com.umc.pieciesoflife.DTO.StoryDto.StoryQna
import com.umc.pieciesoflife.DTO.StoryDto.StoryTag
import com.umc.pieciesoflife.Interface.QuestionService
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
    var previousQuestion: ArrayList<String> = ArrayList()
    var questionTag: ArrayList<Int> = ArrayList()
    lateinit var question: String
    var tagIds = mutableListOf<Int>()
    var num : Int = 0
    lateinit var tagHash : HashMap<Int, String>
    lateinit var tagContent : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_write)
        viewBinding = ActivityStoryWriteBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        tagHash = intent.getSerializableExtra("TagHash") as HashMap<Int, String>
        Log.d("TagHash", "$tagHash")

        // tagContent에 값이 있을 시에, tagIds에 add
        for (i: Int in 1..7) {
            val tagContent_tmp = tagHash[i]
            if (tagContent_tmp != null) {
                tagIds.add(i)
            }
        }
        Log.d("TagIds", "$tagIds")

        // 랜덤 태그 생성
        num = tagIds.random()
        randomTag(num)
        tagContent = tagHash[num].toString() //태그 컨텐츠도 질문태그에 맞게 업데이트
        Log.d("num", "$num")

        val answerList : ArrayList<String> = arrayListOf<String>()
        val questionList : ArrayList<String> = arrayListOf<String>()
        var answer: String = ""

        // 랜덤선택된 태그에 관한 초기 질문 생성
        initQuestion(viewBinding.editTextTextMultiLineWriteStory)

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
                    //Log.i(javaClass.simpleName, "CHARACTER OF NEW WORD: $startChar")
                } catch (ex: Exception) {
                    startChar = ""
                }
                //Log.i(javaClass.simpleName, "Start: $start count: $count")

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
                    str = concat(str, startChar) as String
                    changeTheColorDefault(s.toString().substring(start), start, start + count)
                    hashTagIsComing++

                    //태그 버튼 선택 ->리스트에 인풋&글자 색 바꾸기&태그 선택화면 내리기
                    viewBinding.tagAgeButton.setOnClickListener { //나이 버튼 선택
                        //문자열 색깔 바꾸기
                        changeAgeColor(s.toString().substring(start), start - 1, start + count)
                        //리스트에 문자열 입력하기
                        str = edtTxtMine.text.toString().substring(start, start + count) //str 문자열 자르기
                        //Log.i(javaClass.simpleName, "str :$str") //str 문자열 잘 받아와졌는지 확인
                        str = "" //str 초기화
                        setTagInvisible()
                        hashTagIsComing = 0
                    }
                    viewBinding.tagMatterButton.setOnClickListener { //상황 버튼 선택
                        changeMatterColor(s.toString().substring(start), start - 1, start + count)
                        str = edtTxtMine.text.toString().substring(start, start + count)
                        //Log.i(javaClass.simpleName, "str :$str")
                        str = ""
                        setTagInvisible()
                        hashTagIsComing = 0
                    }
                    viewBinding.tagMoodButton.setOnClickListener { //감정 버튼 선택
                        changeMoodColor(s.toString().substring(start), start - 1, start + count)
                        str = edtTxtMine.text.toString().substring(start, start + count) //str 문자열 자르기
                        //Log.i(javaClass.simpleName, "str :$str") //str 문자열 잘 받아와졌는지 확인
                        str = "" //str 초기화
                        setTagInvisible()
                        hashTagIsComing = 0
                    }
                    viewBinding.tagObjectButton.setOnClickListener { //물건 버튼 선택
                        changeObjectColor(s.toString().substring(start), start - 1, start + count)
                        str = edtTxtMine.text.toString().substring(start, start + count) //str 문자열 자르기
                        //Log.i(javaClass.simpleName, "str :$str") //str 문자열 잘 받아와졌는지 확인
                        str = "" //str 초기화
                        setTagInvisible()
                        hashTagIsComing = 0
                    }
                    viewBinding.tagPersonButton.setOnClickListener { //인물  버튼 선택
                        changePersonColor(s.toString().substring(start), start - 1, start + count)
                        str = edtTxtMine.text.toString().substring(start, start + count) //str 문자열 자르기
                        //Log.i(javaClass.simpleName, "str :$str") //str 문자열 잘 받아와졌는지 확인
                        str = "" //str 초기화
                        setTagInvisible()
                        hashTagIsComing = 0
                    }
                    viewBinding.tagPlaceButton.setOnClickListener { //장소 버튼 선택
                        changePlaceColor(s.toString().substring(start), start - 1, start + count)
                        str = edtTxtMine.text.toString().substring(start, start + count) //str 문자열 자르기
                        //Log.i(javaClass.simpleName, "str :$str") //str 문자열 잘 받아와졌는지 확인
                        str = "" //str 초기화
                        setTagInvisible()
                        hashTagIsComing = 0
                    }
                    viewBinding.tagYearButton.setOnClickListener { //연도 버튼 선택
                        changeYearColor(s.toString().substring(start), start - 1, start + count)
                        str = edtTxtMine.text.toString().substring(start, start + count) //str 문자열 자르기
                        //Log.i(javaClass.simpleName, "str :$str") //str 문자열 잘 받아와졌는지 확인
                        str = "" //str 초기화
                        setTagInvisible()
                        hashTagIsComing = 0
                    }
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {}
            override fun afterTextChanged(s: Editable) {}
        })

        // 새로운 질문 생성 (다음)
        viewBinding.buttonNext.setOnClickListener {
            val inputText = viewBinding.editTextTextMultiLineWriteStory.text.toString()
            if (inputText.isEmpty()) {
                Toast.makeText(this, "답변을 입력하지 않으면 넘어갈 수 없습니다. \n어려운 질문은 건너뛰어도 돼요.", Toast.LENGTH_LONG).show()
            } else {
                answer = viewBinding.editTextTextMultiLineWriteStory.text.toString()
                val question_new = tagContent + " " + question
                answerList.add(answer)
                questionList.add(question_new)
                questionTag.add(num)
                Log.d("num", "$num")
                Log.i("answerList", "$answerList")
                Log.i("questionList", "$questionList")
                Log.i("questionTag", "$questionTag")

                //랜덤 질문
                num = tagIds.random()
                randomTag(num)
                Log.d("updated_num", "$num")
                tagContent = tagHash[num].toString() //태그 컨텐츠도 질문태그에 맞게 업데이트

                // 랜덤 선택된 태그에 관한 질문 생성
                initQuestion(viewBinding.editTextTextMultiLineWriteStory)

                //edit Text 초기화
                viewBinding.editTextTextMultiLineWriteStory.setText("")
                viewBinding.editTextTextMultiLineWriteStory.setSelection(viewBinding.editTextTextMultiLineWriteStory.text.length)
            }
        }
        // 질문 건너뛰기 - 새로운 질문 생성
        viewBinding.buttonSkipQuestion.setOnClickListener {
            //랜덤 질문
            num = tagIds.random()
            randomTag(num)
            Log.d("updated_num", "$num")
            tagContent = tagHash[num].toString() //태그 컨텐츠도 질문태그에 맞게 업데이트
            // 랜덤 선택된 태그에 관한 질문 생성
            initQuestion(viewBinding.editTextTextMultiLineWriteStory)
        }
        // 뒤로가기 - 이전 Tag 화면 띄우기
        viewBinding.buttonBack.setOnClickListener {
            val intent = Intent(applicationContext, TagPersonActivity::class.java)
            startActivity(intent)
        }
        // 이야기 완성하기 - 이야기 저장
        viewBinding.buttonFinish.setOnClickListener {
            //StoryTag
            val tagIdList = tagHash.keys.toList()
            val tagContentList = tagHash.values.toList()
            val storyTagList = ArrayList<StoryTag>()
            for (i in tagIdList.indices) {
                if (tagIdList[i] == null || tagContentList[i] == null) {
                    continue
                }
                storyTagList.add(StoryTag(tagIdList[i]!!, tagContentList[i]!!)) //나는 이렇게 넣어야한다고 생각햇는데 그냥 이중배열로 넣는거면 수정만 하면 될듯
            }

            //QnaTag
            val storyQnaList = mutableListOf<StoryQna>()
            for (i in answerList.indices) {
                if (answerList[i] == null || questionList[i] == null || questionTag[i] == null) {
                }
                storyQnaList.add(StoryQna(questionList[i], answerList[i], questionTag[i])) //얘도 마찬가지!!
            }
            Log.i("storyTagList","$storyTagList")
            Log.i("storyQnaList","$storyQnaList")

            val intent = Intent(applicationContext, SaveTitleActivity::class.java)

            intent.putExtra("storyQnaList","$storyQnaList")
            intent.putExtra("storyTagList","$storyTagList")
            startActivity(intent)
        }
    }

    // 랜덤 태그 생성
    fun randomTag(num : Int){
        viewBinding.textViewTag.setText("#"+tagHash.get(num))
        if (num==1)         viewBinding.textViewTag.setTextColor(Color.parseColor("#6B9A85"))
        if (num==2)         viewBinding.textViewTag.setTextColor(Color.parseColor("#A56E4E"))
        if (num==3)         viewBinding.textViewTag.setTextColor(Color.parseColor("#567DA7"))
        if (num==4)         viewBinding.textViewTag.setTextColor(Color.parseColor("#5F8D8D"))
        if (num==5)         viewBinding.textViewTag.setTextColor(Color.parseColor("#93666F"))
        if (num==6)         viewBinding.textViewTag.setTextColor(Color.parseColor("#8D8565"))
        if (num==7)         viewBinding.textViewTag.setTextColor(Color.parseColor("#9467A3"))
    }

    // 랜덤 선택된 태그에 관한 질문 생성
    fun initQuestion(editText: EditText) {
        val call: QuestionService = RetrofitClient.questionService
        call.getQuestion(num).enqueue(object: Callback<Question>{
            // 성공 처리
            override fun onResponse(call: Call<Question>, response: Response<Question>) {
                if(response.isSuccessful()) { // <--> response.code == 200
                    response.body()?.let {
                        question = it.data.questionTemplate
                        if (!previousQuestion.contains(question)) {
                            viewBinding.tvQuestion.setText(question)
                            previousQuestion.add(question)
                        }
                        else
                            initQuestion(viewBinding.editTextTextMultiLineWriteStory)
                    }
                }
            }
            // 실패 처리
            override fun onFailure(call: Call<Question>, t: Throwable) {
                Log.d("testt", "에러입니다. ${t.message}")
                t.printStackTrace()
            }
        })
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
