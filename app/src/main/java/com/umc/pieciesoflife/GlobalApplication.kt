package com.umc.pieciesoflife

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.umc.pieciesoflife.R

class GlobalApplication : Application() {
    // Application class
    // 애플리케이션 컴포넌트 사이에 공동으로 멤버들을 사용할 수 있게 해주는 공유 클래스

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "10b956924e3663c525109092e81593ec")
    }

}