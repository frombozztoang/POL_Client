package com.umc.pieciesoflife.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.umc.pieciesoflife.Acitivity.MainActivity
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    private lateinit var viewBinding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentSearchBinding.inflate(inflater, container, false)

        return viewBinding.root
    }

}
