package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.umc.pieciesoflife.databinding.ActivityDialogPublicConfirmBinding

class DialogPublicConfirmActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityDialogPublicConfirmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDialogPublicConfirmBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(viewBinding.root)


        viewBinding.btnOk.setOnClickListener {
            val intent = Intent(this, MybookDetailedActivity::class.java)
            startActivity(intent)
        }
    }
}