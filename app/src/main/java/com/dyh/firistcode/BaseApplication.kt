package com.dyh.firistcode

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.zxy.tiny.Tiny

class BaseApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        Tiny.getInstance().init(this)
    }
}