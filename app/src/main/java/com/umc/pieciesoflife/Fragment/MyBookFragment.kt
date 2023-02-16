package com.umc.pieciesoflife.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.pieciesoflife.Acitivity.MybookDetailedActivity
import com.umc.pieciesoflife.Acitivity.NotiActivity
import com.umc.pieciesoflife.Acitivity.StartNewstoryAcitivity
import com.umc.pieciesoflife.Adapter.FilterRVAdapter
import com.umc.pieciesoflife.Adapter.MyStoryAdapter
import com.umc.pieciesoflife.BottomNavBar.BottomNavBarActivity
import com.umc.pieciesoflife.DTO.StoryDto.*
import com.umc.pieciesoflife.DTO.StoryDto.myStory.MyStory
import com.umc.pieciesoflife.DTO.UserDto.User
import com.umc.pieciesoflife.GlobalApplication
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.Retrofit.RetrofitClient.storyService
import com.umc.pieciesoflife.Retrofit.RetrofitClient.userService
import com.umc.pieciesoflife.databinding.FragmentMybookBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MyBookFragment : Fragment() {
    private lateinit var viewBinding: FragmentMybookBinding
    private lateinit var bookAdapter: FilterRVAdapter
    private lateinit var myStoryAdapter: MyStoryAdapter
    var jwtToken = GlobalApplication.prefs.getString("jwtToken", "default-value") // 토큰
    var bookList: ArrayList<StoryFilterDataTagStory> = arrayListOf()
    var myStoryList : ArrayList<com.umc.pieciesoflife.DTO.StoryDto.myStory.Story> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMybookBinding.inflate(inflater, container, false)

        // filter adapter 연결
        bookAdapter = FilterRVAdapter(bookList)
        viewBinding.rvMybooks.adapter = bookAdapter
        viewBinding.rvMybooks.layoutManager = LinearLayoutManager(context)


        //태그 버튼 초기 설정은 날짜로
        viewBinding.btnDate.isSelected = true

        // 페이지 로딩 시 전체 보여줌
        getAllMyStory()

        // 마이페이지
        viewBinding.btnProfile.setOnClickListener {
            val mActivity = activity as BottomNavBarActivity
            mActivity.binding.navigationView.selectedItemId = R.id.myPageFragment
        }

        //알림
        viewBinding.btnNoti.setOnClickListener {
            val intent = Intent(context, NotiActivity::class.java)
            startActivity(intent)
        }

        //이야기 새로 쓰기
        viewBinding.btnWrite.setOnClickListener {
            val intent = Intent(context, StartNewstoryAcitivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK // 새로운 tash로 시작
            startActivity(intent)
        }

        // 태그 버튼 '날짜' 클릭
        viewBinding.btnDate.setOnClickListener {
            setTagBtn(true,false, false,false,false,false,false,false, "#날짜")
            getAllMyStory()

        }

        // 태그 버튼 '나이' 클릭
        viewBinding.btnAge.setOnClickListener {
            setTagBtn(false,true, false,false,false,false,false,false, "#나이")
            initRecycler(1,"age")
        }

        // 태그 버튼 '연도' 클릭
        viewBinding.btnYear.setOnClickListener {
            setTagBtn(false,false, true,false,false,false,false,false, "#연도")
            initRecycler(2, "year")
        }

        // 태그 버튼 '감정' 클릭
        viewBinding.btnEmo.setOnClickListener {
            setTagBtn(false,false, false,true,false,false,false,false, "#감정")
            initRecycler(3, "")

        }


        // 태그 버튼 '장소' 클릭
        viewBinding.btnPlace.setOnClickListener {
            setTagBtn(false,false, false,false,true,false,false,false, "#장소")
            initRecycler(4,"")
        }

        // 태그 버튼 '상황' 클릭
        viewBinding.btnSitu.setOnClickListener {
            setTagBtn(false,false, false,false,false,true,false,false,"#상황")
            initRecycler(5,"")
        }

        // 태그 버튼 '물건' 클릭
        viewBinding.btnObject.setOnClickListener {
            setTagBtn(false,false, false,false,false,false,true,false, "#물건")
            initRecycler(6,"")
        }

        // 태그 버튼 '사람' 클릭
        viewBinding.btnPeople.setOnClickListener {
            setTagBtn(false,false, false,false,false,false,false,true, "#인물")
            initRecycler(7,"")
        }


        // 프로필 설정
        userService.getUserInfo("Bearer $jwtToken").enqueue(object : Callback<User> {
            // 성공 처리
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) { // <--> response.code == 200
                    response.body()?.let {
                        viewBinding.tvName.text = it.data.nickname
                    }
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("MyStory-Profile-Error", "onFailure 에러: " + t.message.toString());
            }
        })

        // -> 자서전 상세보기
        bookAdapter.setMyItemClickListener(object : FilterRVAdapter.MyItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(context, MybookDetailedActivity::class.java)
                intent.putExtra("id", bookList[position].id)
                startActivity(intent)
            }
        })

        myStoryAdapter.setMyItemClickListener(object: MyStoryAdapter.MyItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(context, MybookDetailedActivity::class.java)
                intent.putExtra("id", myStoryList[position].id)
                startActivity(intent)
            }
        })


        return viewBinding.root
    }

    fun setTagBtn( btnDate: Boolean,btnAge:Boolean, btnYear: Boolean, btnEmo: Boolean,btnPlace : Boolean, btnSitu:Boolean, btnObject: Boolean, btnPeople:Boolean,tagName: String ) {
        viewBinding.btnDate.isSelected = btnDate
        viewBinding.btnAge.isSelected = btnAge
        viewBinding.btnEmo.isSelected = btnEmo
        viewBinding.btnPeople.isSelected = btnPeople
        viewBinding.btnSitu.isSelected = btnSitu
        viewBinding.btnPlace.isSelected = btnPlace
        viewBinding.btnYear.isSelected = btnYear
        viewBinding.btnObject.isSelected = btnObject
        bookAdapter.clear()
    }


    private fun initRecycler(tagId: Int, tagSort: String) {
        //리사이클러뷰 어댑터 설정
        bookAdapter = FilterRVAdapter(bookList)
        viewBinding.rvMybooks.adapter = bookAdapter

        storyService.getStoryFilter("application/json","Bearer $jwtToken", tagId,0, 8, "$tagSort").enqueue(object : Callback<StoryFilter> {
            // 성공 처리
            override fun onResponse(call: Call<StoryFilter>, response: Response<StoryFilter>) {
                if (response.isSuccessful) { // <--> response.code == 200
                    response.body()?.let {
                        viewBinding.tvNumstory.text = it.data.userStoryCnt.toString()
                        viewBinding.tvNumpiece.text = it.data.userPieceCnt.toString()
                        var result = arrayListOf<StoryFilterDataTagStory>()

                        for(i in 0 until it.data.storyTags.size){
                            Log.d("data.storyTags 의 사이즈", "${it.data.storyTags.size}")
                            val j = it.data.storyTags[i].stories.size
                            for(j in 0 until it.data.storyTags[i].stories.size) {
                                if(j > 0 && (it.data.storyTags[i].stories[j].storyTag == it.data.storyTags[i].stories[j-1].storyTag)) {
                                    it.data.storyTags[i].stories[j].storyTag = ""

                                }
                            }
                             result = (result + it.data.storyTags[i].stories as ArrayList<StoryFilterDataTagStory>) as ArrayList<StoryFilterDataTagStory>
                        }
                        Log.d("필터링가링가링", "$result")
                        bookAdapter.addItems(result)
                    }
                }
            }
            override fun onFailure(call: Call<StoryFilter>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("testtt", "onFailure 에러: " + t.message.toString());
            }
        })
    }

    private fun getAllMyStory() {
        // mystory adapter 연결
        myStoryAdapter = MyStoryAdapter(myStoryList)
        viewBinding.rvMybooks.adapter = myStoryAdapter

        storyService.getMyStory("Bearer $jwtToken",0,8, "").enqueue(object : Callback<MyStory> {
            // 성공 처리
            override fun onResponse(call: Call<MyStory>, response: Response<MyStory>) {
                if (response.isSuccessful) { // <--> response.code == 200
                    response.body()?.let {
                        myStoryList = it.data.stories as ArrayList<com.umc.pieciesoflife.DTO.StoryDto.myStory.Story>
                        myStoryAdapter.addItems(myStoryList)
                        viewBinding.tvNumstory.text = it.data.userStoryCnt.toString()
                        viewBinding.tvNumpiece.text = it.data.userPieceCnt.toString()
                        Log.d("전체", "$myStoryList")
                    }
                }
            }
            override fun onFailure(call: Call<MyStory>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("testtt", "onFailure 에러: " + t.message.toString());
            }
        })

    }


}




