package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.pieciesoflife.databinding.ActivityDialogStoryBinding
import com.umc.pieciesoflife.databinding.ActivityDialogStoryConfirmBinding

class DialogStoryConfirmActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityDialogStoryConfirmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDialogStoryConfirmBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        viewBinding.btnOk.setOnClickListener {
            val intent = Intent(this, MyDetailedActivity::class.java)
            startActivity(intent)
        }
    }
}