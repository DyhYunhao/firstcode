package com.dyh.firistcode.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.dyh.firistcode.MainActivity
import com.dyh.firistcode.R
import kotlin.concurrent.thread

class MyService : Service() {

    private val mBinder = DownloadBinder()

    class DownloadBinder: Binder() {
        fun startDownload() {
            Log.d("service", "startDownload: executed")
        }

        fun getProgress(): Int {
            Log.d("service", "getProgress: executed")
            return 0
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()

        //前台service
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("my_service", "前台service通知", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val intent = Intent(this, ServiceActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, 0)
        val notification = NotificationCompat.Builder(this, "my_service")
            .setContentTitle("this is content title")
            .setContentText("this is content text")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_foreground))
            .setContentIntent(pi)
            .build()
        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //service 默认运行在主线程，如果进行耗时操作容易出现ANR，因此需要多线程编程，在子线程中处理耗时操作
        thread {
            //处理耗时逻辑
            stopSelf() //这里就是让前台service执行完自动停止
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}