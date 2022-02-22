package com.dyh.firistcode.webview

import java.lang.Exception

interface HttpCallbackListener {
    fun finish(response: String)
    fun error(e: Exception)
}