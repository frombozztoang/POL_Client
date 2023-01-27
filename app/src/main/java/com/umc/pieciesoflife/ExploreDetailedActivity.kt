package com.umc.pieciesoflife

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.umc.pieciesoflife.databinding.ActivityDetailedExploreBinding

class ExploreDetailedActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailedExploreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        /** Toolbar **/
        setSupportActionBar(binding.tbDetailed)
        //Toolbar에 표시되는 제목의 표시 유무를 설정. false로 해야 custom한 툴바의 이름이 화면에 보인다.
        supportActionBar?.setDisplayShowTitleEnabled(false)
        // 뒤로가기 버튼 활성화 (화살표)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        /* 좋아요 버튼 클릭 */
        // binding.tvAlignNew.setOnClickListener(this)
        // binding.tvAlignLike.setOnClickListener(this)

        // 사용자명, 프로필사진 클릭
    }

    //item 버튼 메뉴 Toolbar에 집어 넣기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_detailed_menu, menu)
        return true
    }

    //item 버튼 클릭 했을 때
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            android.R.id.home -> {
                // 뒤로가기 버튼 눌렀을 때
                Log.d("ToolBar_item: ", "뒤로가기 버튼 클릭")
                finish()
                return true
            }
            R.id.action_message -> {
                // 쪽지 버튼 눌렀을 때
                Log.d("ToolBar_item: ", "쪽지 버튼 클릭")
                val intent = Intent(applicationContext, MainActivity::class.java) //? 일단 메인액티비티로 가게 함
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    // 좋아요 버튼 클릭
    /*
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

     */

    // 사용자명, 프로필사진 클릭

    // 토스트 코드
    private fun toast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }
}