package com.umc.pieciesoflife.Acitivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.pieciesoflife.databinding.ActivityDialogBottomBinding

class DialogBottomAcitivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityDialogBottomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDialogBottomBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        viewBinding.btnStory.setOnClickListener {
            val intent = Intent(this, DialogStoryActivity::class.java)
            startActivity(intent)
        }

        viewBinding.btnPublic.setOnClickListener {
            val intent = Intent(this, DialogPublicActivity::class.java)
            startActivity(intent)
        }

        viewBinding.btnColor.setOnClickListener {
            val intent = Intent(this, DialogColorActivity::class.java)
            startActivity(intent)
        }

        viewBinding.btnDelete.setOnClickListener {
            val intent = Intent(this, DialogDeleteActivity::class.java)
            startActivity(intent)
        }
    }
}