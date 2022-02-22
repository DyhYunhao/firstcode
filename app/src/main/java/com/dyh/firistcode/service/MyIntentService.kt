package com.dyh.firistcode.service

import android.app.IntentService
import android.content.Intent
import android.util.Log

class MyIntentService: IntentService("MyIntentService") {
    override fun onHandleIntent(intent: Intent?) {
        Log.d("service", "onHandleIntent: ${Thread.currentThread().name}")
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}