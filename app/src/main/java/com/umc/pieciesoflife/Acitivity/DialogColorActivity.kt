package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.pieciesoflife.databinding.ActivityDialogColorBinding

class DialogColorActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityDialogColorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDialogColorBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnOk.setOnClickListener {
            val intent = Intent(this, DialogColorConfirmActivity::class.java)
            startActivity(intent)
        }
        viewBinding.btnCancel.setOnClickListener {
            val intent = Intent(this, MybookDetailedActivity::class.java)
            startActivity(intent)
        }
    }
}