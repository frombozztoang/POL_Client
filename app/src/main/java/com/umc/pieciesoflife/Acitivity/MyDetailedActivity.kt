package com.umc.pieciesoflife.Acitivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.databinding.ActivityMyDetailedBinding


class MyDetailedActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMyDetailedBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val mInflater = menuInflater
        mInflater.inflate(R.menu.menu_my_option, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_story -> {
                // Dialog만들기
                val mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog_story, null)
                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                val  mAlertDialog = mBuilder.show()

                val okButton = mDialogView.findViewById<Button>(R.id.btn_ok)
                okButton.setOnClickListener {
                    Toast.makeText(this, "대표 이야기로 설정되었습니다.", Toast.LENGTH_SHORT).show()
                }

                val noButton = mDialogView.findViewById<Button>(R.id.btn_cancel)
                noButton.setOnClickListener {
                    mAlertDialog.dismiss()
                }
            }
            R.id.menu_item_open -> {
                val mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog_open, null)
                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                val  mAlertDialog = mBuilder.show(

                )

                val okButton = mDialogView.findViewById<Button>(R.id.btn_ok)
                okButton.setOnClickListener {
                    // val nextButton = mDialogView.findViewById<Button>(R.id.btn_ok)
                    Toast.makeText(this, "전체 공개로 설정되었습니다.", Toast.LENGTH_SHORT).show()
                }

                val noButton = mDialogView.findViewById<Button>(R.id.btn_cancel)
                noButton.setOnClickListener {
                    mAlertDialog.dismiss()
                }
            }
            R.id.menu_item_book_color -> {
                val mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog_color, null)
                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                val  mAlertDialog = mBuilder.show()

                val okButton = mDialogView.findViewById<Button>(R.id.btn_ok)
                okButton.setOnClickListener {
                    Toast.makeText(this, "대표 이야기로 설정되었습니다.", Toast.LENGTH_SHORT).show()
                }

                val noButton = mDialogView.findViewById<Button>(R.id.btn_cancel)
                noButton.setOnClickListener {
                    mAlertDialog.dismiss()
                }
            }
            R.id.menu_item_delete -> {
                val mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog_delete, null)
                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                val  mAlertDialog = mBuilder.show()

                val okButton = mDialogView.findViewById<Button>(R.id.btn_ok)
                okButton.setOnClickListener {
                    Toast.makeText(this, "이야기가 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                }

                val noButton = mDialogView.findViewById<Button>(R.id.btn_cancel)
                noButton.setOnClickListener {
                    mAlertDialog.dismiss()
                }
            }
        }
        return false
    }
}