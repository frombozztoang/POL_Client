package com.umc.pieciesoflife.Acitivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umc.pieciesoflife.databinding.ActivityDialogUserEditBinding

class DialogUserEditActivity: AppCompatActivity() {
    private lateinit var viewBinding: ActivityDialogUserEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDialogUserEditBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}