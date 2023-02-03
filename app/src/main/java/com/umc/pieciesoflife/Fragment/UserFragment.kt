package com.umc.pieciesoflife.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.viewpager2.widget.ViewPager2
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.Adapter.UserVPAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.umc.pieciesoflife.Acitivity.DialogUserEditActivity
import com.umc.pieciesoflife.Acitivity.NotiActivity
import com.umc.pieciesoflife.Acitivity.StartNewstoryAcitivity


class UserFragment : Fragment() {
    //private lateinit var viewBinding: FragmentUserBinding

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //viewBinding = FragmentUserBinding.inflate(inflater, container, false)
        //return viewBinding.root

        val view:View = inflater.inflate(R.layout.fragment_user, container, false)
        viewPager = view.findViewById(R.id.viewPager)
        tabLayout =  view.findViewById(R.id.tabLayout)
        val pagerAdapter = UserVPAdapter(requireActivity())

        // ->알림
        val btnNoti = view.findViewById<ImageButton>(R.id.btn_noti)
        btnNoti.setOnClickListener {
            val intent = Intent(context, NotiActivity::class.java)
            startActivity(intent)
        }

        // ->유저 프로필 편집
        val btnEdit = view.findViewById<ImageButton>(R.id.btn_edit)
        btnEdit.setOnClickListener {
            val intent = Intent(context, DialogUserEditActivity::class.java)
            startActivity(intent)
        }

        // ->새로운 이야기 작성
        val btnNewStory = view.findViewById<Button>(R.id.btn_new_story)
        btnNewStory.setOnClickListener {
            startActivity(Intent(context, StartNewstoryAcitivity::class.java))
        }

        //ViewPager
        // 2개의 fragment add
        pagerAdapter.addFragment(UserBookFragment())
        pagerAdapter.addFragment(UserMessageFragment())
        // adapter 연결
        viewPager.adapter = pagerAdapter
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int){
                super.onPageSelected(position)
                Log.e("ViewPagerFragment", "Page 이거 뜨면 되는거다? ${position+1}")
            }
        })

        //Tablayout
        TabLayoutMediator(tabLayout, viewPager){ tab, position ->
            when(position) {
                0 -> tab.text = "좋아요 누른 자서전"
                1 -> tab.text = "쪽지함"
            }
        }.attach()


        return view
    }

}


