package com.example.pieciesoflife.bottomNavBar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.pieciesoflife.R
import com.example.pieciesoflife.databinding.FragmentMyPageBinding
import com.example.pieciesoflife.myPage.BookFragment
import com.example.pieciesoflife.myPage.MessageFragment
import com.example.pieciesoflife.myPage.PagerFragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MyPageFragment : Fragment() {

    private lateinit var Binding: FragmentMyPageBinding

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        Binding = FragmentMyPageBinding.inflate(inflater, container, false)
//        return Binding.root

        val view:View = inflater.inflate(R.layout.fragment_my_page, container, false)
        viewPager = view.findViewById(R.id.viewPager)
        tabLayout =  view.findViewById(R.id.tabLayout)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pagerAdapter = PagerFragmentStateAdapter(requireActivity())
        // 2개의 fragment add
        pagerAdapter.addFragment(BookFragment())
        pagerAdapter.addFragment(MessageFragment())


        // adapter 연결
        viewPager.adapter = pagerAdapter
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int){
                super.onPageSelected(position)
                Log.e("ViewPagerFragment", "Page ${position+1}")
            }
        })

        // tablayout attach
        TabLayoutMediator(tabLayout, viewPager){ tab, position ->
            when(position) {
                0 -> tab.text = "내가 작성한 자서전"
                1 -> tab.text = "쪽지함"
            }
        }.attach()
    }



}


