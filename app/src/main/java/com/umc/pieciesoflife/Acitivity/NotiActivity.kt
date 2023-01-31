package com.umc.pieciesoflife.Acitivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.pieciesoflife.DataClass.Noti
import com.umc.pieciesoflife.Adapter.NotiRVAdapter
import com.umc.pieciesoflife.R
import com.umc.pieciesoflife.databinding.ActivityNotiBinding

class NotiActivity : AppCompatActivity(){
    private lateinit var viewBinding: ActivityNotiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityNotiBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val notiList: ArrayList<Noti> = arrayListOf()

        notiList.apply {
            add(Noti(R.drawable.logo, "알림1", "1m ago"))
            add(Noti(R.drawable.logo, "알림2", "2m ago"))
            add(Noti(R.drawable.logo, "알림3", "3m ago"))
        }

        val notiRVAdapter = NotiRVAdapter(notiList)

        viewBinding.RVNOTI.adapter = notiRVAdapter
        viewBinding.RVNOTI.layoutManager = LinearLayoutManager(this)

    }
}