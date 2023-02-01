package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.pieciesoflife.databinding.ActivityDialogPublicBinding
import com.umc.pieciesoflife.databinding.ActivityDialogStoryBinding

class DialogPublicActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityDialogPublicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDialogPublicBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        viewBinding.btnOk.setOnClickListener {
            val intent = Intent(this, DialogPublicConfirmActivity::class.java)
            startActivity(intent)
        }
        viewBinding.btnCancel.setOnClickListener {
            val intent = Intent(this, MyDetailedActivity::class.java)
            startActivity(intent)
        }
    }
}