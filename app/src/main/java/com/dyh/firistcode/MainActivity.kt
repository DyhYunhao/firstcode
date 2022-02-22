package com.dyh.firistcode

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.dyh.firistcode.audio.AudioActivity
import com.dyh.firistcode.camera.CameraActivity
import com.dyh.firistcode.contentprovider.ContentdProviderActivity
import com.dyh.firistcode.datasave.DbSaveActivity
import com.dyh.firistcode.fragment.FragmentActivity
import com.dyh.firistcode.jetpack.JetpackActivity
import com.dyh.firistcode.material.MaterialActivity
import com.dyh.firistcode.notification.NotificationActivity
import com.dyh.firistcode.recycleview.RcvActivity
import com.dyh.firistcode.service.ServiceActivity
import com.dyh.firistcode.webview.NetActivity
import com.dyh.firistcode.webview.WebViewActivity

class MainActivity : BaseActivity() {

    private lateinit var mBtnFragment: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        mBtnFragment = findViewById(R.id.btn_fragment)
        mBtnFragment.setOnClickListener {
            startActivity(Intent(this@MainActivity, FragmentActivity::class.java))
        }

        findViewById<Button>(R.id.btn_rcv).setOnClickListener {
            startActivity(Intent(this@MainActivity, RcvActivity::class.java))
        }

        findViewById<Button>(R.id.btn_offline).setOnClickListener {
            sendBroadcast(Intent("com.dyh.broadcast.FORCE_OFFLINE"))
        }

        findViewById<Button>(R.id.btn_save).setOnClickListener {
//            startActivity(Intent(this@MainActivity, FileSaveActivity::class.java))
//            startActivity(Intent(this@MainActivity, SpSaveActivity::class.java))
            startActivity(Intent(this@MainActivity, DbSaveActivity::class.java))
        }

        findViewById<Button>(R.id.btn_content_provider).setOnClickListener {
            startActivity(Intent(this@MainActivity, ContentdProviderActivity::class.java))
        }

        findViewById<Button>(R.id.btn_notification).setOnClickListener {
            startActivity(Intent(this@MainActivity, NotificationActivity::class.java))
        }

        findViewById<Button>(R.id.btn_camera).setOnClickListener {
            startActivity(Intent(this@MainActivity, CameraActivity::class.java))
        }

        findViewById<Button>(R.id.btn_audio).setOnClickListener {
            startActivity(Intent(this@MainActivity, AudioActivity::class.java))
        }

        findViewById<Button>(R.id.btn_service).setOnClickListener {
            startActivity(Intent(this@MainActivity, ServiceActivity::class.java))
        }

        findViewById<Button>(R.id.btn_webView).setOnClickListener {
            startActivity(Intent(this@MainActivity, WebViewActivity::class.java))
        }

        findViewById<Button>(R.id.btn_net).setOnClickListener {
            startActivity(Intent(this@MainActivity, NetActivity::class.java))
        }

        findViewById<Button>(R.id.btn_material_design).setOnClickListener {
            startActivity(Intent(this@MainActivity, MaterialActivity::class.java))
        }

        findViewById<Button>(R.id.btn_jetpack).setOnClickListener {
            startActivity(Intent(this@MainActivity, JetpackActivity::class.java))
        }

    }
}