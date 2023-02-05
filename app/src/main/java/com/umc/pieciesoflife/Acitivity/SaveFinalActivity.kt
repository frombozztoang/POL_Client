package com.umc.pieciesoflife.Acitivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.umc.pieciesoflife.BottomNavBar.BottomNavBarActivity
import com.umc.pieciesoflife.Fragment.MyBookFragment
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.databinding.ActivityBottomNavBarBinding
import com.umc.pieciesoflife.databinding.ActivitySaveFinalBinding

class SaveFinalActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySaveFinalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySaveFinalBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()

        // 자서전 완성 시, 다시 MyBookFragment로 복귀
        viewBinding.buttonFinish.setOnClickListener {
            transaction.replace(R.id.mainFrameLayout, MyBookFragment()).commit()
        }
    }
}