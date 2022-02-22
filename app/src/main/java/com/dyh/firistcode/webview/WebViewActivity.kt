package com.dyh.firistcode.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.dyh.firistcode.R

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val mWvWebView = findViewById<WebView>(R.id.wv_webView)
        mWvWebView.settings.javaScriptEnabled = true
        mWvWebView.webViewClient = WebViewClient()
        mWvWebView.loadUrl("https://www.baidu.com")
    }
}