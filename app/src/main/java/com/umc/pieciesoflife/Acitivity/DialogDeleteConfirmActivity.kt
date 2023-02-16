package com.umc.pieciesoflife.Acitivity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.umc.pieciesoflife.Fragment.HomeFragment
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.databinding.ActivityDialogDeleteConfirmBinding

class DialogDeleteConfirmActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityDialogDeleteConfirmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDialogDeleteConfirmBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(viewBinding.root)


        viewBinding.btnOk.setOnClickListener {
            this@DialogDeleteConfirmActivity.finishAffinity()
        }
    }
}