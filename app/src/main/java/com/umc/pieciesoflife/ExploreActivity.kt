package com.umc.pieciesoflife


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.umc.pieciesoflife.databinding.ActivityExploreBinding


class ExploreActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityExploreBinding.inflate(layoutInflater)
    }
    //private val binding by lazy {
    //    ActivityExploreBinding.inflate(layoutInflater)
    // }
    // private lateinit var toolbarBinding:ToolbarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /* Toolbar */
        setSupportActionBar(binding.abInclude.toolbar)
        //Toolbar에 표시되는 제목의 표시 유무를 설정. false로 해야 custom한 툴바의 이름이 화면에 보인다.
        supportActionBar?.setDisplayShowTitleEnabled(true)
        //왼쪽 버튼 사용설정(기본은 뒤로가기)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        //정렬
        binding.tvAlignNew.setOnClickListener(this)
        binding.tvAlignLike.setOnClickListener(this)
    }

    //item 버튼 메뉴 Toolbar에 집어 넣기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    //item 버튼 클릭 했을 때
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item?.itemId) {
            R.id.action_myPage -> {
                //마이페이지 버튼 눌렀을 때
                Log.d("ToolBar_item: ", "마이페이지 버튼 클릭")
                val intent = Intent(applicationContext, MainActivity::class.java) //? 일단 메인액티비티로 가게 함
                startActivity(intent)
                return true
            }
            R.id.action_alarm -> {
                //도움말 버튼 눌렀을 때
                Log.d("ToolBar_item: ", "알람 버튼 클릭")
                val intent = Intent(applicationContext, MainActivity::class.java) //? 일단 메인액티비티로 가게 함
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    // 정렬하는 텍스트뷰
    override fun onClick(v: View?) {
        when (v?.id) {
           binding.tvAlignNew.id -> {
               // 최신순 정렬하는 코드, 일단은 토스트
               toast("최신순 정렬 Clicked")
           }
            binding.tvAlignLike.id -> {
                // 인기순 정렬하는 코드, 일단은 토스트 호출
                toast("인기순 정렬 Clicked")
            }
        }
    }

    // 토스트 코드
    private fun toast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }
}



