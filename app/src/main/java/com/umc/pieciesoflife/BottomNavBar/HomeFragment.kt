package com.example.pieciesoflife.bottomNavBar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pieciesoflife.R
import com.example.pieciesoflife.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var Binding: FragmentHomeBinding

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            Binding = FragmentHomeBinding.inflate(inflater, container, false)

            return Binding.root
        }


    }

