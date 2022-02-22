package com.dyh.firistcode.datasave

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.dyh.firistcode.R

class SpSaveActivity : AppCompatActivity() {

    lateinit var mBtnSpSave: Button
    lateinit var mBtnSpRead: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sp_save)

        mBtnSpSave = findViewById(R.id.btn_sp_save)
        mBtnSpRead = findViewById(R.id.btn_sp_read)

        mBtnSpSave.setOnClickListener {
//            val editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit()
//            editor.run {
//                putString("name", "Tom")
//                putInt("age", 28)
//                putBoolean("married", false)
//                apply()
//            }
            //使用高阶函数简化如下sp.kt
//            getSharedPreferences("data", Context.MODE_PRIVATE).open {
//                putString("name", "Tom")
//                putInt("age", 28)
//                putBoolean("married", false)
//            }
            //core-ktx扩展简化
//            getSharedPreferences("data", Context.MODE_PRIVATE).edit{
//                putString("name", "Tom")
//                putInt("age", 28)
//                putBoolean("married", false)
//            }

        }

        mBtnSpRead.setOnClickListener {
            val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
            val name = prefs.getString("name", "")
            val age = prefs.getInt("age", 0)
            val married = prefs.getBoolean("married", false)

            Log.d("sp", "name is $name, age is $age, married is $married")
        }
    }
}