package com.dyh.firistcode.datasave

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.dyh.firistcode.R
import java.lang.Exception
import java.lang.NullPointerException

class DbSaveActivity : AppCompatActivity() {

    lateinit var mBtnCreateDatabase: Button
    lateinit var mBtnAddData: Button
    lateinit var mBtnUpdateData: Button
    lateinit var mBtnDeleteData: Button
    lateinit var mBtnQueryData: Button
    lateinit var mBtnReplaceData: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_db_save)

        mBtnCreateDatabase = findViewById(R.id.btn_create_database)
        mBtnAddData = findViewById(R.id.btn_add_data)
        mBtnUpdateData = findViewById(R.id.btn_update_data)
        mBtnDeleteData = findViewById(R.id.btn_delete_data)
        mBtnQueryData = findViewById(R.id.btn_query_data)
        mBtnReplaceData = findViewById(R.id.btn_replace_data)

        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 2)
        mBtnCreateDatabase.setOnClickListener {
            dbHelper.writableDatabase
        }
        mBtnAddData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                put("name", "thinking in java")
                put("author", "Brunch")
                put("pages", 500)
                put("price", 17.1)
            }
            db.insert("Book", null, values1)
            val values2 = ContentValues().apply {
                put("name", "the lost symbol")
                put("author", "Dan Brown")
                put("pages", 510)
                put("price", 19.95)
            }
            db.insert("Book", null, values2)
        }
        mBtnUpdateData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price", 10.99)
            db.update("Book", values, "name = ?", arrayOf("the lost symbol"))
        }
        mBtnDeleteData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.delete("Book", "pages > ?", arrayOf("500"))
        }
        mBtnQueryData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val cursor = db.query("Book", null, null, null, null, null, null)
            if (cursor.moveToFirst()) {
                do {
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))
                    Log.d("db", "$name, $author, $pages, $price")
                } while (cursor.moveToNext())
            }
            cursor.close()
        }
        mBtnReplaceData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.beginTransaction() //开启事务
            try {
                db.delete("Book", null, null)
//                if (true) {
//                    //手动抛出异常，让事务失败
//                    throw NullPointerException()
//                }
                val values = ContentValues().apply {
                    put("name", "Game of Thrones")
                    put("author", "George Martin")
                    put("pages", 729)
                    put("price", 29.95)
                }
                db.insert("Book", null, values)
                db.setTransactionSuccessful() //事务已经执行成功
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction() // 结束事务
            }
        }
    }
}