package com.umc.pieciesoflife.Acitivity

import android.Manifest
import android.R
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.umc.pieciesoflife.BottomNavBar.BottomNavBarActivity
import com.umc.pieciesoflife.Fragment.MyBookFragment
import com.umc.pieciesoflife.Fragment.UserFragment
import com.umc.pieciesoflife.databinding.ActivityBottomNavBarBinding
import com.umc.pieciesoflife.databinding.ActivityUserEditBinding
import java.io.File


class UserEditActivity:AppCompatActivity() {
    private lateinit var viewBinding: ActivityUserEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityUserEditBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        var profileImgUrl = ""
        var defaultFile = ""

        val userIntent = intent

        val nickname = userIntent.getStringExtra("nickname")
        viewBinding.editNickName.setText(nickname)

        if(userIntent.getStringExtra("imgProfile")!=null) {
            profileImgUrl = userIntent.getStringExtra("imgProfile")!!
            Glide.with(this).load(profileImgUrl).into(viewBinding.imgProfile)
        } else {
            viewBinding.imgProfile.setImageResource(com.umc.pieciesoflife.R.drawable.ic_pol)
        }

        viewBinding.btnCamera.setOnClickListener{
            selectGallery()
            Glide.with(this)
                .load(imageResult)
                .into(viewBinding.imgProfile)
        }


        // 뒤로가기
        viewBinding.btnUserEditCancel.setOnClickListener {
            this@UserEditActivity.finishAffinity()
    }

        // 확인하기 일단 피니시
        viewBinding.btnUserEditOk.setOnClickListener{
            this@UserEditActivity.finishAffinity()
            Toast.makeText(this, "프로필이 변경됐다능", Toast.LENGTH_SHORT ).show()
        }

    }

    companion object{
        const val REVIEW_MIN_LENGTH = 10
        // 갤러리 권한 요청
        const val REQ_GALLERY = 1

        // API 호출 시 PArameter key 값
        const val PARAM_KEY_IMAGE = "image"
        const val PARAM_KEY_PRODUCT_ID = "product_id"
        const val PARAM_KEY_REVIEW = "review_content"
        const val PARAM_KEY_RATING = "rating"
    }

    // 이미지 결과값으로 받는 변수
    private val imageResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        result ->
        if(result.resultCode == RESULT_OK) {
            val imageUri = result.data?.data
            imageUri?.let {

                // 서버 업로드를 위해 파일 형태로 변환
                var imageFile = File(getRealPathFromURI(it))

                // 이미지 불러옴
                Glide.with(this)
                    .load(imageUri)
                    .fitCenter()
                    .into(viewBinding.imgProfile)
            }
        }
    }

    // 이미지 실제 경로 반환
    fun getRealPathFromURI(uri: Uri):String{
        val buildName = Build.MANUFACTURER
        if(buildName.equals("Xiaomi")) {
            return uri.path!!
        }
        var columnIndex = 0
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(uri,proj,null,null,null)
        if(cursor!!.moveToFirst()){
            columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        }
        val result = cursor.getString(columnIndex)
        cursor.close()
        return result
    }

    // 갤러리 부르는 메서드
    private fun selectGallery() {
        val writePermission = ContextCompat.checkSelfPermission(this,
        Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val readPermission = ContextCompat.checkSelfPermission(this,
        Manifest.permission.READ_EXTERNAL_STORAGE)

        // 권한 확인
        if(writePermission == PackageManager.PERMISSION_DENIED ||
                readPermission == PackageManager.PERMISSION_DENIED) {
            // 권한 요청
            ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE), REQ_GALLERY)
        } else {
            // 권한이 있는 경우 갤러리 실행
            val intent = Intent(Intent.ACTION_PICK)
            // intent의 data와 type을 동시에 설정하는 메서드
            intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            imageResult.launch(intent)
        }
    }



}

