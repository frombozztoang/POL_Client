package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.pieciesoflife.databinding.ActivityDialogPublicBinding
import com.umc.pieciesoflife.databinding.ActivityDialogPublicConfirmBinding

class DialogPublicConfirmActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityDialogPublicConfirmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDialogPublicConfirmBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        viewBinding.btnOk.setOnClickListener {
            val intent = Intent(this, MyDetailedActivity::class.java)
            startActivity(intent)
        }
    }
}