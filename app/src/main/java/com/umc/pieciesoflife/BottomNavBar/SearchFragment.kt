package com.example.pieciesoflife.bottomNavBar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pieciesoflife.R
import com.example.pieciesoflife.databinding.FragmentMyPageBinding
import com.example.pieciesoflife.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    private lateinit var Binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Binding = FragmentSearchBinding.inflate(inflater, container, false)

        return Binding.root
    }


}
