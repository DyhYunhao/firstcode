package com.dyh.firistcode.datasave

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.dyh.firistcode.R
import java.io.*
import java.lang.StringBuilder

class FileSaveActivity : AppCompatActivity() {

    lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_save)
        editText = findViewById<EditText>(R.id.et_data)

        val text = load()
        if (text.isNotEmpty()) {
            editText.setText(text)
            editText.setSelection(text.length)
            Toast.makeText(this, "restore succeeded", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val text = findViewById<EditText>(R.id.et_data).text.toString()
        save(text)
    }

    private fun save(text: String) {
        try {
            val output = openFileOutput("data", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            //use是kotlin的扩展函数，表示lambda表达式中代码全部执行完成后自动将外层的流关闭，不需要finally手动关闭
            writer.use {
                it.write(text)
            }
        } catch (e : IOException) {
            e.printStackTrace()  //kotlin取消了异常检查机制（checked exception）为了规范可写可不写
        }
    }

    private fun load(): String {
        val content = StringBuilder()
        try {
            val input = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    content.append(it)
                }
            }
        } catch (e : IOException) {
            e.printStackTrace()
        }
        return content.toString()
    }
}