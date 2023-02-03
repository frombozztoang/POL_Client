package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.pieciesoflife.databinding.ActivityDialogStoryBinding

class DialogStoryActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityDialogStoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDialogStoryBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        viewBinding.btnOk.setOnClickListener {
            val intent = Intent(this, DialogStoryConfirmActivity::class.java)
            startActivity(intent)
        }
        viewBinding.btnCancel.setOnClickListener {
            val intent = Intent(this, MybookDetailedActivity::class.java)
            startActivity(intent)
        }
    }
}