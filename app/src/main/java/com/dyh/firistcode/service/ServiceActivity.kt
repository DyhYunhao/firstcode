package com.dyh.firistcode.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import com.dyh.firistcode.R

class ServiceActivity : AppCompatActivity() {

    lateinit var downloadBinder: MyService.DownloadBinder

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder?) {
            downloadBinder = service as MyService.DownloadBinder
            downloadBinder.startDownload()
            downloadBinder.getProgress()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        findViewById<Button>(R.id.btn_start_service).setOnClickListener {
            startService(Intent(this, MyService::class.java))
        }

        findViewById<Button>(R.id.btn_stop_service).setOnClickListener {
            stopService(Intent(this, MyService::class.java))
        }

        findViewById<Button>(R.id.btn_bind_service).setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }

        findViewById<Button>(R.id.btn_unbind_service).setOnClickListener {
            unbindService(connection)
        }
    }
}