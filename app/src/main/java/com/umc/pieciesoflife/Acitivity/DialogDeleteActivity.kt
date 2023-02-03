package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.umc.pieciesoflife.databinding.ActivityDialogDeleteBinding

class DialogDeleteActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityDialogDeleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDialogDeleteBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(viewBinding.root)


        viewBinding.btnOk.setOnClickListener {
            val intent = Intent(this, DialogDeleteConfirmActivity::class.java)
            startActivity(intent)
        }
        viewBinding.btnCancel.setOnClickListener {
            val intent = Intent(this, MybookDetailedActivity::class.java)
            startActivity(intent)
        }
    }
}