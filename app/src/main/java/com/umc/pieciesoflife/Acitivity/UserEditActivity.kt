package com.umc.pieciesoflife.Acitivity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.umc.pieciesoflife.DTO.UserDto.User
import com.umc.pieciesoflife.DTO.UserDto.UserEdit
import com.umc.pieciesoflife.GlobalApplication
import com.umc.pieciesoflife.Interface.UserService
import com.umc.pieciesoflife.Retrofit.RetrofitClient
import com.umc.pieciesoflife.databinding.ActivityUserEditBinding
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

        var jwtToken = GlobalApplication.prefs.getString("jwtToken", "default-value")

        var nickname = userIntent.getStringExtra("nickname")
        viewBinding.editNickName.setText(nickname)

        if(userIntent.getStringExtra("imgProfile")!=null) {
            profileImgUrl = userIntent.getStringExtra("imgProfile")!!
            Picasso.get().load(profileImgUrl).into(viewBinding.imgProfile)
        } else {
            viewBinding.imgProfile.setImageResource(com.umc.pieciesoflife.R.drawable.ic_default_profileimg)
        }

        viewBinding.btnCamera.setOnClickListener{
            selectGallery()
            val imgFile = File("profileImg")
            val imgRequestFile = RequestBody.create(MediaType.parse("image/png"), imgFile)
            val imgBody = MultipartBody.Part.createFormData("file", imgFile.name, imgRequestFile)
            Picasso.get().load(imgFile).into(viewBinding.imgProfile)
//            Picasso.get().load(profileImgUrl).into(viewBinding.imgProfile)
//            Glide.with(this)
//                .load(imageResult)
//                .into(viewBinding.imgProfile)
        }


        // 뒤로가기
        viewBinding.btnUserEditCancel.setOnClickListener {
            this@UserEditActivity.finishAffinity()
      }

        // 확인하기 일단 피니시
        viewBinding.btnUserEditOk.setOnClickListener{
            val ninkname = viewBinding.editNickName.text // 입력되어 있는 닉네임
            val profileImg = imageResult // 변경된 img Url 주소
            val call: UserService = RetrofitClient.userService

            if(imageResult.equals("") || imageResult.equals(null)) {
                profileImgUrl = userIntent.getStringExtra("imgProfile")!!
                Picasso.get().load(profileImgUrl).into(viewBinding.imgProfile)
            } else {
                val imgFile = File("profileImg")
                val imgRequestFile = RequestBody.create(MediaType.parse("image/png"), imgFile)
                val imgBody = MultipartBody.Part.createFormData("file", imgFile.name, imgRequestFile)


                call.patchUserProfile("multipart/form-data","Bearer $jwtToken","$ninkname", imgBody).enqueue(object : Callback<UserEdit> {
                    // 전송 실패
                    override fun onFailure(call: Call<UserEdit>, t: Throwable) {
                        Log.d("패치치치치치프렛즐", t.message!!)
                    }

                    // 전송 성공
                    override fun onResponse(call: Call<UserEdit>, response: Response<UserEdit>){
                        response.body()?.let {
                            var aa = it.data.nickname
                            var bb = it.data.profileImgUrl

                            Log.d("성공" , "profile : $bb \nnickname : $aa ")
                        } ?: Log.d("Body is null", "몸통은 비었다.")
                    }
                })
            }


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

