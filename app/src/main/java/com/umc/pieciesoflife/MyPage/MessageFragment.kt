package com.example.pieciesoflife.myPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pieciesoflife.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {
    private lateinit var Binding: FragmentMessageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Binding = FragmentMessageBinding.inflate(inflater, container, false)
        return Binding.root
    }

}

