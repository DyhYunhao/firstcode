package com.dyh.firistcode.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.dyh.firistcode.R
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net)

        findViewById<Button>(R.id.btn_send_request).setOnClickListener {
            sendRequestWithHttpURLConnection()
        }

        val address = "https://www.baidu.com"
        //---------------------
        HttpUtil.sendHttpRequest(address, object: HttpCallbackListener {
            override fun finish(response: String) {
                //得到服务器返回
            }

            override fun error(e: Exception) {
                //错误
            }

        })

        HttpUtil.sendOkHttpRequest(address, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                //错误
            }

            override fun onResponse(call: Call, response: Response) {
                //得到服务器返回
                val res = response.body?.string()
            }

        })

        //协程简化写法


        //--------------------
    }
    //协程简化写法
    suspend fun request(address: String) : String {
        return suspendCoroutine { continuation ->
            HttpUtil.sendHttpRequest(address, object : HttpCallbackListener{
                override fun finish(response: String) {
                    continuation.resume(response)
                }

                override fun error(e: Exception) {
                    continuation.resumeWithException(e)
                }

            })

        }
    }

    private fun sendRequestWithHttpURLConnection() {
        thread {
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL("https://www.baidu.com")
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val input = connection.inputStream
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                showResponse(response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
        }
    }

    private fun sendRequestWithOkHttp() {
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder().url("https://www.baidu.com").build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null) {
                    showResponse(responseData)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getDataByRetrofit() {
//        val retrofit = Retrofit.Builder().baseUrl("http://127.0.0.1/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        val appService = retrofit.create(AppService::class.java)
        val appService = ServiceCreator.create(AppService::class.java)
        //val appService = ServiceCreator.create<AppService>()

        appService.getAppData().enqueue(object : retrofit2.Callback<List<App>> {
            override fun onResponse(
                call: retrofit2.Call<List<App>>,
                response: retrofit2.Response<List<App>>
            ) {
                val list = response.body()
                if (list != null) {
                    for (app in list) {
                        //...........
                    }
                }
            }

            override fun onFailure(call: retrofit2.Call<List<App>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    private fun showResponse(response: String) {
        runOnUiThread {
            findViewById<TextView>(R.id.tv_response).text = response
        }
    }
}