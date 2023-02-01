package com.umc.pieciesoflife.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import com.umc.pieciesoflife.Acitivity.ChatSendActivity
import com.umc.pieciesoflife.Acitivity.NotiActivity
import com.umc.pieciesoflife.Acitivity.StartNewstoryAcitivity
import com.umc.pieciesoflife.databinding.FragmentBookBinding
import com.umc.pieciesoflife.databinding.FragmentHomeBinding
import com.umc.pieciesoflife.databinding.FragmentMybookBinding


class HomeFragment : Fragment() {
    private lateinit var viewBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHomeBinding.inflate(inflater, container, false)

        viewBinding.btnProfile.setOnClickListener { // 마이페이지
            val intent = Intent(context, UserFragment::class.java)
            startActivity(intent)
        }

        viewBinding.btnNoti.setOnClickListener { // 알림
            val intent = Intent(context, NotiActivity::class.java)
            startActivity(intent)
        }
        return viewBinding.root
    }

}

