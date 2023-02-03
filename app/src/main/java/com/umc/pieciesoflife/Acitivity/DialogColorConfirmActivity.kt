package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.umc.pieciesoflife.BottomNavBar.BottomNavBarActivity
import com.umc.pieciesoflife.databinding.ActivityDialogColorBinding
import com.umc.pieciesoflife.databinding.ActivityDialogColorConfirmBinding

class DialogColorConfirmActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityDialogColorConfirmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDialogColorConfirmBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(viewBinding.root)

    }
}